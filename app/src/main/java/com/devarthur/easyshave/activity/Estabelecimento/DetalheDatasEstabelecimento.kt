package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.devarthur.easyshave.R
import com.devarthur.easyshave.extensions.setupToolbar


//Mostra as datas disponíveis de um serviço selecionado pelo usuário.
class DetalheDatasEstabelecimento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_datas_estabelecimento)

        var title = "Datas"
        setupToolbar(R.id.toolbar, title, true)
    }
}
