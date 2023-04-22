package com.chirypto.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Login!")
        Button(onClick = { navController.navigate("Home") }) {
            Text(text = "Continue")
        }
    }
}