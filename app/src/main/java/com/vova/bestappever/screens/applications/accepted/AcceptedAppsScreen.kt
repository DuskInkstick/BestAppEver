package com.vova.bestappever.screens.applications.accepted

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.vova.bestappever.data.models.Application
import com.vova.bestappever.screens.applications.models.ApplicationsState
import com.vova.bestappever.screens.applications.views.ApplicationCard
import com.vova.bestappever.screens.applications.views.ApplicationsListView

@Composable
fun AcceptedAppsScreen(
    state: ApplicationsState,
    onCompleteAppClick: (appIndex: Int) -> Unit,
    onCommentSend: (appIndex: Int, comment: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var appIndexForDisplayDialog by rememberSaveable {
        mutableIntStateOf(-1)
    }
    ApplicationsListView(
        title = state.title,
        apps = state.apps,
        onAppClick = { i, app -> appIndexForDisplayDialog = i },
        modifier = modifier
    )

    if (appIndexForDisplayDialog >= 0) {
        CheckAcceptedApplicationDialog(
            app = state.apps[appIndexForDisplayDialog],
            onDismissRequest = { appIndexForDisplayDialog = -1 },
            onCompleteAppClick = {
                onCompleteAppClick(appIndexForDisplayDialog)
                appIndexForDisplayDialog = -1
            },
            onCommentSend = { comment -> onCommentSend(appIndexForDisplayDialog, comment) },
            modifier = Modifier.fillMaxSize(0.9f)
        )
    }
}

@Composable
fun CheckAcceptedApplicationDialog(
    app: Application,
    onDismissRequest: () -> Unit,
    onCompleteAppClick: () -> Unit,
    onCommentSend: (comment: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        var comment by rememberSaveable {
            mutableStateOf("")
        }
        Surface(
            modifier = modifier.clip(RoundedCornerShape(8.dp))
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    ApplicationCard(app = app)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp, 0.dp)
                    ) {
                        TextField(
                            value = comment,
                            onValueChange = { s -> comment = s },
                            label = { Text(text = "Оставить комментарий") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            trailingIcon = {
                                IconButton(onClick = {
                                    if (comment != "") {
                                        onCommentSend(comment)
                                        comment = ""
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.Send,
                                        contentDescription = "Отправить"
                                    )
                                }
                            }
                        )

                    }
                }
                Button(
                    onClick = onCompleteAppClick,
                    modifier = Modifier
                        .padding(12.dp, 12.dp)
                        .width(150.dp)
                ) {
                    Text(text = "Выполнена")
                }
            }
        }
    }
}