package com.vova.bestappever.screens.applications.created

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
fun NotAcceptedAppsScreen(
    state: ApplicationsState,
    onAppAccepted: (app: Application) -> Unit,
    modifier: Modifier = Modifier
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
        val app = state.apps[appIndexForDisplayDialog]

        CheckNotAcceptedApplicationDialog(
            app = app,
            onDismissRequest = { appIndexForDisplayDialog = -1 },
            onAppAccepted = {
                appIndexForDisplayDialog = -1
                onAppAccepted(app)
            },
            modifier = Modifier.fillMaxSize(0.9f)
        )
    }
}

@Composable
private fun CheckNotAcceptedApplicationDialog(
    app: Application,
    onDismissRequest: () -> Unit,
    onAppAccepted: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = modifier.clip(RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier.padding(12.dp, 0.dp)
            ) {
                ApplicationCard(app = app, modifier = Modifier.fillMaxHeight().weight(1f))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = onAppAccepted,
                    ) {
                        Text(text = "Принять")
                    }
                }
            }
        }
    }
}