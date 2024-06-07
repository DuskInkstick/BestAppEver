package com.vova.bestappever.screens.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextWithLabel(
    text: String,
    label: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    style: TextStyle = LocalTextStyle.current
) {
    Column(
        modifier = modifier
    ) {
        Text(text = label, color = Color(0.5f, 0.5f, 0.5f))
        Spacer(modifier = Modifier.padding(2.dp))
        Text(text = text, fontSize = fontSize, style = style)
    }
}