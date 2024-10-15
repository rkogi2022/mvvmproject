package com.rhyttah.schwebsite.ui.theme.Screens.register


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rhyttah.schwebsite.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import com.rhyttah.schwebsite.navigation.ROUTE_LOGIN
import com.rhyttah.schwebsite.ui.theme.Models.User

@Composable
fun RegisterScreen(navController: NavController) { // Pass navController here
    var myname by remember { mutableStateOf(TextFieldValue("")) }
    var myemail by remember { mutableStateOf(TextFieldValue("")) }
    var myage by remember { mutableStateOf(TextFieldValue("")) }
    var mypass by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Circle Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .border(5.dp, Color.Gray, CircleShape)
        )
        Text(text = "NextGen Coders Club",
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Monospace)

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = myname,
            onValueChange = { myname = it },
            label = {
                Text(
                    text = "Enter your Name....",
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = myemail,
            onValueChange = { myemail = it },
            label = {
                Text(
                    text = "Enter Email....",
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = myage,
            onValueChange = { myage = it },
            label = {
                Text(
                    text = "Enter your age....",
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = mypass,
            onValueChange = { mypass = it },
            label = {
                Text(
                    text = "Enter Password....",
                    color = Color.Red,
                    fontSize = 12.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontStyle = FontStyle.Italic
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                // Create a User object
                val user = User(myname.text, myemail.text, myage.text, mypass.text)

                // Get a reference to the Firebase Database
                val database = Firebase.database
                val usersRef = database.getReference("users")

                // Push user data to Firebase under a unique key
                val newUserRef = usersRef.push()

                newUserRef.setValue(user)
                    .addOnSuccessListener {
                        // Handle success (e.g., show a Toast or navigate back to the login screen)
                        navController.navigate(ROUTE_LOGIN)
                    }
                    .addOnFailureListener {
                        // Handle failure (e.g., show a Toast with error message)
                    }
            },
            modifier = Modifier.width(300.dp)
        ) {
            Text(
                text = "Register",
                color = Color.Black,
                fontFamily = FontFamily.Serif,
                fontSize = 25.sp
            )
        }


        TextButton(onClick = {
            navController.navigate(ROUTE_LOGIN) // Navigate back to LoginScreen
        }) {
            Text(
                text = "I already have an account. Click here to Login",
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 15.sp
            )
        }
    }
}

@Preview
@Composable
private fun RegisterPrev() {
    RegisterScreen(navController = rememberNavController())
}