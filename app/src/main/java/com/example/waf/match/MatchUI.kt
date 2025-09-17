package com.example.waf.match

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import com.example.waf.common.modules.Match
import com.example.waf.common.modules.Player
import com.example.waf.common.modules.Team
import com.example.waf.fakeMatch
import kotlinx.coroutines.delay

@Composable
fun MatchUI(id: String, modifier: Modifier = Modifier) {
    Column {
        MatchContent(fakeMatch)

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Allow this Row to take remaining vertical space
                .padding(8.dp),
           horizontalAlignment = Alignment.CenterHorizontally // Or Arrangement.spacedBy(8.dp)

        ) {
            val fakeMatch = fakeMatch
            // Team 1 List
            TeamList(
                match = com.example.waf.fakeMatch,
                team = com.example.waf.fakeMatch.team1,
                onClick = {/**/},
                modifier = Modifier
                    .weight(1f) // Each team list takes half the width
                    .fillMaxHeight() // Fill available height in the Row
            )

            Spacer(modifier = Modifier.height(8.dp)) // Optional spacer between lists

            // Team 2 List
            TeamList(

                match = com.example.waf.fakeMatch,
                team = com.example.waf.fakeMatch.team2,
                onClick = {/**/},

                modifier = Modifier
                    .weight(1f) // Each team list takes half the width
                    .fillMaxHeight() // Fill available height in the Row
            )
        }

    }
}

@Composable
fun RatingDisplay(rating: Int, isWinner: Boolean, isLoser: Boolean) {
    val backgroundColor = when {
        isWinner -> Color.Green.copy(alpha = 0.7f)
        isLoser -> Color.Red.copy(alpha = 0.7f)
        else -> Color.Yellow.copy(alpha = 0.7f) // Draw
    }
    Text(
        text = "$rating",
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color.Black, // Ensure text is visible
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .padding(horizontal = 12.dp, vertical = 6.dp) // Adjusted padding
    )
}


@Composable
fun TeamList(match: Match, team: Team, onClick: () -> Unit, modifier: Modifier = Modifier) {

    if (match == null) {
        // Handle case where team data might be null (e.g., still loading or not available)
        Column(modifier = modifier.padding(8.dp)) {
            Text("Team data not available.")
        }
        return
    }

    // Assuming pitchDimensions "5x5" means 5 players per team for this list example
    // Or you can use team.players.size if players list is available
    val numberOfPlayersToList = try {
        match.pitch.pitchDimensions.split("x").firstOrNull()?.toIntOrNull() ?: 0
    } catch (e: Exception) {
        0
    }

    LazyColumn(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item { // Header for the team name
            Text(
                text = team.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        items(numberOfPlayersToList as Int) { it -> // Use team.players directly
            var player: Player
            try {
                player = team.players[it]
            }
            catch (e: Exception) {
                return@items
            }

            Card(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp) // Add horizontal padding
                    .fillMaxWidth()
            ) {
                Text(
                    text = player.name, // Display player name or details
                    modifier = Modifier
                        .padding(16.dp) // Add padding inside the card
                        .align(Alignment.CenterHorizontally)
                )
            }
        }



    }

    if (team.players.size < numberOfPlayersToList) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val pressedListener by rememberUpdatedState(onClick)
        val stepDelay = 100L

        LaunchedEffect(isPressed) {
            while (isPressed) {
                delay(stepDelay.coerceIn(1L, Long.MAX_VALUE))
                pressedListener()
            }
        }

        IconButton(
            modifier = modifier,
            onClick = onClick,
            interactionSource = interactionSource
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Add Player",
                tint = Color.Magenta,
                modifier = Modifier.size(48.dp)
            )
        }
    }


}


@Composable
fun MatchContent(match: Match, modifier: Modifier = Modifier) { // Added match parameter
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier // Apply modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = match.team1.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Text(
                    text = match.team2.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = match.pitch.location)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = match.result, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(match.date)
                Spacer(modifier = Modifier.width(6.dp))
                Text(match.time)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround, // SpaceAround for ratings and closed status
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Team 1 Rating
                match.team1.let { team ->
                    RatingDisplay(
                        rating = team.overallRating,
                        isWinner = team.overallRating > (match.team2?.overallRating ?: -1) ,
                        isLoser = team.overallRating < (match.team2?.overallRating ?: Int.MAX_VALUE)
                    )
                }

                Text(text = "Closed: ${match.closed}")

                // Team 2 Rating
                match.team2?.let { team ->
                    RatingDisplay(
                        rating = team.overallRating,
                        isWinner = team.overallRating > (match.team1?.overallRating ?: -1),
                        isLoser = team.overallRating < (match.team1?.overallRating ?: Int.MAX_VALUE)
                    )
                }
            }
        }
    }
}