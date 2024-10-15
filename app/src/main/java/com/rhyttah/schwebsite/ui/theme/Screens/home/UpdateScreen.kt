import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase
import com.rhyttah.schwebsite.navigation.ROUTE_VIEW
import com.rhyttah.schwebsite.ui.theme.Components.NavigationBarScreen
import com.rhyttah.schwebsite.ui.theme.Models.Student

@Composable
fun UpdateScreen(navController: NavController, studentList: List<Student>) {
    var name by remember { mutableStateOf("") }
    var htmlMarks by remember { mutableStateOf("") }
    var cssMarks by remember { mutableStateOf("") }
    var jsMarks by remember { mutableStateOf("") }
    var pythonMarks by remember { mutableStateOf("") }
    var javaMarks by remember { mutableStateOf("") }
    var cSharpMarks by remember { mutableStateOf("") }
    var studentFound by remember { mutableStateOf(false) }

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
                Text("Update Student Marks", fontSize = 24.sp)

                TextField(value = name, onValueChange = { name = it }, label = { Text("Student Name") })

                Button(onClick = {
                    // Find the student and pre-fill marks
                    val student = studentList.find { it.name == name }
                    if (student != null) {
                        htmlMarks = student.htmlMarks.toString()
                        cssMarks = student.cssMarks.toString()
                        jsMarks = student.jsMarks.toString()
                        pythonMarks = student.pythonMarks.toString()
                        javaMarks = student.javaMarks.toString()
                        cSharpMarks = student.cSharpMarks.toString()
                        studentFound = true
                    }
                }) {
                    Text("Find Student")
                }

                if (studentFound) {
                    TextField(value = htmlMarks, onValueChange = { htmlMarks = it }, label = { Text("HTML Marks") })
                    TextField(value = cssMarks, onValueChange = { cssMarks = it }, label = { Text("CSS Marks") })
                    TextField(value = jsMarks, onValueChange = { jsMarks = it }, label = { Text("JS Marks") })
                    TextField(value = pythonMarks, onValueChange = { pythonMarks = it }, label = { Text("Python Marks") })
                    TextField(value = javaMarks, onValueChange = { javaMarks = it }, label = { Text("Java Marks") })
                    TextField(value = cSharpMarks, onValueChange = { cSharpMarks = it }, label = { Text("C# Marks") })

                    Button(onClick = {
                        // Update the student marks in the local list
                        studentList.find { it.name == name }?.apply {
                            this.htmlMarks = htmlMarks.toInt() // Update marks
                            this.cssMarks = cssMarks.toInt()
                            this.jsMarks = jsMarks.toInt()
                            this.pythonMarks = pythonMarks.toInt()
                            this.javaMarks = javaMarks.toInt()
                            this.cSharpMarks = cSharpMarks.toInt()

                            // Update in Firebase
                            updateStudentInFirebase(this)
                        }
                        navController.navigate(ROUTE_VIEW)
                    }) {
                        Text("Update Marks")
                    }
                }
            }
        }
    )
}

private fun updateStudentInFirebase(student: Student) {
    val database = FirebaseDatabase.getInstance().reference
    val studentId = student.name // Use student ID or unique identifier if available
    database.child("students").child(studentId).setValue(student).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            // Successfully updated in Firebase
            Log.d("FirebaseUpdate", "Student updated successfully.")
        } else {
            // Handle failure
            Log.e("FirebaseUpdate", "Failed to update student: ${task.exception?.message}")
        }
    }
}

@Preview
@Composable
fun UpdatePrev() {
    val sampleList = mutableListOf(
        Student("John Doe", 85, 90, 75, 80, 95, 88)
    )
    UpdateScreen(rememberNavController(), studentList = sampleList)
}
