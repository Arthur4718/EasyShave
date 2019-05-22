package com.devarthur.easyshave.activity.RegisterActivities


import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.fragments.DatePickerFragment
import com.devarthur.easyshave.utils.Mask
import com.devarthur.easyshave.utils.ValidationsUtils
import kotlinx.android.synthetic.main.activity_create_account.*
import org.jetbrains.anko.toast

import com.devarthur.easyshave.utils.CPFUtil
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_complete_profile.*
import java.util.*


class CreateAccount : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initActions()


    }

    private fun initActions() {

        tvBackNavigation2.setOnClickListener { finish() }


        btnSignIn.setOnClickListener {

            signUpWithUserData()

        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        edtUserBirthDate.setOnClickListener {



            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->

                edtUserBirthDate.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year)
            }, year, month, day)

            datePicker.show()

        }



    }

    fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun signUpWithUserData() {

        if(edtUserSignUpPass1.length() < 6){

            toast("Coloque uma senha de pelo menos 6 dígitos ou mais. ")
            return

        }

        if(edtUserSignUpPass2.length() < 6){

            toast("Coloque uma senha de pelo menos 6 dígitos ou mais. ")
            return

        }

        if(edtUserSignUpPass1.text != edtUserSignUpPass2.text){
            toast("As senhas não batem")
            return
        }

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
                        Log.d("arthurdebug", "Usuário criado com sucesso : ")
                        toast("Usuário criado com sucesso. ")
                        finish()
                    }
                    .addOnFailureListener {
                        toast("Falha ao conectar com o serviço : ${it.message}")
                        Log.d("Login", "Failed to log user : ${it.message}")
                    }
            }


        }
    }
}
