package com.example.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repo.Repository
import com.example.myapplication.room.RoomDB
import com.example.myapplication.room.UserEntity
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository

    init {
        val userDAO = RoomDB.createDatabase(application).userDAO()
        repository = Repository(userDAO)
    }

    fun getUserList() : LiveData<List<UserEntity>> {
        return repository.getUserList()
    }

    fun addUser(user: UserEntity) {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
    /*
    fun replaceUser(fromPosition: Int, toPosition: Int, user: UserEntity) {
        viewModelScope.launch {
            repository.replaceUser(fromPosition, toPosition, user)
        }
    }

     */


}