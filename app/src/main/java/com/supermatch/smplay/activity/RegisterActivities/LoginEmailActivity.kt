package com.supermatch.smplay.activity.RegisterActivities


import android.os.Bundle
import android.widget.Toast
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import com.supermatch.smplay.utils.ValidationsUtils
import kotlinx.android.synthetic.main.activity_login_email.*

class LoginEmailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)


        initActions()


    }

    private fun initActions() {

        tvBackNavigation.setOnClickListener { finish() }

        btnLoginWithEmail.setOnClickListener {  logWithUserData() }


        tvRecoverAccount.setOnClickListener {
            Toast.makeText(context, "Recover Account", Toast.LENGTH_SHORT).show()
        }

        tvCreateAccount.setOnClickListener {
            Toast.makeText(context, "Create Account", Toast.LENGTH_SHORT).show()
        }


    }

    private fun logWithUserData() {
        if (ValidationsUtils.verifyLoginAccount(edtLoginEmail, edtLoginPassword)) {

            val userLogin = edtLoginEmail.text.toString()
            val userPassword = edtLoginPassword.text.toString()

            Toast.makeText(context, "Verifying Account...", Toast.LENGTH_SHORT).show()

        }
    }


}
