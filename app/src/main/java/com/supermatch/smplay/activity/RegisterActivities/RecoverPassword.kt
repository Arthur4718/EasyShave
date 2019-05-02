package com.supermatch.smplay.activity.RegisterActivities


import android.os.Bundle
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import kotlinx.android.synthetic.main.activity_recover_password.*
import org.jetbrains.anko.toast

class RecoverPassword : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)


        initActions()
    }

    private fun initActions() {
        tvBackNavication3.setOnClickListener { finish() }

        btnRecoverPassword.setOnClickListener { toast("Check your e-mail for details") }
    }
}
