package com.creativeitinstitute.ozzo.data.repository

import android.net.Uri
import com.creativeitinstitute.ozzo.core.Nodes
import com.creativeitinstitute.ozzo.data.models.Product
import com.creativeitinstitute.ozzo.data.models.UserLogin
import com.creativeitinstitute.ozzo.data.models.UserRegistration
import com.creativeitinstitute.ozzo.data.services.AuthService
import com.creativeitinstitute.ozzo.data.services.SellerService
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.installations.time.SystemClock
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class SellerRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val storageRef: StorageReference
) : SellerService{
    override fun uploadProductImage(productImageUri: Uri): UploadTask {

        val storage: StorageReference = storageRef.child("product").child("PRD_${System.currentTimeMillis()}")
        return storage.putFile(productImageUri)
    }

    override fun uploadProduct(product: Product): Task<Void>  {

        return  db.collection(Nodes.PRODUCT).document(product.productID).set(product)
    }

    override fun getAllProductByUserID(userID: String): Task<QuerySnapshot> {

      return db.collection(Nodes.PRODUCT).whereEqualTo("sellerID", userID).get()
    }

}