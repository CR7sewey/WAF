package com.example.waf

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.waf.common.navigation.NavigationRoutes
import com.example.waf.ui.theme.WAFTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WAFTheme(dynamicColor = false) {
                val navController = rememberNavController();
                var currentRoute by remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
                val scopeCourotine = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (
                            currentRoute == NavigationRoutes.EntryScreen.route ||
                            currentRoute == NavigationRoutes.SignPage.route ||
                            currentRoute == null
                            ) {
                            return@Scaffold
                        }
                        Bottom(navController)
                    },
                   /* topBar = {
                        if (
                            currentRoute == NavigationRoutes.EntryScreen.route ||
                            currentRoute == NavigationRoutes.SignPage.route ||
                            currentRoute == null
                        ) {
                            return@Scaffold
                        }
                        TopBar(scopeCourotine, drawerState)
                    }*/

                ) { innerPadding ->

                   /* ModalNavigationDrawer(
                        drawerContent = {
                            ModalDrawerSheet {
                                Column(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                        .verticalScroll(rememberScrollState())
                                ) {
                                    Spacer(Modifier.height(12.dp))
                                    Text("Drawer Title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                                    HorizontalDivider()

                                    Text("Section 1", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                                    NavigationDrawerItem(
                                        label = { Text("Item 1") },
                                        selected = false,
                                        onClick = { /* Handle click */ }
                                    )
                                    NavigationDrawerItem(
                                        label = { Text("Item 2") },
                                        selected = false,
                                        onClick = { /* Handle click */ }
                                    )

                                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                                    Text("Section 2", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                                    NavigationDrawerItem(
                                        label = { Text("Settings") },
                                        selected = false,
                                        icon = {
                                            androidx.compose.material3.Icon(
                                                Icons.Outlined.Settings,
                                                contentDescription = null
                                            )
                                        },
                                        badge = { Text("20") }, // Placeholder
                                        onClick = { /* Handle click */ }
                                    )
                                    NavigationDrawerItem(
                                        label = { Text("Help and feedback") },
                                        selected = false,
                                        icon = {
                                            androidx.compose.material3.Icon(
                                                Icons.Default.Call,
                                                contentDescription = null
                                            )
                                        },
                                        onClick = { /* Handle click */ },
                                    )
                                    Spacer(Modifier.height(12.dp))
                                }
                            }
                        },
                        drawerState = drawerState
                    ) {*/
                        Navigation(
                            navGraph = navController,
                            changeRoute = { it: String -> currentRoute = it },
                            modifier = Modifier.padding(innerPadding))
                //}

                    Log.d("Current ROute", currentRoute.toString())
                }
            }
        }
    }
}

@Composable
fun Bottom(navHostController: NavHostController, modifier: Modifier = Modifier) {

    data class BottomItems(
        val obj: NavigationRoutes,
        val title: String,
        val icon: ImageVector
    )

    var items = listOf(
       BottomItems(
           obj = NavigationRoutes.LandingPage,
           title = "Home",
           icon = Icons.Default.Home
       ),
        BottomItems(
            obj = NavigationRoutes.RatingPage,
            title = "Rating",
            icon = Icons.Default.Search
        ),
        BottomItems(
            obj = NavigationRoutes.ProfilePage,
            title = "Profile",
            icon = Icons.Default.Person
        )
    )

    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    BottomNavigation(
        elevation = 20.dp,
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    if (item.obj is NavigationRoutes.ProfilePage) {
                        navHostController.navigate(item.obj.createRoute("1"))
                    }
                    navHostController.navigate(item.obj.route)

                },
                icon = {
                    if (item.icon == null) {
                        return@NavigationBarItem
                    }
                    Icon(
                        imageVector = item.icon,
                        modifier = modifier.size(24.dp),

                        contentDescription = null,
                    )
                    //Icon(painter = painterResource(id = item.icon), contentDescription = null)

                },
                label = {
                    Text(
                        item.title,

                        )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary,

                )
            )

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scopeCourotine: CoroutineScope, drawerState: androidx.compose.material3.DrawerState) {

        TopAppBar(
            title = { Text("jjjj") },
            navigationIcon = {
                IconButton(onClick = {
                    scopeCourotine.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                },
                ) {
                    androidx.compose.material3.Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
            ),
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        )

}

/*
@Composable
fun MyDrawer(nav: @Composable Unit, drawerState: androidx.compose.material3.DrawerState) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Drawer Title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    HorizontalDivider()

                    Text("Section 1", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("Item 1") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Item 2") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Section 2", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        icon = {
                            androidx.compose.material3.Icon(
                                Icons.Outlined.Settings,
                                contentDescription = null
                            )
                        },
                        badge = { Text("20") }, // Placeholder
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Help and feedback") },
                        selected = false,
                        icon = {
                            androidx.compose.material3.Icon(
                                Icons.Default.Call,
                                contentDescription = null
                            )
                        },
                        onClick = { /* Handle click */ },
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        nav

    }
}
*/
