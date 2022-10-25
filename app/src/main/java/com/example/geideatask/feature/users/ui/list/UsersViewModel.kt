package com.example.geideatask.feature.users.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geideatask.feature.users.data.database.User
import com.example.geideatask.feature.users.data.models.shared.State
import com.example.geideatask.feature.users.data.repos.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

    private val _usersLiveData = MutableLiveData<State<List<User>>>()
    val usersLiveData: LiveData<State<List<User>>>
        get() = _usersLiveData

    init {
        getAllUsers()
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            usersRepository.getAllUsers().collect { usersState ->
                _usersLiveData.value = usersState
            }
        }
    }
}