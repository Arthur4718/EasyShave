package com.supermatch.smplay.activity.RegisterActivities


import android.os.Bundle
import android.widget.Toast
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import kotlinx.android.synthetic.main.activity_select_login.*

class SelectLoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_login)


        btnEmailLogin.setOnClickListener {
            Toast.makeText(context, "BtnEmail clicked", Toast.LENGTH_SHORT).show()
        }

        btnFacebookLogin.setOnClickListener {
            Toast.makeText(context, "btnFacebook clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
