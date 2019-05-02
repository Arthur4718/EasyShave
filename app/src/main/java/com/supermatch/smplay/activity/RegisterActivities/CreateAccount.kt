package com.supermatch.smplay.activity.RegisterActivities


import android.os.Bundle
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccount : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initActions()
    }

    private fun initActions() {

        tvBackNavigation2.setOnClickListener { finish() }

    }
}
