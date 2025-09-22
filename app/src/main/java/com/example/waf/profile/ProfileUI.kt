package com.example.waf.profile

import android.window.SplashScreen
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.CarouselState
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.waf.R
import com.example.waf.fakeProfile

@Composable
fun ProfileUI(id: String, modifier: Modifier = Modifier) {


        var scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) { // Player Profile
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.footballplayer),
                        contentDescription = "user",
                        modifier = modifier
                            .width(156.dp)
                            .height(176.dp)
                            .padding(8.dp)

                    )
                    Column(
                        modifier = modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier.padding(end = 8.dp)
                        ) {
                            CardComposition("Name", "Miguel Carvalho", modifier)
                            Spacer(modifier = modifier.width(16.dp))
                            CardComposition("Age", "26", modifier)
                        }
                        Spacer(modifier = modifier.height(8.dp))
                        Row {
                            CardComposition("Favorite Position", "Attacking Midfielder", modifier)
                        }
                        Spacer(modifier = modifier.height(8.dp))
                        Row {
                            CardComposition("Favorite Team", "Real Madrid", modifier)
                        }
                        Spacer(modifier = modifier.height(8.dp))
                        Row {
                            CardComposition("Favorite Player", "Neymar Jr", modifier)
                        }
                    }
                }
            }
            // games played, goals, assists, rating etc
            CarouselExample_MultiBrowse()
            PlayerListSkills()

        }


}

@Composable
fun CardComposition(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${label}:",
            fontWeight = FontWeight.Bold,

            )
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic,
           // color = Color.DarkGray

        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselExample_MultiBrowse() {
    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val contentDescription: String
    )

    data class CarouselItem2(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val name: String,
        val color: Color
        )

    val items = remember {
       /* listOf(
            CarouselItem(0, R.drawable.baseline_stars_24, "donut"),
            CarouselItem(1, R.drawable.baseline_stars_24, "cupcake"),
            CarouselItem(2, R.drawable.baseline_stars_24, "cupcake"),
            CarouselItem(3, R.drawable.baseline_stars_24, "cupcake"),

            )*/
        listOf(
            CarouselItem2(0, R.drawable.goal, "Goals", Color.Green),
            CarouselItem2(1, R.drawable.goal, "Assists", Color.Yellow),
            CarouselItem2(2, R.drawable.goal, "Game", Color.Blue),
            CarouselItem2(3, R.drawable.goal, "Assists", Color.Yellow),
            CarouselItem2(4, R.drawable.goal, "Game", Color.Blue),


            )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        HorizontalUncontainedCarousel(
            state = rememberCarouselState(initialItem = 2) { items.count() },
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 8.dp),
            itemWidth = 150.dp,
            itemSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) { i ->
            val item = items[i]

                // Background Image

                Image(
                    painter = painterResource(id = item.imageResId), // Use the image for the card
                    contentDescription = "${item.name} background",
                    contentScale = ContentScale.Fit, // Important for background behavior
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f)
                        .maskClip(MaterialTheme.shapes.large),
                    // You can add an alpha modifier here if you want to make the image semi-transparent

                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(48.dp))
                    Text(
                        item.name,
                        //color = Color.Black, // Adjust for visibility on your image
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,

                    )
                    Text(
                        "24", // Example data
                      //  color = Color.Black, // Adjust for visibility,
                        fontSize = 16.sp
                    )
                }
            }
            /*   Image(
                    modifier = Modifier
                        .height(220.dp)
                        .width(220.dp)
                        .maskClip(MaterialTheme.shapes.large),
                    painter = painterResource(id = item.imageResId),
                    contentDescription = item.contentDescription,
                    contentScale = ContentScale.Crop
                )*/
            Spacer(modifier = Modifier.width(12.dp))
        }
        //}


}

@Composable
fun PlayerListSkills(modifier: Modifier = Modifier) {

    val player = fakeProfile

    Column {
        Spacer(modifier = Modifier.height(8.dp))
        fakeProfile.skill1.forEach { skill ->

            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = Modifier.padding(vertical = 2.dp, horizontal = 24.dp)
            )
            {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = skill?.name ?: "")
                    Text(text = skill?.rating.toString())
                }
            }
            Spacer(modifier = Modifier.height(4.dp))


        }

    }

}