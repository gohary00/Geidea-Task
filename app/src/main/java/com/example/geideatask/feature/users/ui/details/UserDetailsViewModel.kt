package com.example.geideatask.feature.users.ui.details

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
class UserDetailsViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

    private val _userLiveData = MutableLiveData<State<User>>()
    val userLiveData: LiveData<State<User>>
        get() = _userLiveData

    fun getUserDetails(userId: Int) {
        viewModelScope.launch {
            usersRepository.getUserDetails(userId).collect { usersState ->
                _userLiveData.value = usersState
            }
        }
    }
}