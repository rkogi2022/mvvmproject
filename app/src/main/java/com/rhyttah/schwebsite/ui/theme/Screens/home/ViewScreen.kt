package com.rhyttah.schwebsite.ui.theme.Screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rhyttah.schwebsite.ui.theme.Components.NavigationBarScreen
import com.rhyttah.schwebsite.ui.theme.Models.Student

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewScreen(navController: NavController, studentList: List<Student>) {
    Scaffold(
        topBar = { NavigationBarScreen(navController) },
        content = {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text("Student Marks", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

                // Table Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(8.dp)
                ) {
                    Text("Name", modifier = Modifier.weight(1f), color = Color.White, textAlign = TextAlign.Center)
                    Text("HTML", modifier = Modifier.weight(1f), color = Color.White, textAlign = TextAlign.Center)
                    Text("CSS", modifier = Modifier.weight(1f), color = Color.White, textAlign = TextAlign.Center)
                    Text("JS", modifier = Modifier.weight(1f), color = Color.White, textAlign = TextAlign.Center)
                    Text("Python", modifier = Modifier.weight(1f), color = Color.White, textAlign = TextAlign.Center)
                    Text("Java", modifier = Modifier.weight(1f), color = Color.White, textAlign = TextAlign.Center)
                    Text("C#", modifier = Modifier.weight(1f), color = Color.White, textAlign = TextAlign.Center)
                }

                // Table Body
                studentList.forEach { student ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(student.name, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                        Text(student.htmlMarks.toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                        Text(student.cssMarks.toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                        Text(student.jsMarks.toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                        Text(student.pythonMarks.toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                        Text(student.javaMarks.toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                        Text(student.cSharpMarks.toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ViewPrev() {
    val sampleList = listOf(
        Student("John Doe", 85, 90, 75, 80, 95, 88),
        Student("Jane Smith", 78, 88, 82, 91, 84, 90)
    )
    ViewScreen(rememberNavController(), studentList = sampleList)
}
