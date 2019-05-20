package com.devarthur.easyshave.activity.BaseActivities

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity

/*
 Class created by : Arthur Gomes da Silva @ Sportsmatch
 Date: 2019-04-25 - 11:14 
 Contat: devarthur4718@hotmail.com

  Copyright Sportsmatch 2019. 
*/
open class BaseActivity : AppCompatActivity() {

    //SwipeRefreshLayout
    private var refresh: SwipeRefreshLayout? = null


    //Propriety used to access context from anywhere in the project
    protected val context : Context get() = this


    //Shared methods across the activities are definied here...


}