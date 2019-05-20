package com.devarthur.easyshave.activity.RegisterActivities


import android.os.Bundle
import android.widget.Toast
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.activity.User.UserActivity
import com.devarthur.easyshave.utils.ValidationsUtils
import kotlinx.android.synthetic.main.activity_login_email.*
import org.jetbrains.anko.startActivity

class LoginEmailActivity : BaseActivity() {

    val standardEmail = "admin@admin.com"
    val standardPassword = "123456"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)


        initActions()


    }

    private fun initActions() {

        tvBackNavigation.setOnClickListener { finish() }

        btnLoginWithEmail.setOnClickListener {  logWithUserData() }

        tvRecoverAccount.setOnClickListener { startActivity<RecoverPassword>()}

        //If the user needs to login right after sign in up - Consider using Start Activity for result
        //Then, use the e-mail and login as parameters so the Login activity can do its job. - Its saves time and code.
        tvCreateAccount.setOnClickListener { startActivity<CreateAccount>() }

        edtLoginEmail.setText(standardEmail)
        edtLoginPassword.setText(standardPassword)


    }


    private fun logWithUserData() {
        if (ValidationsUtils.verifyLoginAccount(edtLoginEmail, edtLoginPassword)) {

            val userLogin = edtLoginEmail.text.toString()
            val userPassword = edtLoginPassword.text.toString()

            Toast.makeText(context, "Verifying Account...", Toast.LENGTH_SHORT).show()

            if(userLogin == standardEmail && userPassword == standardPassword){

                startActivity<UserActivity>()

                finish()
            }



        }
    }


}
