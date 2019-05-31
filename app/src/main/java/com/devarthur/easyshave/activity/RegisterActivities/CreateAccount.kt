package com.devarthur.easyshave.activity.RegisterActivities


import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.extensions.toast
import com.devarthur.easyshave.fragments.DatePickerFragment
import com.devarthur.easyshave.utils.FireStoreUtil
import com.devarthur.easyshave.utils.ValidationsUtils
import kotlinx.android.synthetic.main.activity_create_account.*
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.indeterminateProgressDialog



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

        if(!edtUserSignUpPass1.text.toString().equals(edtUserSignUpPass2.text.toString())){
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
                val progressDialog = indeterminateProgressDialog ("Carregando...")

                val email = edtUserEmailSignUp.text.toString()
                val password = edtUserSignUpPass1.text.toString()


                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {

                        if(!it.isSuccessful) return@addOnCompleteListener

                        //if sucessful
                        Log.d("arthurdebug", "Usuário criado com sucesso : ")

                        saveUserToFirebase(edtUserNameSignUp.text.toString(),
                                           edtUserEmailSignUp.text.toString(),
                                           edtUserBirthDate.text.toString()


                            )

                    }
                    .addOnFailureListener {
                        toast("Falha ao conectar com o serviço : ${it.message}")
                        Log.d("Login", "Failed to log user : ${it.message}")
                    }
                progressDialog.dismiss()
            }
        }
    }

    private fun saveUserToFirebase(userName: String, userEmail: String, userBirthDate: String) {

        //Verifica se o usuário é um estabelecimento ou usuário comum.
        //Se for um estabelecimento, vamos add a tabela estabelecimentos no firebase

        val tipoUsuario = switch1.isChecked
        var userType : String = ""

        if(tipoUsuario){
            //Estabelecimento
            userType = "1"

        }else{
            //Usuário comum
            userType = "0"
        }

        FireStoreUtil.initCurrentUserIfFirstTime(userName, userEmail, userBirthDate, userType) {

            toast("User data stored")
            finish()

        }

//        val uid  = FirebaseAuth.getInstance().uid ?: ""
//        val databaseRef = FirebaseDatabase.getInstance().getReference("users/$uid")
//

//
//
//        //Criar um objeto user com os dados salvos neste formulario
//
//        val createdUser  = UserProfile(uid, userName, userEmail, userBirthDate, userType)
//
//        //Salva os dados no database.
//
//        databaseRef.setValue(createdUser)
//            .addOnSuccessListener {
//
//                toast("Usuário criado com sucesso. ")
//                finish()
//            }.addOnFailureListener {
//                toast("Falha ao enviar: ${it.message}")
//            }


    }

}


