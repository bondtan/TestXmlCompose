package com.tbondarenko.testxmlcompose.presentation.screens.randomFragment.state

/**
 * For trying to decompose NetworkResult into ViewModel
 */
sealed class RandomEmojiEvent{
    object ShowRandomEmoji: RandomEmojiEvent()
    object NextRandomButtonClick: RandomEmojiEvent()
    object TryAgain: RandomEmojiEvent()
}
