package com.supermatch.smplay.utils

/*
 File created by : Arthur Gomes da Silva @ Sportsmatch
 Date: 2019-05-02 - 14:45 
 Contat: devarthur4718@hotmail.com

  Copyright Sportsmatch 2019. 
*/
import android.app.Activity
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

object ValidationsUtils {

    //==============================================================================================
    //
    // Verifie Obrigatory Fields
    //
    //==============================================================================================

    fun isEmpty(verifyData: String): Boolean {
        return if (verifyData == "") {
            false
        } else {
            true
        }
    }

    fun isEmpty(verifyData: Int): Boolean {
        return if (verifyData <= -1) {
            false
        } else {
            true
        }
    }

    //==============================================================================================
    //
    // Verifie Obrigatory Fields
    //
    //==============================================================================================

    fun isFieldObrigatory(editText: EditText, msgErro: String): Boolean {
        if (editText.text.toString().trim { it <= ' ' } == "") {
            editText.error = msgErro
            return false
        } else {
            return true
        }
    }

    fun isPasswordValid(editText : EditText, errorMessage : String ) : Boolean{

        val minPasswordLengh = 6

        if(editText.text.length < minPasswordLengh){
            editText.error = errorMessage
            return false
        } else{
            return true
        }



    }

    fun isFieldObrigatory(etField: EditText): Boolean {

        if (etField.text.toString().trim { it <= ' ' } == "" || etField.text.toString().trim { it <= ' ' }.isEmpty()) {
            etField.error = "Preencha este campo"
            etField.requestFocus()
            return false
        }

        return true
    }

    fun isFieldObrigatory(number: Int): Boolean {

        return if (number == -1) {
            false
        } else true

    }


    //==============================================================================================
    //
    // Verify Array Boolean
    //
    //==============================================================================================

    fun verifyArrayObrigatory(ok: BooleanArray): Boolean {

        for (nOk in ok) {
            if (!nOk) {
                return false
            }
        }
        return true
    }

    //==============================================================================================
    //
    // Verify Login
    //
    //==============================================================================================

    fun verifyLoginAccount(emailLogin: EditText, senhaLogin: EditText): Boolean {

        val ok = booleanArrayOf(
            isFieldObrigatory(senhaLogin, "Preencha este campo"),
            isPasswordValid(senhaLogin, "Senha inválida"),
            isFieldObrigatory(emailLogin, "Preencha este campo"),
            isEmailValide(emailLogin)
        )

        for (nOk in ok) {
            if (!nOk) {
                return false
            }
        }
        return true
    }


    //==============================================================================================
    //
    // Email Verify
    //
    //==============================================================================================

    fun isEmailValide(etField: EditText): Boolean {
        val emailPattern = ("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")
        if (!etField.text.toString().trim { it <= ' ' }.matches(emailPattern.toRegex())) {
            etField.error = "Digite um e-mail válido"
            etField.requestFocus()
            return false
        }
        return true
    }

    fun isEmailconfimation(email: EditText, confimationEmail: EditText): Boolean {

        val ok = booleanArrayOf(
            isFieldObrigatory(confimationEmail),
            isFieldObrigatory(email),
            isEmailValide(confimationEmail),
            isEmailValide(email)
        )

        val valide1 = verifyArrayObrigatory(ok)

        if (valide1) {

            if (email.text.toString().trim { it <= ' ' } == confimationEmail.text.toString().trim { it <= ' ' }) {
                return true
            } else {
                email.setText("")
                confimationEmail.setText("")
                email.error = "As senhas não são iguais"
                confimationEmail.error = "As senhas não são iguais"
                return false
            }

        } else {
            email.setText("")
            confimationEmail.setText("")
            email.error = "As senhas não são iguais"
            confimationEmail.error = "As senhas não são iguais"
            return false
        }

    }

    //==============================================================================================
    //
    // Password Verify
    //
    //==============================================================================================

    fun isPasswordConfirmation(password: EditText, passwordConfirmation: EditText): Boolean {

        val ok = booleanArrayOf(isFieldObrigatory(passwordConfirmation), isFieldObrigatory(password))

        val valide1 = verifyArrayObrigatory(ok)

        if (valide1) {

            if (password.text.toString().trim { it <= ' ' } != passwordConfirmation.text.toString().trim { it <= ' ' }) {
                password.setText("")
                passwordConfirmation.setText("")
                password.error = "As senhas não são iguais"
                passwordConfirmation.error = "As senhas não são iguais"
                return false
            } else {
                return true
            }
        } else {
            return false
        }
    }

    //==============================================================================================
    //
    // CheckBox
    //
    //==============================================================================================

    fun verifyCheckboxState(activity: Activity, checkBox: CheckBox, textErro: String): Boolean {
        val c = checkBox.isChecked
        if (c) {
            return true
        } else {
            if (textErro != "") {
                Toast.makeText(activity, textErro, Toast.LENGTH_SHORT).show()
            }
            return false
        }

    }

}
