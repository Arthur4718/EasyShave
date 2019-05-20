package com.devarthur.easyshave.activity.RegisterActivities


import android.os.Bundle

import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import kotlinx.android.synthetic.main.activity_login_email.*
import org.jetbrains.anko.startActivity


class LoginEmailActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)


        initActions()


    }

    private fun initActions() {

        btnSignIn.setOnClickListener {
            startActivity<CreateAccount>()
        }

    }


    private fun logWithUserData() {



    }


}
