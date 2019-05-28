package com.devarthur.easyshave.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.devarthur.easyshave.R
import com.devarthur.easyshave.utils.FireStoreUtil
import kotlinx.android.synthetic.main.fragment_perfil.*



class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        val btnUpdateProfile = view.findViewById<Button>(R.id.btnUpdateProfile)

        btnUpdateProfile.setOnClickListener {
            FireStoreUtil.updateCurrentUser(
                editText_username.text.toString(),
                editText_email.text.toString(),
                editText_userbirthdate.text.toString(),
                editText_usertype.text.toString()
            )
        }


        return view
    }

    override fun onStart() {
        super.onStart()
        FireStoreUtil.getCurrentUser { user ->
            if (this@PerfilFragment.isVisible) {
                editText_username.setText(user.username)
                editText_email.setText(user.useremail)
                editText_userbirthdate.setText(user.userbirthdate)
                if(user.userType.equals("1")){
                    editText_usertype.setText("Estabelecimento")
                }else{
                    editText_usertype.setText("Usuário comum")
                }

                //Todo update image for the user later...

                // if(!pictureJustChaged && user.profilePath != null)


            }
        }
    }

}

