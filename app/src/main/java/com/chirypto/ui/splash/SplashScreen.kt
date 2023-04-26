package com.chirypto.ui.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chirypto.R
import com.chirypto.viewModel.splash.SplashViewModel


@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = SplashViewModel()
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (viewModel.shouldDisplayUpdateApp(stringResource(id = R.string.app_version).toInt())) {
                SplashState.Error -> {}
                SplashState.UpdateDialog -> {
                    DisplayUpdateDialog(this)
                }

                SplashState.SignedUser -> {}
                else -> {
                    DisplaySplashScreen(navController)
                }
            }

            DisplayAppVersion(this)

        }
    }
}

@Composable
fun DisplayAppVersion(boxScope: BoxScope) {
    boxScope.apply {
        Text(
            text = stringResource(id = R.string.app_version),
            Modifier
                .align(Alignment.BottomCenter)
                .padding(10.dp)
        )
    }

}

@Composable
fun DisplayUpdateDialog(boxScope: BoxScope) {
    boxScope.apply {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {

            Text(
                text = stringResource(id = R.string.update_app),
                Modifier
                    .padding(10.dp)
            )
            Button(
                onClick = { }) {
                Text(text = "update Now!")
            }
        }
    }


}

@Composable
fun DisplaySplashScreen(navController: NavController) {

    Text(
        text = stringResource(id = R.string.splash_txt),
        Modifier
            .padding(10.dp)
    )
    Button(onClick = { navController.navigate("Login") }) {
        Text(text = "Continue")
    }
}

