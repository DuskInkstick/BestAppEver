package com.vova.bestappever.screens.applications.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.data.repository.ApplicationRepository
import com.vova.bestappever.screens.utils.PhonePreview
import com.vova.bestappever.screens.utils.TextWithLabel
import com.vova.bestappever.ui.theme.BestAppEverTheme
import com.vova.bestappever.ui.theme.Purple40
import com.vova.bestappever.ui.theme.Purple80
import com.vova.bestappever.ui.theme.PurpleGrey40

@Composable
fun ApplicationCard(
    modifier: Modifier = Modifier,
    app: Application
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            TextWithLabel(text = app.description, label = "Описание")
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f)
                ) {
                    TextWithLabel(text = app.dateOfCreation, label = "Опубликовано")
                    Spacer(modifier = Modifier.padding(8.dp))

                    TextWithLabel(text = app.directionOfWork, label = "Направление работ")
                    Spacer(modifier = Modifier.padding(8.dp))

                    TextWithLabel(text = app.criticality.jsonValue, label = "Критичность")
                    Spacer(modifier = Modifier.padding(8.dp))

                    TextWithLabel(text = app.expenseApprovalStatus, label = "Согласование расходов")

                }
                Spacer(modifier = Modifier.padding(8.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextWithLabel(text = app.dateOfCompletion, label = "Выполнить до")
                    Spacer(modifier = Modifier.padding(8.dp))

                    TextWithLabel(text = app.typeOfRepairWork.jsonValue, label = "Вид  работ")
                    Spacer(modifier = Modifier.padding(8.dp))

                    TextWithLabel(text = app.causeOfFailure, label = "Причина")
                    Spacer(modifier = Modifier.padding(8.dp))

                    TextWithLabel(text = "Пока пусто", label = "Место")
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(8.dp, 8.dp, 8.dp, 0.dp)

            ) {
                app.comments.forEach {
                    TextWithLabel(text = it.comment, label = it.userEmail)
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@PhonePreview
@Composable
private fun Preview() {
    ApplicationCard(app = ApplicationRepository().getAll()[0])
}