package com.vova.bestappever.screens.applications.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.repository.ApplicationRepository
import com.vova.bestappever.screens.utils.PhonePreview
import com.vova.bestappever.screens.utils.TextWithLabel

@Composable
fun ApplicationBriefCard(
    modifier: Modifier = Modifier,
    app: Application,
    onClick: (Application) -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        onClick = { onClick(app) }
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
               // horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    TextWithLabel(
                        text = app.dateOfCreation,
                        label = "Дата создания",
                        fontSize = 18.sp,
                        style = LocalTextStyle.current.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight(500),
                        )
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    TextWithLabel(text = app.directionOfWork, label = "Направление работ")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextWithLabel(
                        text = app.dateOfCompletion ?: "-",
                        label = "Выполнить до",
                        fontSize = 18.sp,
                        style = LocalTextStyle.current.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight(500)
                        )
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    TextWithLabel(text = app.typeOfRepairWork.jsonValue, label = "Вид  работ")
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
            TextWithLabel(text = app.description, label = "Описание")
         }
    }
}

@PhonePreview
@Composable
private fun Preview() {
    /*val app =
    ApplicationBriefCard(app = app, onClick =  { })*/
}

