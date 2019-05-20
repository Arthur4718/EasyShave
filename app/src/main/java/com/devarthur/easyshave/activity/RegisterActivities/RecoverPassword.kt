package com.devarthur.easyshave.activity.RegisterActivities


import android.os.Bundle
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.utils.ValidationsUtils
import kotlinx.android.synthetic.main.activity_recover_password.*
import org.jetbrains.anko.toast

//Sends e-mail for the user so he can recover his password.
class RecoverPassword : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)


        initActions()
    }

    private fun initActions() {
        tvBackNavication3.setOnClickListener { finish() }

        btnRecoverPassword.setOnClickListener { recoverUserPassword()}
    }

    private fun recoverUserPassword() {

        if(ValidationsUtils.isEmailValide(edtRecoverEmail) && ValidationsUtils.isFieldObrigatory(edtRecoverEmail)){

            toast("Verifique seu e-mail!")

        }
    }
}
