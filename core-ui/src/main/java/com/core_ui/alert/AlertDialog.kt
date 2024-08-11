package com.core_ui.alert

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.core_ui.R

@Composable
fun AlertDialogWithButtons(
    onReload: () -> Unit
) {

    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = context.getString(R.string.error_title))
        },
        text = {
            Text(text = context.getString(R.string.error_description))
        },
        confirmButton = { },
        dismissButton = {
            Button(
                onClick = { onReload() }
            ) {
                Text(context.getString(R.string.reload))
            }
        }
    )
}

@Preview
@Composable
fun AlertDialogWithButtonsPreview() {
    AlertDialogWithButtons(
        onReload = {}
    )
}