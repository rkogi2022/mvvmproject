package com.rhyttah.schwebsite.ui.theme.Screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rhyttah.schwebsite.navigation.ROUTE_VIEW
import com.rhyttah.schwebsite.ui.theme.Components.NavigationBarScreen
import com.rhyttah.schwebsite.ui.theme.Models.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(navController: NavController, studentList: MutableList<Student>) {
    var name by remember { mutableStateOf("") }
    var htmlMarks by remember { mutableStateOf("") }
    var cssMarks by remember { mutableStateOf("") }
    var jsMarks by remember { mutableStateOf("") }
    var pythonMarks by remember { mutableStateOf("") }
    var javaMarks by remember { mutableStateOf("") }
    var cSharpMarks by remember { mutableStateOf("") }

    // Get a reference to your database
    val database = Firebase.database.reference

    Scaffold(
        topBar = { NavigationBarScreen(navController) },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Add Student Marks", fontSize = 24.sp)

                TextField(value = name, onValueChange = { name = it }, label = { Text("Student Name") })
                TextField(value = htmlMarks, onValueChange = { htmlMarks = it }, label = { Text("HTML Marks") })
                TextField(value = cssMarks, onValueChange = { cssMarks = it }, label = { Text("CSS Marks") })
                TextField(value = jsMarks, onValueChange = { jsMarks = it }, label = { Text("JS Marks") })
                TextField(value = pythonMarks, onValueChange = { pythonMarks = it }, label = { Text("Python Marks") })
                TextField(value = javaMarks, onValueChange = { javaMarks = it }, label = { Text("Java Marks") })
                TextField(value = cSharpMarks, onValueChange = { cSharpMarks = it }, label = { Text("C# Marks") })

                Button(onClick = {
                    // Create a student object
                    val student = Student(
                        name = name,
                        htmlMarks = htmlMarks.toIntOrNull() ?: 0,
                        cssMarks = cssMarks.toIntOrNull() ?: 0,
                        jsMarks = jsMarks.toIntOrNull() ?: 0,
                        pythonMarks = pythonMarks.toIntOrNull() ?: 0,
                        javaMarks = javaMarks.toIntOrNull() ?: 0,
                        cSharpMarks = cSharpMarks.toIntOrNull() ?: 0
                    )

                    // Add student to the list
                    studentList.add(student)

                    // Save to Firebase Realtime Database
                    val studentId = database.child("students").push().key
                    if (studentId != null) {
                        database.child("students").child(studentId).setValue(student)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Navigate to the view screen after successful submission
                                    navController.navigate(ROUTE_VIEW)
                                } else {
                                    // Handle error
                                    task.exception?.let {
                                        println("Firebase Error: ${it.message}")
                                    }
                                }
                            }
                    }
                }) {
                    Text("Submit")
                }
            }
        }
    )
}

@Preview
@Composable
fun AddPrev() {
    val sampleList = mutableListOf<Student>()
    AddScreen(rememberNavController(), studentList = sampleList)
}
