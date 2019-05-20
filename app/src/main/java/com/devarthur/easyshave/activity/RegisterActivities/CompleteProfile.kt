package com.devarthur.easyshave.activity.RegisterActivities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.devarthur.easyshave.R
import com.devarthur.easyshave.utils.CPFUtil
import com.devarthur.easyshave.utils.Mask
import com.devarthur.easyshave.utils.ValidationsUtils
import kotlinx.android.synthetic.main.activity_complete_profile.*
import kotlinx.android.synthetic.main.activity_complete_profile.chbTerms
import org.jetbrains.anko.toast


//This activity is used to recover aditional information about the user when he sign up with facebook.
class CompleteProfile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_profile)

        initActions()
    }

    private fun initActions() {

        tvBackNavigation4.setOnClickListener { finish() }

        btnCompleteProfile.setOnClickListener { signUpWithAditionalInfo() }

        //Mask for CPF
        edtCompleteProfile_CPF.addTextChangedListener(Mask.mask("###.###.###-##", edtCompleteProfile_CPF))

        //Mask for Phone Contact
        edtCompleteProfile_phone.addTextChangedListener(Mask.mask("(##)#####-####", edtCompleteProfile_phone))


        edtCompleteProfile_CPF.setOnFocusChangeListener { v, hasFocus ->

            if(!hasFocus){
                //When the user leaves the field we check if the cpf is valid


                if(!CPFUtil.myValidateCPF(edtCompleteProfile_CPF.text.toString())){
                    edtCompleteProfile_CPF.error = "CPF invalido"
                }else{

                    edtCompleteProfile_CPF.error = null
                }

            }

        }



    }

    private fun signUpWithAditionalInfo() {

        if(ValidationsUtils.verifyCheckboxState(this, chbTerms, "Aceite os Termos")){

            val validation = booleanArrayOf(

                ValidationsUtils.isFieldObrigatory(edtCompleteProfile_name),
                ValidationsUtils.isFieldObrigatory(edtCompleteProfile_email),
                ValidationsUtils.isEmailValide(edtCompleteProfile_email),
                ValidationsUtils.isFieldObrigatory(edtCompleteProfile_phone)

            )

            if(ValidationsUtils.verifyArrayObrigatory(validation)){
                toast("Sending information.")
            }

        }

    }
}
