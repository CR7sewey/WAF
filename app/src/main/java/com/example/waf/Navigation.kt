package com.example.waf

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.waf.common.navigation.NavigationRoutes
import com.example.waf.landingPage.LandingPage
import com.example.waf.sign.SignPage


@Composable
fun Navigation(navGraph: NavHostController, modifier: Modifier = Modifier) {


    val graph: NavGraph = navGraph.createGraph(
        startDestination = NavigationRoutes.LandingPage.route,
    ) {
        composable(route = NavigationRoutes.SignPage.route) {
            SignPage()
        }
        composable(route = NavigationRoutes.LandingPage.route) {
            LandingPage()
        }

    }

    NavHost(
        navController = navGraph,
        graph = graph,
        modifier = modifier
    )
    
}