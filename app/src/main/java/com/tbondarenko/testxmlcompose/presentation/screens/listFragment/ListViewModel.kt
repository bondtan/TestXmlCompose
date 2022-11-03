package com.tbondarenko.testxmlcompose.presentation.screens.listFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbondarenko.testxmlcompose.core.NetworkResult
import com.tbondarenko.testxmlcompose.data.remoteDataSource.EmojiNetworkEntity
import com.tbondarenko.testxmlcompose.domain.useCases.AllEmojiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val allEmojiUseCase: AllEmojiUseCase
) : ViewModel() {

    private val _result: MutableLiveData<NetworkResult<List<EmojiNetworkEntity>>> =
        MutableLiveData()
    val result: LiveData<NetworkResult<List<EmojiNetworkEntity>>> = _result

    fun getAllEmoji() = viewModelScope.launch {
        allEmojiUseCase().collect { values ->
            _result.value = values
        }
    }
}