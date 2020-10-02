package com.josancamon19.composemvvmretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josancamon19.composemvvmretrofit.data.User
import com.josancamon19.composemvvmretrofit.network.UsersAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _users : MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    init {
        setUsers()
    }

    private fun setUsers() {
        uiScope.launch {
            _users.value = UsersAPI.retrofitService.getUsers()
        }
    }
}