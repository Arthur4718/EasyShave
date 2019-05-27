package com.devarthur.easyshave

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.devarthur.easyshave.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_servido_detalhe.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ServicoDetalhe : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servido_detalhe)

        val title = "Detalhes do serviço"
        val servico = intent.getSerializableExtra("servico") as String
        val nome = intent.getSerializableExtra("nome") as String

        txtDetalheServico1.setText(servico)

        setupToolbar(R.id.toolbar, title, true)

        toolbar.setOnClickListener {finish()}


    }
}
