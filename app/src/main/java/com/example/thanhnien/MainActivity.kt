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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                openNewsScreen = {
                    navController.navigate("news")
                }
            )
        }
        composable("register"){
            RegisterScreen(
                openLoginScreen = {
                    navController.navigate("login")
                }
            )
        }
        composable("news"){
            NewsScreen(
                openDiscoverScreen = {
                    navController.navigate("discover")
                },
                openProfileScreen = {
                    navController.navigate("profile")
                },
                openServiceScreen = {
                    navController.navigate("service")
                }
            )
        }
        composable("service"){
            ServiceScreen(
                openDiscoverScreen = {
                    navController.navigate("discover")
                },
                openProfileScreen = {
                    navController.navigate("profile")
                },
                openNewsScreen = {
                    navController.navigate("news")
                }
            )
        }
        composable("profile"){
            ProfileScreen(
                openServiceScreen = {
                    navController.navigate("service")
                },
                openDiscoverScreen = {
                    navController.navigate("discover")
                },
                openNewsScreen = {
                    navController.navigate("news")
                }
            )
        }
        composable("discover"){
            DiscoverScreen(
                openServiceScreen = {
                    navController.navigate("service")
                },
                openProfileScreen = {
                    navController.navigate("profile")
                },
                openNewsScreen = {
                    navController.navigate("news")
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