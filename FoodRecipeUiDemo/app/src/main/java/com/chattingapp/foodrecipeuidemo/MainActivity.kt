package com.chattingapp.foodrecipeuidemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chattingapp.foodrecipeuidemo.composables.authorizeuser.LoginPage
import com.chattingapp.foodrecipeuidemo.composables.authorizeuser.SignupPage
import com.chattingapp.foodrecipeuidemo.theme.FoodRecipeUiDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodRecipeUiDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //************************************** Home Page After Login ************************************************************
                    /*val navController = rememberNavController()

                    Scaffold(
                        bottomBar = { AppNavigationBar(navController = navController) }
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(innerPadding)
                        ) {
                            *//*composable("home") { HomeScreen(navController) }
                            composable("search") { SearchScreen(navController) }
                            composable("create recipe") { CreateRecipeScreen(navController) }
                            composable("user generated recipes") { UserGeneratedRecipesScreen(navController) }
                            composable("profile") { ProfileScreen(navController) }*//*

                            // Add more destinations as needed

                        }
                    }*/
                    //********************************************* Home Page After Login **********************************************************

                    LoginManager()

                }
            }
        }
    }
}
@Composable
fun LoginManager() {
    var showLogin by remember { mutableStateOf(true) }

    if (showLogin) {
        LoginPage(onSwitchToSignup = { showLogin = false })
    } else {
        SignupPage(onSwitchToLogin = { showLogin = true })
    }
}