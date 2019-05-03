package com.supermatch.smplay.activity.RegisterActivities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.supermatch.smplay.R
import kotlinx.android.synthetic.main.activity_complete_profile.*
import org.jetbrains.anko.toast


//This activity is used to recover aditional information about the user when he sign up with facebook.
class CompleteProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_profile)

        initActions()
    }

    private fun initActions() {

        tvBackNavigation4.setOnClickListener { finish() }

        btnCompleteProfile.setOnClickListener { toast("Registering....") }
    }
}
