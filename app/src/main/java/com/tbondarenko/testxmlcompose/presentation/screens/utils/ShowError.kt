package com.tbondarenko.testxmlcompose.presentation.screens.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tbondarenko.testxmlcompose.R

fun showErrorInFragment(
    context: Context,
    title: String,
    message: String,
    setViewVisibility:() -> Unit,
    tryAgain: () -> Unit
): AlertDialog {
  return  MaterialAlertDialogBuilder(context)
        .setCancelable(false)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(context.getString(R.string.dialog_cancel)) { dialog, _ ->
            setViewVisibility()
            dialog.dismiss()
        }
        .setPositiveButton(context.getString(R.string.dialog_try_again)) { dialog, _ ->
            tryAgain()
            dialog.dismiss()
        }
        .show()
}

