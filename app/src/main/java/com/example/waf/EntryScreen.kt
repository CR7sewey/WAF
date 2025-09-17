package com.example.waf

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.waf.common.navigation.NavigationRoutes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun EntryScreen(navHostController: NavHostController, modifier: Modifier = Modifier) {


    LaunchedEffect(key1 = true) {
        delay(3000L)
        navHostController.navigate(route = NavigationRoutes.LandingPage.route) {
            // Optional: Configure popUpTo to clear EntryScreen from the back stack
            // so the user can't navigate back to it using the system back button.
            popUpTo(NavigationRoutes.EntryScreen.route) {
                inclusive = true
            }
            // Optional: launchSingleTop = true if LandingPage could already be on top
            // and you don't want to create a new instance.
            // launchSingleTop = true
        }
    }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navHostController.navigate(route = NavigationRoutes.LandingPage.route)
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )
        }

}