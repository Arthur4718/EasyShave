package com.devarthur.easyshave.utils

import com.devarthur.easyshave.dataModel.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

object FireStoreUtil {
    private val firestoreInstance : FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    private val currentUserDocRef : DocumentReference
        get() = firestoreInstance.document("users/${FirebaseAuth.getInstance().uid
            ?: throw  NullPointerException("Uid is null")}")

    fun initCurrentUserIfFirstTime(onComplete: () -> Unit ){

        currentUserDocRef.get().addOnSuccessListener {documentSnapshot ->
            if (!documentSnapshot.exists()){
                val newUser = UserProfile(FirebaseAuth.getInstance().currentUser?.uid ?: "",
                    "",
                    FirebaseAuth.getInstance().currentUser?.email ?: "",
                    "",
                    ""
                    )
                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplete()
                }
            }else{
                onComplete()

            }

        }


    }

    fun updateCurrentUser(username : String = "", useremail : String = "", userbirthdate : String, userType : String){
        val userFieldMap = mutableMapOf<String, Any>()
        if(username.isNotBlank()) userFieldMap["username"] = username
        if(useremail.isNotBlank()) userFieldMap["useremail"] = useremail
        if(userbirthdate.isNotBlank()) userFieldMap["userbirthdate"] = userbirthdate
        if(userType.isNotBlank()) userFieldMap["userType"] = userType

        currentUserDocRef.update(userFieldMap)

    }

    fun getCurrentUser(onComplete: (UserProfile) -> Unit){
        currentUserDocRef.get()
            .addOnSuccessListener {
                onComplete(it.toObject(UserProfile::class.java)!!)
            }
    }
}