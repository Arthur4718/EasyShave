package com.devarthur.easyshave.activity.RegisterActivities


import android.os.Bundle
import android.os.Handler

import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
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

        val timer : Long = 1000
        val handler = Handler()
        handler.postDelayed({
            //Do something after n Seconds
            startActivity<SelectLoginActivity>()
            finish()


        }, timer)

    }
}
