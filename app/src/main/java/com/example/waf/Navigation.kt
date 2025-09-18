package com.example.waf

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navArgument
import com.example.waf.common.navigation.NavigationRoutes
import com.example.waf.landingPage.LandingPage
import com.example.waf.match.MatchUI
import com.example.waf.profile.ProfileUI
import com.example.waf.sign.SignPage


@Composable
fun Navigation(navGraph: NavHostController, changeRoute: (String) -> Unit, modifier: Modifier = Modifier) {


    val graph: NavGraph = navGraph.createGraph(
        startDestination = NavigationRoutes.EntryScreen.route,
    ) {
        composable(
            route = NavigationRoutes.EntryScreen.route,
            exitTransition = {
                // Define your exit transition here
                // Example: Slide out to the left
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700) // Optional: Customize duration (e.g., 700ms)
                )
                // Another example: Fade out
                // fadeOut(animationSpec = tween(500))
            }
        ) {
            EntryScreen(navGraph)
        }
        composable(route = NavigationRoutes.SignPage.route,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(1000, easing = LinearEasing)
                )
            }
            ) {
            SignPage()
        }
        composable(route = NavigationRoutes.LandingPage.route) {
            changeRoute.invoke(navGraph.currentBackStackEntry?.destination?.route.toString())
            LandingPage(navGraph)
        }
        composable(route = NavigationRoutes.ProfilePage.route, arguments = listOf(navArgument("id"){type =
            NavType.StringType})) {
            ProfileUI(id = requireNotNull(it.arguments?.getString("id").toString()))
        }
        composable(route = NavigationRoutes.MatchPage.route, arguments = listOf(navArgument("id"){type =
            NavType.StringType})) {
            MatchUI(id = requireNotNull(it.arguments?.getString("id").toString()))
        }



    }

    NavHost(
        navController = navGraph,
        graph = graph,
        modifier = modifier
    )
    
}