package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.DetalheHorarioAdapter
import com.devarthur.easyshave.dataModel.DetalheHorarioModel
import com.devarthur.easyshave.extensions.setupToolbar
import java.util.ArrayList

class DetalheHorariosEstabelecimento : AppCompatActivity() {

    val horaList = ArrayList<DetalheHorarioModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_horarios_estabelecimento)

        val toolbarTitle = intent.getSerializableExtra("data") as String
        setupToolbar(R.id.toolbar, toolbarTitle, true)
        initActions()
    }

    private fun initActions() {

        val recyclerHora = findViewById<RecyclerView>(R.id.recyclerHorario)

        recyclerHora?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        horaList.add(DetalheHorarioModel("12:00"))
        horaList.add(DetalheHorarioModel("13:00"))

        val adapterHora = DetalheHorarioAdapter(horaList)

        recyclerHora?.adapter = adapterHora

    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
