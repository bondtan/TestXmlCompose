package com.tbondarenko.testxmlcompose.presentation.screens.detailFragment.compose.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.tbondarenko.testxmlcompose.presentation.screens.utils.fromHtml

@Composable
fun EmojiTextView (
    modifier: Modifier = Modifier,
    emojiHtml: String
){
    Text(
        modifier= Modifier
            .fillMaxWidth(),
        text = emojiHtml.fromHtml(),
        fontSize = 200.sp,
        textAlign = TextAlign.Center
    )
}