package com.supermatch.smplay.activity.User


import android.os.Bundle
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity


//This is the main activity that will load a fragment for each navigation in the bottom nav menu
class UserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }
}
