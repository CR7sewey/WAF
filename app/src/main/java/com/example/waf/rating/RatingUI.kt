package com.example.waf.rating

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun RatingUI(onTimeout: () -> Unit, modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 8f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "scale"
    )

    // LaunchedEffect to navigate after a delay
    LaunchedEffect(key1 = true) { // `true` ensures this runs once on composition
        delay(3000L) // Wait for 3000 milliseconds (3 seconds)

        // Navigate to your desired screen. Replace with your actual route.
        // For example, navigate to LandingPage:
       /* navController.navigate(NavigationRoutes.LandingPage.route) {
            // Optional: Clear RatingUI from the back stack
            popUpTo(NavigationRoutes.RatingScreen.route) { // Assuming RatingUI's route name
                inclusive = true
            }*/
            // Optional: If the destination could already be on top
            // launchSingleTop = true
        onTimeout.invoke()
        }


    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello",
            modifier = Modifier
                .graphicsLayer() {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
                .align(Alignment.Center),
            // Text composable does not take TextMotion as a parameter.
            // Provide it via style argument but make sure that we are copying from current theme
            style = LocalTextStyle.current.copy(textMotion = TextMotion.Animated)
        )
    }
}


// In the parent screen that uses RatingUI
@Composable
fun ParentScreen(navController: NavController) {
    var showRating by remember { mutableStateOf(true) }

    if (showRating) {
        RatingUI(
            onTimeout = {
                showRating = false // Hide RatingUI
                //navController.navigate("your_next_screen_route") {
                    // popUpTo logic if needed
                })


    } else {
        // Show other content or indicate navigation has happened
        Text("Navigated!")
    }
}
