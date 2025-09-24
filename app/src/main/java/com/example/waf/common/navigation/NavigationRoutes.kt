package com.example.waf.common.navigation

sealed class NavigationRoutes(public val id: String, public val route: String) {

    object EntryScreen : NavigationRoutes(id = "0", route = "entryScreen")
    object SignPage : NavigationRoutes(id = "1", route = "signPage")
    object LandingPage : NavigationRoutes(id = "2", route = "landingPage")
    object ProfilePage : NavigationRoutes(id = "3", route = "profilePage" + "/{id}") {
        fun createRoute(id: String) = "profilePage/$id"
    }
    object MatchPage : NavigationRoutes(id = "4", route = "matchPage" + "/{id}") {
        fun createRoute(id: String) = "matchPage/$id"
    }
    object RatingPage : NavigationRoutes(id = "5", route = "ratingPage")






}