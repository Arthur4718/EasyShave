package com.supermatch.smplay.activity.RegisterActivities


import android.os.Bundle
import android.widget.Toast
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import kotlinx.android.synthetic.main.activity_login_email.*

class LoginEmailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)



        tvBackNavigation.setOnClickListener {
            Toast.makeText(context, "Up Navigation", Toast.LENGTH_SHORT).show()
        }

        tvRecoverAccount.setOnClickListener {
            Toast.makeText(context, "Recover Account", Toast.LENGTH_SHORT).show()
        }

        tvCreateAccount.setOnClickListener {
            Toast.makeText(context, "Recover Account", Toast.LENGTH_SHORT).show()
        }
    }
}
