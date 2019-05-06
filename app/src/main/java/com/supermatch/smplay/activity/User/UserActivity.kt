package com.supermatch.smplay.activity.User


import android.os.Bundle
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import com.supermatch.smplay.extensions.addFragment
import com.supermatch.smplay.fragments.PlayFragment
import kotlinx.android.synthetic.main.activity_user.*


//This is the main activity that will load a fragment for each navigation in the bottom nav menu
class UserActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        if(savedInstanceState == null){
            //Adds fragment to the layout
            addFragment(R.id.layout_container, PlayFragment())
        }


    }
}
