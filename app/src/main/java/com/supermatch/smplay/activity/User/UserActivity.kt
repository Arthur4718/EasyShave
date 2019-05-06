package com.supermatch.smplay.activity.User


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import com.supermatch.smplay.extensions.addFragment
import com.supermatch.smplay.fragments.PlayFragment
import com.supermatch.smplay.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_user.*

import org.jetbrains.anko.toast


//This is the main activity that will load a fragment for each navigation in the bottom nav menu
class UserActivity : BaseActivity() {

    private val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->

        when(item.itemId){
            R.id.id_nav_item_play -> {
                toast("play menu pressed")
                return@OnNavigationItemSelectedListener true
            }

            R.id.id_nav_item_profile -> {
                toast("profile menu pressed")
                return@OnNavigationItemSelectedListener true
            }

            R.id.id_nav_item_bank -> {
                toast("bank menu pressed")
                return@OnNavigationItemSelectedListener true
            }
            R.id.id_nav_item_tutorials -> {
                toast("tutorials menu pressed")
                return@OnNavigationItemSelectedListener true
            }

        }
            false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        initVars()


        //Loads the first page
        if(savedInstanceState == null){
            //Adds fragment to the layout
            addFragment(R.id.layout_container, PlayFragment())
            bottomNavBar.selectedItemId = R.id.id_nav_item_play
        }


    }

    private fun initVars() {

        //Setting up bottom navigation listener
        bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)

    }
}
