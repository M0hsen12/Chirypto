package com.chirypto.ui.composebles

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chirypto.MainActivity
import com.chirypto.R

@Composable
fun displayNormalFieldText(txt: MutableState<String>, placeholder: String, label: String) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        value = txt.value,
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
fun displayRegisterBtn(columnScope: ColumnScope, navController: NavController) {
    columnScope.apply {
        Button(modifier = Modifier
            .size(LocalConfiguration.current.screenWidthDp.div(1.5).dp, 50.dp)
            .align(Alignment.CenterHorizontally),
            onClick = { navController.navigate(MainActivity.HOME_NAV_TAG) }) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.signup_btn)
            )

        }

    }

}