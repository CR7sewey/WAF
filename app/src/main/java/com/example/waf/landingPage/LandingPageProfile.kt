package com.example.waf.landingPage

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waf.R
import com.example.waf.common.modules.Player
import com.example.waf.common.modules.Positions
import kotlin.String

@Composable
fun LandingPageProfile(modifier: Modifier = Modifier) {
    val fakeProfile: Player = Player(
        id = "1",
        name = "Mike",
        age = 26,
        favoritePosition = Positions.AttackingMidfielder,
        mostPlayedPosition = Positions.AttackingMidfielder,
        goals = 10,
        assists = 12,
        rating = 87,
        skill1 = "Dribbling",
        url = null,
        favoriteTeam = null,
        favoritePlayer= null,
         skill2= null,
         skill3= null,
         skill4= null,
         skill5= null,
        gamesPlayed = 20
    )
    ElevatedCardExample(fakeProfile)
}

@Composable
fun ElevatedCardExample(profile: Player) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            . padding(24.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = profile.name,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                    Text(
                        text = "${profile.age}",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                }

                Text(
                    text = "${profile.rating}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 38.sp,
                    modifier = Modifier.background(color = Color.LightGray, shape = CircleShape).padding(6.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 4.dp, end = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

                ) {
                var position: String = ""
                profile.favoritePosition?.name?.forEach { it ->
                    if (it == it.toUpperCase())
                        position += " $it"
                    else
                        position += it.toString()

                }
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center

                ) {
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.goal),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            contentScale = ContentScale.Crop,
                            alpha = 0.5F
                        )
                        Text(
                            text = "${profile.gamesPlayed}",
                            maxLines = 1,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                    }
                    Spacer(modifier = Modifier.height(height = 4.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.goal),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            contentScale = ContentScale.Crop,
                            alpha = 0.5F
                        )
                        Text(
                            text = "${profile.goals}",
                            maxLines = 1,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                    }
                    Spacer(modifier = Modifier.height(height = 4.dp))
                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.player),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            contentScale = ContentScale.Crop,
                            alpha = 0.5F
                        )

                        Text(
                            text = "${profile.assists}",
                            maxLines = 1,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }


                }
                Text(
                    text = position.trimStart(),
                    maxLines = 1,
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                    fontStyle = FontStyle.Italic,
                )


            }


        }

    }
}

@Preview
@Composable
fun ElevatedCardPreview() {
    val fakeProfile: Player = Player(
        id = "1",
        name = "Mike",
        age = 26,
        favoritePosition = Positions.AttackingMidfielder,
        mostPlayedPosition = Positions.AttackingMidfielder,
        goals = 10,
        assists = 12,
        rating = 87,
        skill1 = "Dribbling",
        url = null,
        favoriteTeam = null,
        favoritePlayer= null,
        skill2= null,
        skill3= null,
        skill4= null,
        skill5= null,
        gamesPlayed = 20
    )
    ElevatedCardExample(fakeProfile)
}