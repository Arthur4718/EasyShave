package com.devarthur.easyshave

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.devarthur.easyshave.extensions.setupToolbar
import kotlinx.android.synthetic.main.include_toolbar.*

class DataDetalhe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detalhe)

        val toolbarTitle = intent.getSerializableExtra("data") as String
        setupToolbar(R.id.toolbar, toolbarTitle, true)

        toolbar.setOnClickListener {


            finish()

        }



    }
}
