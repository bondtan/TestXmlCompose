package com.tbondarenko.testxmlcompose.presentation.screens.randomFragment.state

/**
 * For trying to decompose NetworkResult into ViewModel
 */
data class RandomEmojiUiState(
    val isLoading: Boolean = false,
    //val isError: Boolean = false,
    //val isException: Boolean = false,
    val name: String = "",
    val category: String = "",
    val group: String = "",
    val html: String = "",
)

data class ErrorUi(
    val statusCode: Int = 0,
    val message: String = "",
    val error: String = "",
   // val isError: Boolean = false
)
