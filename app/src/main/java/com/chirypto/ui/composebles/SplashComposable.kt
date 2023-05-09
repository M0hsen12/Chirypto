package com.chirypto.ui.composebles

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.chirypto.MainActivity
import com.chirypto.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DisplayNoInternet(boxScope: BoxScope) {
    boxScope.apply {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.no_internet)
            )
            Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { }) {
                Text(text = "Connect Now!")
            }

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

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DisplaySplashScreen(boxScope: BoxScope, navController: NavController) {
    boxScope.apply {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_splash_icon),
                contentDescription = "",
                tint = Color.Unspecified,
            )
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = stringResource(id = R.string.app_name_txt).uppercase(),
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = FontFamily.SansSerif
            )
//navigate to home or signup
//            CoroutineScope(Dispatchers.Main).launch {
//                delay(1000)
//                navController.navigate(MainActivity.SIGNUP_NAV_TAG)
//            }
        }
    }

}

@Composable
fun DisplayProgressbar(boxScope: BoxScope) {

    boxScope.apply {
        val showDialog = remember { mutableStateOf(true) }
        if (showDialog.value) {
            Dialog(
                onDismissRequest = { showDialog.value = false },
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .size(200.dp, 100.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .testTag(stringResource(id = R.string.splash_progressbar_tag))
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "please wait a bit...",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                }
            }

        }
    }
}




