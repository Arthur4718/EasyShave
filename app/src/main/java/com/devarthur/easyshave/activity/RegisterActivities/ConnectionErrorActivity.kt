package com.devarthur.easyshave.activity.RegisterActivities



import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import kotlinx.android.synthetic.main.activity_connection_error.*

import com.devarthur.easyshave.BuildConfig



class ConnectionErrorActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_error)






        val versionCode = BuildConfig.VERSION_CODE
        tvVersionText.text = "Version $versionCode"


        btnSkipUpdate.setOnClickListener {

            Toast.makeText(context, "Btn Skip update clicked", Toast.LENGTH_SHORT).show()
        }

        btnUpdate.setOnClickListener {
            Toast.makeText(context, "Btn Update Clicked", Toast.LENGTH_SHORT).show()
        }

    }

    //Wether we are updating or with a server maitenance display the correct views and info for the user
    private fun setupViews(isUpdateActive : Boolean){


        if(isUpdateActive){

            val textString = resources.getString(R.string.btnUpdateText)
            btnUpdate.text = textString



        }else{
            val textString = resources.getString(R.string.btnTryAgain)
            btnUpdate.text = textString
            tvConnectionInfo.text = "Servidor em manutenção."
            btnSkipUpdate.visibility = View.INVISIBLE
        }


    }

    override fun onResume() {
        super.onResume()


        //Todo - Get type from bundle and display the adequate message for the user

        val code : Int

        code = 666
        when(code){
            666 -> { // Server is down for maintenence
                setupViews(false)
            }
            402 -> {
                // An update is availabre
                setupViews(true)
            }

        }




    }
}
