package com.example.waf.common.navigation

sealed class NavigationRoutes(public val id: String, public val route: String) {

    object SignPage : NavigationRoutes(id = "1", route = "signPage")
    object LandingPage : NavigationRoutes(id = "2", route = "landingPage")
    object ProfilePage : NavigationRoutes(id = "3", route = "profilePage" + "/{id}")





}