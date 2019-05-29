package com.devarthur.easyshave.activity.RegisterActivities


import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast


import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.activity.User.MainMenuActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_email.*
import org.jetbrains.anko.*


class LoginEmailActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)


        initActions()
        debug()


    }

    private fun initActions() {

        btnSignIn.setOnClickListener { logWithUserData()}

        txtEsqueciSenha.setOnClickListener {startActivity<RecoverPassword>() }

        txtCadastro.setOnClickListener { startActivity<CreateAccount>() }

    }

    private fun debug(){
         edtUserNameSignUp.setText("arthur.gomes_4718@hotmail.com")
         edtUserPassword.setText("123456")
    }


    private fun logWithUserData() {

        val progressDialog = indeterminateProgressDialog ("Setting up acccount")


        val email = edtUserNameSignUp.text.toString()
        val pw = edtUserPassword.text.toString()


        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pw)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener

                //If success
                Log.d("Login", "User logged with uid: ${it.result?.user?.uid}")
                progressDialog.dismiss()
                startActivity(intentFor<MainMenuActivity>().newTask().clearTask())



            }.addOnFailureListener {
                Log.d("Login", "Failed to log user : ${it.message}")
                alert("Erro - Este usuário não existe").show()
                progressDialog.dismiss()
            }



    }


}
