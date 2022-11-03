package com.tbondarenko.testxmlcompose.presentation.screens.utils

import android.text.Html

fun String.fromHtml() =
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
