package com.example.waf

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
fun Navigation(navGraph: NavHostController, modifier: Modifier = Modifier) {


    val graph: NavGraph = navGraph.createGraph(
        startDestination = NavigationRoutes.LandingPage.route,
    ) {
        composable(route = NavigationRoutes.SignPage.route) {
            SignPage()
        }
        composable(route = NavigationRoutes.LandingPage.route) {
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