package com.supermatch.smplay.activity.RegisterActivities


import android.os.Bundle
import com.supermatch.smplay.R
import com.supermatch.smplay.activity.BaseActivities.BaseActivity
import org.jetbrains.anko.startActivity

class WellcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome)

        //Todo - Check for the web api status here

        //Todo - Check for user logged information


        //Todo - send user to the splashscreen activity.
    }


    override fun onResume() {
        super.onResume()

        //Todo - Check connection

        //Debug

    }
}
