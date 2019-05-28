package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devarthur.easyshave.R
import com.devarthur.easyshave.utils.FireStoreUtil
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        return  view
    }


    override fun onStart() {
        super.onStart()

//        FireStoreUtil.getCurrentUser { user ->
//            if(this@ProfileFragment.isVisible){
//                editText_username.setText(user.username)
//                editText_usertype.setText(user.useremail)
//                editText_userbirthdate.setText(user.userbirthdate)
//                editText_usertype.setText(user.userType)
//                //Todo update image for the user later...
//
//                // if(!pictureJustChaged && user.profilePath != null)
//
//
//            }
//        }
    }
}
