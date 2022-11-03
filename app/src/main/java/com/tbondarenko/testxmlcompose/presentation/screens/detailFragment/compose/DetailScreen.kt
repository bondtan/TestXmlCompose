package com.tbondarenko.testxmlcompose.presentation.screens.detailFragment.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tbondarenko.testxmlcompose.presentation.models.EmojiUi
import com.tbondarenko.testxmlcompose.presentation.screens.detailFragment.compose.views.EmojiTextView
import com.tbondarenko.testxmlcompose.presentation.screens.detailFragment.compose.views.TitleTextView

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    emojiUi: EmojiUi = EmojiUi(
        name = "hugging face",
        category = "smileys and people",
        group = "face positive",
        html = "&#129303;"
    ),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleTextView(
                title = buildString {
                    append("Category: ")
                    append(emojiUi.category)
                })
            TitleTextView(
                title = buildString {
                    append("Group: ")
                    append(emojiUi.group)
                })
            Divider(
                modifier = Modifier,
                color = Color.LightGray,
                thickness = 2.dp,
            )
        }
        EmojiTextView(
            emojiHtml = emojiUi.html
        )
        TitleTextView(
            title = emojiUi.name.uppercase(),
            paddingBottom = 32.dp
        )
    }
}


