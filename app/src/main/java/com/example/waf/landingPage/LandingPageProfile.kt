package com.example.waf.landingPage

import android.media.Image
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.waf.R
import com.example.waf.common.modules.Player
import com.example.waf.common.modules.Positions
import com.example.waf.common.navigation.NavigationRoutes
import com.example.waf.fakeProfile
import com.example.waf.profile.CardComposition
import kotlin.String

@Composable
fun LandingPageProfile(navHostController: NavHostController, modifier: Modifier) {
    val fakeProfile: Player = fakeProfile


    ElevatedCardExample(navHostController, fakeProfile)

}

@Composable
fun ElevatedCardExample(navHostController: NavHostController, profile: Player, modifier: Modifier = Modifier) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            /*.clickable {
                navHostController.navigate(route = "profilePage/${profile.id}")
            }*/
    ) {
        var expanded = remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .animateContentSize()
                .height(if (expanded.value) 420.dp else 180.dp)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    expanded.value = !expanded.value
                }

        ) {
            Column(

            ) {
                var position: String = ""
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
                        modifier = Modifier.background(color = Color.LightGray, shape = CircleShape)
                            .padding(6.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 10.dp, top = 4.dp, end = 10.dp, bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween

                ) {

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
                Spacer(modifier = Modifier.height(6.dp))
                Column(
                    modifier = modifier.padding(8.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row {
                        CardComposition(
                            "Favorite position",
                            position, modifier = modifier
                        )
                    }
                    Spacer(modifier = modifier.height(8.dp))
                    Row {
                        CardComposition(
                            "Most played position",
                            profile.mostPlayedPosition?.name.toString(), modifier = modifier
                        )
                    }
                    Spacer(modifier = modifier.height(8.dp))
                    Row(
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        CardComposition(
                            "Games",
                            profile.gamesPlayed.toString(), modifier = modifier
                        )
                        Spacer(modifier = modifier.width(16.dp))
                        CardComposition(
                            "Goals",
                            profile.goals.toString(), modifier = modifier
                        )
                        Spacer(modifier = modifier.width(16.dp))
                        CardComposition(
                            "Assists",
                            profile.assists.toString(), modifier = modifier
                        )
                        Spacer(modifier = modifier.width(16.dp))
                        CardComposition(
                            "Rating",
                            profile.rating.toString(), modifier = modifier
                        )
                    }
                    Spacer(modifier = modifier.height(8.dp))

                    Text(
                        text = "Skills:",
                        fontWeight = FontWeight.Bold,

                        )
                    LazyColumn {

                        items(profile.skill1.size) {
                            Text(
                                text = profile.skill1[it]?.name.toString(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                fontStyle = FontStyle.Italic,
                                // color = Color.DarkGray

                            )

                        }
                    }
                }

            }
        }

    }
}

@Preview
@Composable
fun ElevatedCardPreview() {
    val fakeProfile: Player = fakeProfile
    ElevatedCardExample(navHostController = rememberNavController(), fakeProfile)
}