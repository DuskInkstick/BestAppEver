package com.vova.bestappever.screens.authorization

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vova.bestappever.screens.utils.PhonePreview

@Composable
fun AuthorizationScreen(
    state: AuthorizationState,
    onLoginClick: () -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onEmailChanged: (email: String) -> Unit,
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val showPasswordIcon: ImageVector = if (passwordVisibility) {
        Icons.Filled.Lock
    } else {
        Icons.Outlined.Lock
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Авторизация", fontSize = 28.sp)
            Spacer(modifier = Modifier.padding(16.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = { onEmailChanged(it) },
                label = { Text(text = "Email") },
                isError = state.isError
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { onPasswordChanged(it) },
                label = { Text(text = "Пароль") },
                isError = state.isError,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Icon(
                            imageVector = showPasswordIcon,
                            contentDescription = "Видимость пароля"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.padding(16.dp))
            Button(onClick = onLoginClick) {
                Text(text = "Войти")
            }
            if(state.isError) {
                Toast.makeText(LocalContext.current, state.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@PhonePreview
@Composable
private fun Preview() {

}