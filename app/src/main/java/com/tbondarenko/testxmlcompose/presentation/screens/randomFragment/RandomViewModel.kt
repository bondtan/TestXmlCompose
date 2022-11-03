package com.tbondarenko.testxmlcompose.presentation.screens.randomFragment

import androidx.lifecycle.*
import com.tbondarenko.testxmlcompose.core.NetworkResult
import com.tbondarenko.testxmlcompose.data.remoteDataSource.EmojiNetworkEntity
import com.tbondarenko.testxmlcompose.domain.useCases.RandomEmojiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val randomEmojiUseCase: RandomEmojiUseCase
) : ViewModel() {

    private val _result:MutableLiveData<NetworkResult<EmojiNetworkEntity>> = MutableLiveData()
    val result: LiveData<NetworkResult<EmojiNetworkEntity>> = _result

    fun getRandomEmoji() = viewModelScope.launch {
        randomEmojiUseCase().collect{ values ->
            _result.value = values
        }
    }
}



/**
 *  Trying to decompose NetworkResult into ViewModel
 */
/*
private var _viewState = MutableLiveData(RandomEmojiUiState())
val viewState: LiveData<RandomEmojiUiState> = _viewState

private var _errorUi = MutableLiveData(ErrorUi())
val errorUi: LiveData<ErrorUi> = _errorUi

private var _isError = MutableLiveData(false)
val isError: LiveData<Boolean> = _isError*/

/*
override fun obtainEvent(event: RandomEmojiEvent) {
     when (event) {
         RandomEmojiEvent.ShowRandomEmoji -> //loadRandomEmoji()
         RandomEmojiEvent.NextRandomButtonClick -> //loadRandomEmoji()
         RandomEmojiEvent.TryAgain -> tryAgainLoadRandomEmoji()
         //RandomEmojiEvent.ShownErrorMessage -> errorMessageShown()
     }
}
*/

/*fun loadRandomEmoji() = viewModelScope.launch(ioDispatcher) {
    _viewState.postValue(_viewState.value?.copy(isLoading = true))
    _isError.postValue(false)
    val response = randomEmojiUseCase()
    //withContext(mainDispatcher) {
        when (response) {
            is NetworkResult.Loading -> {
                _viewState.postValue(_viewState.value?.copy(isLoading = true))
                _isError.postValue(false)
            }
            is NetworkResult.Success -> { _viewState.postValue(
                    _viewState.value?.copy(
                        isLoading = false,
                        name = response.data.name,
                        category = response.data.category,
                        group = response.data.group,
                        html = response.data.htmlCode.first(),
                    )
                )
                _isError.postValue(false)
            }
            is NetworkResult.Error -> {
                _viewState.postValue(_viewState.value?.copy(isLoading = false))
                _isError.postValue(true)
                Log.d("RandVM", "${response.message}")
                _errorUi.postValue(
                    _errorUi.value?.copy(
                        statusCode = response.statusCode,
                        message = response.message,
                        error = response.error
                    )
                )
                Log.d("RandVM1", "${errorUi.value?.message}")
            }
        }
    //}
}
*/
/*
private fun tryAgainLoadRandomEmoji() {
    _viewState.postValue(
        _viewState.value?.copy(isLoading = true
        )
    )
    _isError.postValue(false)
    _errorUi.postValue(
        _errorUi.value?.copy(
            statusCode = 0,
            message = "",
            error = ""
        )
    )
    //loadRandomEmoji()
}*/
