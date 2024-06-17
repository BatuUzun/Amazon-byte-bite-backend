package com.chattingapp.foodrecipeuidemo.composables.navigationbar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun UserGeneratedRecipesScreen(navController: NavHostController) {
    Text(
        text = "User Generated Recipes"
    )
}