package com.example.thanhnien

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thanhnien.screens.DiscoverScreen
import com.example.thanhnien.screens.LoginScreen
import com.example.thanhnien.screens.NewsScreen
import com.example.thanhnien.screens.ProfileScreen
import com.example.thanhnien.screens.RegisterScreen
import com.example.thanhnien.screens.Screen
import com.example.thanhnien.screens.ServiceScreen
import com.example.thanhnien.ui.theme.ThanhNienTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThanhNienTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "login"){
        composable("login"){
            LoginScreen(
                openRegister = {
                    navController.navigate("register")
                },
                openNewsScreen = { email, fullName ->
                    navController.navigate("news/$email/$fullName")
                }
            )
        }
        composable("register"){
            RegisterScreen(
                openLoginScreen = {
                    navController.popBackStack("login", inclusive = false, saveState = true)
                }
            )
        }
        composable("news/{email}/{fullName}",
            arguments = listOf(
                navArgument(name = "email") {
                    type = NavType.StringType
                },
                navArgument(name = "fullName") {
                    type = NavType.StringType
                }
            )
        ){navBackStackEntry ->
            var email = navBackStackEntry.arguments?.getString("email")
            var fullName = navBackStackEntry.arguments?.getString("fullName")
            requireNotNull(email)
            requireNotNull(fullName)
            NewsScreen(
                email = email,
                fullName = fullName,
                openDiscoverScreen = { email, fullname ->
                    navController.navigate("discover/$email/$fullName")
                },
                openProfileScreen = { email, fullName ->
                    navController.navigate("profile/$email/$fullName")
                },
                openServiceScreen = { email, fullName ->
                    navController.navigate("service/$email/$fullName")
                }
            )
        }
        composable("service/{email}/{fullName}",
            arguments = listOf(
                navArgument(name = "email") {
                    type = NavType.StringType
                },
                navArgument(name = "fullName") {
                    type = NavType.StringType
                }
            )
        ){ navBackStackEntry ->
            var email = navBackStackEntry.arguments?.getString("email")
            var fullName = navBackStackEntry.arguments?.getString("fullName")
            requireNotNull(email)
            requireNotNull(fullName)
            ServiceScreen(
                email = email,
                fullName = fullName,
                openDiscoverScreen = { email, fullname ->
                    navController.navigate("discover/$email/$fullName")
                },
                openProfileScreen = { email, fullname ->
                    navController.navigate("profile/$email/$fullName")
                },
                backNewsScreen = {
                    navController.popBackStack("news/{email}/{fullName}", inclusive = false, saveState = true)
                }
            )
        }
        composable("profile/{email}/{fullName}",
            arguments = listOf(
                navArgument(name = "email") {
                    type = NavType.StringType
                },
                navArgument(name = "fullName") {
                    type = NavType.StringType
                }
            )
        ){ navBackStackEntry ->
            var email = navBackStackEntry.arguments?.getString("email")
            var fullName = navBackStackEntry.arguments?.getString("fullName")
            requireNotNull(email)
            requireNotNull(fullName)
            ProfileScreen(
                email = email,
                fullName = fullName,
                openServiceScreen = { email, fullname ->
                    navController.navigate("service/$email/$fullName")
                },
                openDiscoverScreen = { email, fullname ->
                    navController.navigate("discover/$email/$fullName")
                },
                backNewsScreen = {
                    navController.popBackStack("news/{email}/{fullName}", inclusive = false, saveState = true)
                }
            )
        }
        composable("discover/{email}/{fullName}",
            arguments = listOf(
                navArgument(name = "email") {
                    type = NavType.StringType
                },
                navArgument(name = "fullName") {
                    type = NavType.StringType
                }
            )
        ){  navBackStackEntry ->
            var email = navBackStackEntry.arguments?.getString("email")
            var fullName = navBackStackEntry.arguments?.getString("fullName")
            requireNotNull(email)
            requireNotNull(fullName)
            DiscoverScreen(
                email = email,
                fullName = fullName,
                openServiceScreen = { email, fullname ->
                    navController.navigate("service/$email/$fullName")
                },
                openProfileScreen = { email, fullname ->
                    navController.navigate("profile/$email/$fullName")
                },
                backNewsScreen = {
                    navController.popBackStack("news/{email}/{fullName}", inclusive = false, saveState = true)
                })
        }
        composable("test"){
            Screen()
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ThanhNienTheme {
        HomeScreen()
    }
}