package com.devarthur.easyshave.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.utils.FireStoreUtil
import kotlinx.android.synthetic.main.fragment_perfil.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.support.v4.toast

class PerfilFragment : Fragment() {

    var userType : String = ""
    var TAG = "perfildebug"

    //User Info
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
    //Database
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        val btnUpdateProfile = view.findViewById<Button>(R.id.btnUpdateProfile)
        val btnUpdateLocal = view.findViewById<Button>(R.id.btnUpdateLocal)
        val txthint = view.findViewById<TextView>(R.id.textView12)


        btnUpdateProfile.setOnClickListener {
            FireStoreUtil.updateCurrentUser(
                editText_username.text.toString(),
                editText_email.text.toString(),
                editText_userbirthdate.text.toString(),
                userType
            )
        }

        btnUpdateLocal.setOnClickListener {
            updateFireStore()
        }
        return view
    }

    private fun updateFireStore() {
        val estabelecimento = HashMap<String, Any>()
        estabelecimento["local"] = editText_endereco.text.toString()
        estabelecimento["nome"] =  editText_username.text.toString()
        estabelecimento["email"] =  editText_email.text.toString()
        estabelecimento["userUid"] = FirebaseAuth.getInstance().uid.toString()

        //Salva na tabela estabelecimento - desse modo aparece na listagem para o usuário comum
        db.collection("estabelecimento")
            .add(estabelecimento)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                toast("data updated")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)

                toast("Failure when updating... $e")
            }
    }

    override fun onStart() {
        super.onStart()
        editText_usertype.isEnabled = false



        FireStoreUtil.getCurrentUser { user ->
            if (this@PerfilFragment.isVisible) {
                editText_username.setText(user.username)
                editText_email.setText(user.useremail)
                editText_userbirthdate.setText(user.userbirthdate)
                if(user.userType.equals("1")){
                    userType = user.userType
                    editText_usertype.setText("Estabelecimento")
                    editText_endereco.visibility = View.VISIBLE
                    btnUpdateLocal.visibility = View.VISIBLE
                    textView12.visibility =  View.VISIBLE
                 }else{
                    userType = user.userType
                    editText_usertype.setText("Usuário comum")

                    editText_endereco.visibility = View.GONE
                    btnUpdateLocal.visibility = View.GONE
                    textView12.visibility =  View.GONE
                }



                //Todo update image for the user later...

                // if(!pictureJustChaged && user.profilePath != null)


            }
        }
    }

}

