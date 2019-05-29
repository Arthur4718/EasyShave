package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.devarthur.easyshave.R

//Exibe uma navegação dos serviços de um salão, com o bottom navigation.
//Ao clicar em um serviço, mostrar os horários disponíveis daquele serviço.
class DetalheServicosEstabelecimento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_servicos_estabelecimento)
    }
}
