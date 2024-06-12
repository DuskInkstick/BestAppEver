package com.vova.bestappever.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vova.bestappever.data.models.User
import com.vova.bestappever.data.repository.UserRepository
import com.vova.bestappever.screens.utils.PhonePreview

@Composable
fun ProfileScreen(
    state: ProfileState,
    onLogout: () -> Unit
) {
    val user = state.user
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = user.fio, fontSize = 21.sp, fontWeight = FontWeight(620))

            IconButton(onClick = onLogout) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = "Выйти из профиля"
                )
            }
        }
        HorizontalDivider()
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            "Личная информация",
            fontSize = 16.sp,
            fontWeight = FontWeight(620),
            fontStyle = FontStyle.Italic
        )
        Column(
            modifier = Modifier.padding(32.dp, 12.dp, 0.dp, 0.dp)
        ) {
            TextWithIcon(
                icon = Icons.Filled.Phone,
                label = "Номер:",
                text = user.phoneNumber,
                modifier = Modifier.padding(4.dp)
            )
            TextWithIcon(
                icon = Icons.Filled.Email,
                label = "Почта:",
                text = user.email,
                modifier = Modifier.padding(4.dp)
            )
        }
        HorizontalDivider(modifier = Modifier.padding(0.dp, 12.dp))
        Text(
            "Дополнительная информация",
            fontSize = 16.sp,
            fontWeight = FontWeight(620),
            fontStyle = FontStyle.Italic
        )
        Column(
            modifier = Modifier.padding(32.dp, 12.dp, 0.dp, 0.dp)
        ) {
            TextWithIcon(
                icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                label = "Организация:",
                text = user.organization,
                modifier = Modifier.padding(4.dp)
            )
            TextWithIcon(
                icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                label = "Филиал:",
                text = user.organizationBranch,
                modifier = Modifier.padding(4.dp)
            )
            TextWithIcon(
                icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                label = "Магазин:",
                text = user.shop,
                modifier = Modifier.padding(4.dp)
            )
            TextWithIcon(
                icon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                label = "Подразделение:",
                text = user.organizationSubdivision,
                modifier = Modifier.padding(4.dp)
            )
        }
        HorizontalDivider(modifier = Modifier.padding(0.dp, 12.dp))
    }
}

@Composable
private fun TextWithIcon(
    icon: ImageVector,
    label: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(imageVector = icon, contentDescription = label)
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = label, fontWeight = FontWeight(620))
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = text)
    }
}

@PhonePreview
@Composable
private fun Preview() {

}

