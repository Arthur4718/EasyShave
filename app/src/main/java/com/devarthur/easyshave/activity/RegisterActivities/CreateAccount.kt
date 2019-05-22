package com.devarthur.easyshave.activity.RegisterActivities


import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.utils.Mask
import com.devarthur.easyshave.utils.ValidationsUtils
import kotlinx.android.synthetic.main.activity_create_account.*
import org.jetbrains.anko.toast

import com.devarthur.easyshave.utils.CPFUtil
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_complete_profile.*


class CreateAccount : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initActions()


    }

    private fun initActions() {

        tvBackNavigation2.setOnClickListener { finish() }


        btnSignIn.setOnClickListener {



        }




    }

    private fun signUpWithUserData() {

        if(ValidationsUtils.verifyCheckboxState(this, checkBox, "Aceite os Termos")){

            val validation = booleanArrayOf(


                ValidationsUtils.isPasswordConfirmation(edtUserSignUpPass1, edtUserSignUpPass2),
                ValidationsUtils.isFieldObrigatory(edtUserNameSignUp),
                ValidationsUtils.isFieldObrigatory(edtUserBirthDate),
                ValidationsUtils.isEmailValide(edtUserEmailSignUp),
                ValidationsUtils.isFieldObrigatory(edtUserEmailSignUp)

            )

            if (ValidationsUtils.verifyArrayObrigatory(validation)) {

                toast("Sending information.")

                val email = edtUserEmailSignUp.text.toString()
                val password = edtUserSignUpPass1.text.toString()


                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {

                        if(!it.isSuccessful) return@addOnCompleteListener

                        //if sucessful
                        Log.d("arthurdebug", "created new user : ")
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Falha ao conectar com o serviço. Por favor verifique sua internet", Toast.LENGTH_SHORT).show()
                    }
            }


        }
    }
}
