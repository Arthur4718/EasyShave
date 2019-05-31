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

    //Armazenar um usuário na tabela users.
    fun initCurrentUserIfFirstTime( username : String, useremail : String, userbirth : String, usertype : String, onComplete: () -> Unit ){

        currentUserDocRef.get().addOnSuccessListener {documentSnapshot ->
            if (!documentSnapshot.exists()){
                val newUser = UserProfile(FirebaseAuth.getInstance().currentUser?.uid ?: "",
                    username,
                    useremail,
                    userbirth,
                    usertype
                    )
                currentUserDocRef.set(newUser).addOnSuccessListener {
                    onComplete()
                }
            }else{
                onComplete()

            }

        }


    }

    //Atualizar um usuário na tabela users
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