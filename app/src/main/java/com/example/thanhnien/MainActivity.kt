package com.example.thanhnien

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thanhnien.ui.theme.ThanhNienTheme
import kotlinx.coroutines.flow.callbackFlow

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