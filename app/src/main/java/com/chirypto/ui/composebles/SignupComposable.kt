package com.chirypto.ui.composebles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chirypto.R
import com.chirypto.model.User
import com.chirypto.utill.*

@Composable
fun displayNormalFieldText(txt: MutableState<String>, placeholder: String, label: String) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        value = txt.value,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFF1F6F9),
            unfocusedBorderColor = Color(0xFFF1F6F9),
            focusedBorderColor = Color(0xFFF1F6F9),
            focusedLabelColor = Color.Gray
        ),
        onValueChange = { txt.value = it },
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        label = {
            Text(text = label)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password // HERE
        ),
    )
}

@Composable
fun displayPasswordFieldText(
    txt: MutableState<String>,
    visibility: MutableState<Boolean>,
    placeholder: String,
    label: String
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        shape = RoundedCornerShape(8.dp),
        value = txt.value,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color(0xFFF1F6F9),
            unfocusedBorderColor = Color(0xFFF1F6F9),
            focusedBorderColor = Color(0xFFF1F6F9),
            focusedLabelColor = Color.Gray
        ),
        onValueChange = { txt.value = it },
        trailingIcon = {
            IconButton(onClick = { visibility.value = !visibility.value }) {
                Icon(
                    painter = painterResource(if (visibility.value) R.drawable.ic_visibility_off else R.drawable.ic_visibility_),
                    contentDescription = "show-password"
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        label = {
            Text(text = label)
        },
        visualTransformation = if (visibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password // HERE
        ),
    )
}

@Composable
fun displayRegisterBtn( onClick: (User) -> Unit) {

    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Button(modifier = Modifier
            .size(LocalConfiguration.current.screenWidthDp.div(1.5).dp, 50.dp)
            .align(Alignment.BottomCenter),
            onClick = {
                onClick.invoke(
                    User(
                        id = 100,
                        userName = "assHat",
                        email = "goodarzi@gamil.com",
                        avatar = "",
                        token = "12121212",
                        phone = "0935658",
                        verifiedDate = 15545
                    )
                )

            }) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                text = SIGNUP_REGISTER_BTN
            )

        }

    }
}

@Preview
@Composable
fun displaySignupHeader() {
    Row(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        displayAppLogoWithText()
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = LOGIN_TXT,
            modifier = Modifier.padding(5.dp),
            color = Color(0xFF177DFF),
            fontSize = 17.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}


@Composable
fun displayAppLogoWithText() {
    Icon(
        modifier = Modifier.size(48.dp, 32.dp),
        imageVector = ImageVector.vectorResource(R.drawable.ic_splash_icon),
        contentDescription = "",
        tint = Color(0xFF177DFF)
    )
    Text(
        modifier = Modifier
            .padding(5.dp),
        text = APP_NAME,
        color = Color(0xFF177DFF),
        fontSize = 19.sp, fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif
    )

}

@Composable
fun DisplaySignupIndicator() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create yor free account",
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        Text(
            text = "To get started with Chirypto, create your account",
            fontSize = 15.sp,
            color = Color.Gray
        )
    }
}