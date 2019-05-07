package com.supermatch.smplay.extensions


import android.support.v7.app.ActionBar
import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast


/*
 File created by : Arthur Gomes da Silva @ Sportsmatch
 Date: 2019-04-25 - 14:08 
 Contat: devarthur4718@hotmail.com

  Copyright Sportsmatch 2019. 
*/

//findViewById + setOnClickListener

fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick: (v: android.view.View?) -> Unit){
    val view = findViewById<View>(viewId)
    view.setOnClickListener {
        onClick(it)
    }

}

//Shows a simple toast.

fun Activity.toast(message: CharSequence, length : Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this,message, length).show()

fun Activity.toast(@StringRes message: Int, length : Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this,message, length).show()

//Setup a toolbar
fun AppCompatActivity.setupToolbar(@IdRes id: Int, title : String? = null,
                                   upNavigation: Boolean = false) : ActionBar{

    val toolbar = findViewById<android.support.v7.widget.Toolbar>(id)
    setSupportActionBar(toolbar)


    if(title != null){
        supportActionBar?.title = title
    }
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)

    return  supportActionBar!!
}

//Adds a framgment to the layout

fun AppCompatActivity.addFragment(@IdRes layoutId : Int, fragment : Fragment){
    fragment.arguments = intent.extras
    val ft = supportFragmentManager.beginTransaction()
    ft.add(layoutId, fragment)
    ft.commit()
}


fun AppCompatActivity.replaceFragment(@IdRes layoutId: Int, fragment: Fragment){
    val ft = supportFragmentManager.beginTransaction()
    ft.replace(layoutId, fragment)
    ft.commit()
}

