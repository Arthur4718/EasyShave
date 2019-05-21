package com.devarthur.easyshave.activity.User


import android.os.Bundle
import android.support.design.widget.BottomNavigationView

import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.extensions.addFragment
import com.devarthur.easyshave.extensions.replaceFragment
import com.devarthur.easyshave.fragments.BankFragment
import com.devarthur.easyshave.fragments.PlayFragment
import com.devarthur.easyshave.fragments.ProfileFragment
import kotlinx.android.synthetic.main.activity_user.*

import org.jetbrains.anko.toast


//This is the main activity that will load a fragment for each navigation in the bottom nav menu
class UserActivity : BaseActivity() {



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

        //Setting up bottom navigation with fragments. - functions that handles fragment are from kotlin extensions
         val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->

            when(item.itemId){
                R.id.id_nav_item_play -> {
                    toast("play menu pressed")
                    replaceFragment(R.id.layout_container, PlayFragment())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_item_profile -> {
                    toast("profile menu pressed")
                    replaceFragment(R.id.layout_container, ProfileFragment())
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_item_bank -> {
                    toast("bank menu pressed")
                    replaceFragment(R.id.layout_container, BankFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_item_tutorials -> {
                    toast("tutorials menu pressed")
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

        //Setting up bottom navigation listener
        bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)

    }
//
//    private fun replaceFragment(fragment : Fragment){
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.replace(R.id.layout_container, fragment)
//        fragmentTransaction.commit()
//
//    }
}