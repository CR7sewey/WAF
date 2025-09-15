package com.example.waf.landingPage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LandingPage(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Landing Page",
            modifier = modifier

        )
        LandingPageProfile(modifier)
    }
}