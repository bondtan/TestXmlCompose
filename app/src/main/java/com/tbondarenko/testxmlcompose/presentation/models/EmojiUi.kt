package com.tbondarenko.testxmlcompose.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmojiUi(
    val name: String,
    val category: String,
    val group: String,
    val html: String
): Parcelable
