package com.rhyttah.schwebsite.ui.theme.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rhyttah.schwebsite.R
import com.rhyttah.schwebsite.navigation.ROUTE_ADD
import com.rhyttah.schwebsite.navigation.ROUTE_HOME
import com.rhyttah.schwebsite.navigation.ROUTE_UPDATE
import com.rhyttah.schwebsite.navigation.ROUTE_VIEW

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarScreen(navController: NavController) {
    TopAppBar(
        title = { Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Circle Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape) // clip to the circle shape
                .border(5.dp, Color.Gray, CircleShape)//optional

        ) },
        actions = {
            Row {
                TextButton(onClick = { navController.navigate(ROUTE_HOME) }) {
                    Text(text = "Home", color = Color.White)
                }
                TextButton(onClick = { navController.navigate(ROUTE_ADD) }) {
                    Text(text = "Add", color = Color.White)
                }
                TextButton(onClick = { navController.navigate(ROUTE_VIEW) }) {
                    Text(text = "View", color = Color.White)
                }
                TextButton(onClick = { navController.navigate(ROUTE_UPDATE) }) {
                    Text(text = "Update", color = Color.White)
                }
                TextButton(onClick = { /* Handle logout */ }) {
                    Text(text = "Log Out", color = Color.White)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EE)
        )
    )
}


@Preview
@Composable
fun TopBarPrev() {
   NavigationBarScreen(rememberNavController())
}