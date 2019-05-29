package com.devarthur.easyshave.activity.CheckOut

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.devarthur.easyshave.R
import kotlinx.android.synthetic.main.activity_checkout_pagamento.*
import com.mercadopago.*;


class CheckoutPagamento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout_pagamento)

        


        initActions()
    }

    private fun initActions() {

        btnCheckOutPagamento.setOnClickListener {
            Toast.makeText(applicationContext, "click", Toast.LENGTH_SHORT).show()
        }
    }
}
