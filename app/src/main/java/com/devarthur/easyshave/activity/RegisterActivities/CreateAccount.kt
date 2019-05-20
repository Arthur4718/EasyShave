package com.devarthur.easyshave.activity.RegisterActivities


import android.os.Bundle

import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.utils.Mask
import com.devarthur.easyshave.utils.ValidationsUtils
import kotlinx.android.synthetic.main.activity_create_account.*
import org.jetbrains.anko.toast

import com.devarthur.easyshave.utils.CPFUtil


class CreateAccount : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        initActions()
    }

    private fun initActions() {

        tvBackNavigation2.setOnClickListener { finish() }

        btnSignUpWithEmail.setOnClickListener { signUpWithUserData() }

        //Mask for cpf
        edtInputCPF.addTextChangedListener(Mask.mask("###.###.###-##", edtInputCPF))

        //Phone Contact mask
        edtContactPhone.addTextChangedListener(Mask.mask("(##)#####-####", edtContactPhone))
        
        edtInputCPF.setOnFocusChangeListener { v, hasFocus ->

            if(!hasFocus){
                //When the user leaves the field we check if the cpf is valid


                if(!CPFUtil.myValidateCPF(edtInputCPF.text.toString())){
                    edtInputCPF.error = "CPF invalido"
                }else{

                    edtInputCPF.error = null
                }

            }

        }



    }

    private fun signUpWithUserData() {
        if(ValidationsUtils.verifyCheckboxState(this, chbTerms, "Aceite os Termos")){

            val validation = booleanArrayOf(


                ValidationsUtils.isPasswordConfirmation(edtSignupPassword, edtSignupPasswordConfirmation),
                ValidationsUtils.isFieldObrigatory(edtSignUp),
                ValidationsUtils.isFieldObrigatory(edtInputCPF),
                ValidationsUtils.isEmailValide(edtSignupEmail),
                ValidationsUtils.isFieldObrigatory(edtSignupEmail),
                ValidationsUtils.isFieldObrigatory(edtContactPhone)
            )

            if (ValidationsUtils.verifyArrayObrigatory(validation)) {

                toast("Sending information.")
            }


        }
    }
}
