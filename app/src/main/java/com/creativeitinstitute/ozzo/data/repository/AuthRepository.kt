package com.creativeitinstitute.ozzo.data.repository

import com.creativeitinstitute.ozzo.data.models.UserRegistration
import com.creativeitinstitute.ozzo.data.services.AuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class AuthRepository : AuthService{
    override fun userRegistration(user: UserRegistration): Task<AuthResult> {

        val jAuth= FirebaseAuth.getInstance()

       return jAuth.createUserWithEmailAndPassword(user.email, user.password)

    }

    override fun userLogin() {

    }

    override fun createUser(user: UserRegistration) {

    }
}