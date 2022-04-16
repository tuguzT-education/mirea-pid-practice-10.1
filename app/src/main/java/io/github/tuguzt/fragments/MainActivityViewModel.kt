package io.github.tuguzt.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainActivityViewModel(state: SavedStateHandle) : ViewModel() {
    private val savedStateHandle = state

    private val _startup : MutableLiveData<Boolean> =
        savedStateHandle.getLiveData<Boolean?>("startup").apply { value = true }
    val startup : LiveData<Boolean> get() = _startup

    fun getStartup() : Boolean {
        return savedStateHandle["startup"] ?: true
    }

    fun invertStartup() {
        savedStateHandle["startup"] = !getStartup()
    }
}
