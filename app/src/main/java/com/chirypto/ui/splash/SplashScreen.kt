package com.chirypto.ui.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chirypto.R
import com.chirypto.viewModel.splash.SplashViewModel


@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = SplashViewModel()
    Column(modifier = Modifier.fillMaxSize()) {

        when (viewModel.shouldDisplayUpdateApp(stringResource(id = R.string.app_version).toInt())) {
            SplashState.Error -> {}
            SplashState.UpdateDialog -> {
                DisplayUpdateDialog()
            }

            SplashState.SignedUser -> {}
            else -> {
                DisplaySplashScreen(navController)
            }
        }

        DisplayAppVersion()

    }
}

@Composable
fun DisplayAppVersion() {

        Text(
            text = stringResource(id = R.string.app_version),
            Modifier
                .padding(10.dp)
        )

}

@Composable
fun DisplayUpdateDialog() {

        Text(
            text = stringResource(id = R.string.update_app),
            Modifier
//                .align(Alignment.CenterStart)
                .padding(10.dp)
        )
        Button(
//            modifier = Modifier.align(Alignment.Center),
            onClick = { }) {
            Text(text = "update Now!")
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

