package com.tbondarenko.testxmlcompose.presentation.screens.detailFragment.compose

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.AbstractComposeView
import com.tbondarenko.testxmlcompose.presentation.models.EmojiUi

class DetailComposeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {

    private val emoji: MutableState<EmojiUi> =
        mutableStateOf(EmojiUi("","","",""))

    var selectEmoji: EmojiUi
        get() = emoji.value
        set(value) {emoji.value = value}

    @Composable
    override fun Content() {
        DetailScreen(emojiUi = emoji.value)
    }
}