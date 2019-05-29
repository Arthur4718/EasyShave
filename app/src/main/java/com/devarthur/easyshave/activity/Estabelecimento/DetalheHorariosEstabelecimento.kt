package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.devarthur.easyshave.R
import com.devarthur.easyshave.extensions.setupToolbar

class DetalheHorariosEstabelecimento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_horarios_estabelecimento)

        var title = "Horários"
        setupToolbar(R.id.toolbar, title, true)
    }
}
