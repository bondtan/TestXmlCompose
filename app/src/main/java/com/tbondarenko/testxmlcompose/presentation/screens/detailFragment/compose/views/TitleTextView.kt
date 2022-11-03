package com.tbondarenko.testxmlcompose.presentation.screens.detailFragment.compose.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleTextView(
    modifier: Modifier = Modifier,
    title: String,
    paddingBottom: Dp = 16.dp
) {
    Text(
        modifier = modifier
            .padding(bottom = paddingBottom),
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    )
}
