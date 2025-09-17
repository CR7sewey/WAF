package com.example.waf.landingPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.waf.common.modules.Match
import com.example.waf.fakeMatch

@Composable
fun LandingPageMatch(navHostController: NavHostController, modifier: Modifier = Modifier) {
    MatchContent(navHostController, modifier)
}

@Composable
fun MatchContent(navHostController: NavHostController, modifier: Modifier = Modifier) {
    val fakeMatch = fakeMatch
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 10.dp)
            .clickable {
                navHostController.navigate(route = "matchPage/${fakeMatch.id}")
            }
        ,
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(text = fakeMatch.team1?.name ?: "Team 1",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                    )
                Text(text = fakeMatch.team2?.name ?: "Team 2",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
            Text(text = fakeMatch.pitch.location)
            Text(text = fakeMatch.result)
            Row {
                Text(fakeMatch.date)
                Spacer(modifier = Modifier.width(6.dp))
                Text(fakeMatch.time)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${fakeMatch.team1?.overallRating}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(color = when {
                            fakeMatch.team1.overallRating > fakeMatch.team2.overallRating -> Color.Green
                            fakeMatch.team1.overallRating < fakeMatch.team2.overallRating -> Color.Red
                            else -> Color.Yellow
                        }, shape = CircleShape)
                        .padding(6.dp)

                )
                Text(text = "Closed: ${fakeMatch.closed}")
                Text(text = "${fakeMatch.team2.overallRating}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(color =when {
                            fakeMatch.team2.overallRating > fakeMatch.team1.overallRating -> Color.Green
                            fakeMatch.team2.overallRating < fakeMatch.team1.overallRating -> Color.Red
                            else -> Color.Yellow
                        }, shape = CircleShape)
                        .padding(6.dp)

                )
            }
        }




    }
}