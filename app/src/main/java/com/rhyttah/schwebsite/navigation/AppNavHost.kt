package com.rhyttah.schwebsite.navigation

import UpdateScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rhyttah.schwebsite.ui.theme.Models.Student
import com.rhyttah.schwebsite.ui.theme.Screens.home.AddScreen
import com.rhyttah.schwebsite.ui.theme.Screens.home.HomeScreen
import com.rhyttah.schwebsite.ui.theme.Screens.home.ViewScreen
import com.rhyttah.schwebsite.ui.theme.Screens.login.LoginScreen
import com.rhyttah.schwebsite.ui.theme.Screens.register.RegisterScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN,
    studentList: MutableList<Student> = mutableListOf() // Assuming a default empty list for demonstration
) {
    NavHost(navController = navController, modifier = Modifier, startDestination = startDestination) {
        composable(ROUTE_LOGIN) {
            LoginScreen(navController = navController)
        }

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController = navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController = navController)
        }

        composable(ROUTE_ADD) {
            AddScreen(navController = navController, studentList = studentList)
        }

        composable(ROUTE_VIEW) {
            ViewScreen(navController = navController, studentList = studentList)
        }

        composable(ROUTE_UPDATE) {
            UpdateScreen(navController = navController, studentList = studentList)
        }
    }
}

