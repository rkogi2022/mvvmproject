package com.rhyttah.schwebsite.ui.theme.Screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rhyttah.schwebsite.R
import com.rhyttah.schwebsite.ui.theme.Components.NavigationBarScreen


@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { NavigationBarScreen(navController) },
        content = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = "Circle Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape) // clip to the circle shape
                        .border(5.dp, Color.Gray, CircleShape)//optional

                )
                Text(
                    text = "NextGen Coders Club",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Nextgen Coders Club is a coding program designed to introduce kids to the world of technology and programming. The club offers coding classes aimed at nurturing creativity, problem-solving skills, and logical thinking in young minds. " +
                            "Held on Saturdays, these sessions are an engaging and interactive way for kids to learn essential tech skills early, preparing them for a future driven by technology.",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}