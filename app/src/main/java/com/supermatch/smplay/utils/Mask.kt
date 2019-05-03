package com.supermatch.smplay.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/*
 File created by : Arthur Gomes da Silva @ Sportsmatch
 Date: 2019-05-03 - 16:38 
 Contat: devarthur4718@hotmail.com

  Copyright Sportsmatch 2019. 
*/
class Mask{
    //Compation objects are are just like static classes from Java.
    companion object{
        private fun replaceChars(cpfFull : String) : String{
            //Remove unsidered chars from the cpf field

            return cpfFull.replace(".", "").replace("-", "")
                .replace("(", "").replace(")", "")
                .replace("/","").replace(" ","")
                .replace("*", "")
        }


        fun mask(mask : String, etCpf: EditText) : TextWatcher {



            val textWatcher : TextWatcher = object : TextWatcher {

                var isUpdating : Boolean = false
                var oldString : String = ""
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
                override fun afterTextChanged(s: Editable?) {

                }




            }



            return textWatcher
        }

    }

}