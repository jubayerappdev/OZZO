package com.creativeitinstitute.ozzo.views.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.ozzo.core.DataState
import com.creativeitinstitute.ozzo.data.models.UserRegistration
import com.creativeitinstitute.ozzo.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authService : AuthRepository
) : ViewModel() {

   private val _registrationResponse = MutableLiveData<DataState<UserRegistration>>()
    val registrationResponse : LiveData<DataState<UserRegistration>> = _registrationResponse

    fun userRegistration(user: UserRegistration){

        _registrationResponse.postValue(DataState.Loading())

        authService.userRegistration(user).addOnSuccessListener {
            _registrationResponse.postValue(DataState.Success(user))
            Log.d("TAG", "userRegistration: Success")

        }.addOnFailureListener {error->
            _registrationResponse.postValue(DataState.Error("${error.message}"))
            Log.d("TAG", "userRegistration: ${error.message}")
        }
    }

}