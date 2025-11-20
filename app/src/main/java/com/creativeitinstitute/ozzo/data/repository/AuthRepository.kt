package com.creativeitinstitute.ozzo.data.repository

import com.creativeitinstitute.ozzo.core.Nodes
import com.creativeitinstitute.ozzo.data.models.UserLogin
import com.creativeitinstitute.ozzo.data.models.UserRegistration
import com.creativeitinstitute.ozzo.data.services.AuthService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val jAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthService{
    override fun userRegistration(user: UserRegistration): Task<AuthResult> {

       return jAuth.createUserWithEmailAndPassword(user.email, user.password)

    }

    override fun userLogin(user: UserLogin) : Task<AuthResult> {

        return jAuth.signInWithEmailAndPassword(user.email, user.password)

    }

    override fun createUser(user: UserRegistration): Task<Void> {

     return  db.collection(Nodes.USER).document(user.userID).set(user)
    }
}