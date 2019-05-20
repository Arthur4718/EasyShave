package com.devarthur.easyshave

import android.app.Application
import android.util.Log
import java.lang.IllegalStateException

/*
 Class created by : Arthur Gomes da Silva @ Sportsmatch
 Date: 2019-04-25 - 11:21 
 Contat: devarthur4718@hotmail.com

  Copyright Sportsmatch 2019. 
*/
class KotlinApplication : Application() {

    private val TAG = "KotlinApplication"
    //Called when android creates the application..

    override fun onCreate() {
        super.onCreate()
        //Stores an instance of this class so we can access it as a singleton..
        appInstance = this
    }

    companion object {
        //Singleton for application class
        private var appInstance : KotlinApplication? = null

        fun getInstance() : KotlinApplication{
            if(appInstance == null){
                throw IllegalStateException("Dont forget to add the application class in AndroidManifest.xml")
            }

            return appInstance!!
        }
    }

    //Called when android finishes application process.
    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "KotlinApplication.OnTerminate()")
    }
}