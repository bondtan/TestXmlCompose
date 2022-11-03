package com.tbondarenko.testxmlcompose.core

/**
 * For trying to decompose NetworkResult into ViewModel
 */
interface EventHandler<E> {
    fun obtainEvent(event: E)
}