package com.supermatch.smplay.activity.RegisterActivities


import android.os.Bundle
import android.widget.Toast
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import kotlinx.android.synthetic.main.activity_connection_error.*

class ConnectionErrorActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connection_error)

        //Todo - Show app version

        tvVersionText.text = "Version 1.0"

        btnSkipUpdate.setOnClickListener {

            Toast.makeText(context, "Btn Skip update clicked", Toast.LENGTH_SHORT).show()
        }

        btnUpdate.setOnClickListener {
            Toast.makeText(context, "Btn Update Clicked", Toast.LENGTH_SHORT).show()
        }

    }


}
