package com.supermatch.smplay.activity.RegisterActivities

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.facebook.CallbackManager
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import kotlinx.android.synthetic.main.activity_select_login.*
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback


import com.facebook.login.widget.LoginButton
import java.util.*
import android.content.Intent
import com.facebook.login.LoginManager








class SelectLoginActivity : BaseActivity() {

    var loginButton : LoginButton? = null
    var callbackManager : CallbackManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_login)




        btnEmailLogin.setOnClickListener {
            Toast.makeText(context, "BtnEmail clicked", Toast.LENGTH_SHORT).show()
        }


        FacebookSdk.sdkInitialize(this.applicationContext)

        callbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Log.d("Success", "Login")

                }

                override fun onCancel() {
                    Toast.makeText(this@SelectLoginActivity, "Login Cancel", Toast.LENGTH_LONG).show()
                }

                override fun onError(exception: FacebookException) {
                    Toast.makeText(this@SelectLoginActivity, exception.message, Toast.LENGTH_LONG).show()
                }
            })

        btnFacebookLogin.setOnClickListener {
            Toast.makeText(context, "btnFacebook clicked", Toast.LENGTH_SHORT).show()
            LoginManager.getInstance().logInWithReadPermissions(this@SelectLoginActivity, Arrays.asList("public_profile", "user_friends"));
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
