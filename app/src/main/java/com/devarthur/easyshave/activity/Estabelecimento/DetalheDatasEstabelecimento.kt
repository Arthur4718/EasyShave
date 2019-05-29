package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.DetalheDataAdapter
import com.devarthur.easyshave.dataModel.DataItemModel
import com.devarthur.easyshave.dataModel.DetalheDataModel
import com.devarthur.easyshave.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_detalhe_datas_estabelecimento.*
import java.util.ArrayList


//Mostra as datas disponíveis de um serviço selecionado pelo usuário.
class DetalheDatasEstabelecimento : AppCompatActivity() {

    val dataList = ArrayList<DetalheDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_datas_estabelecimento)

        var title = "Datas"
        val toolbarTitle = intent.getSerializableExtra("servico") as String

        setupToolbar(R.id.toolbar, toolbarTitle, true)
        initActions()
    }

    private fun initActions() {
        loadDebugData()
    }

    private fun loadDebugData() {
        recycler_view_datas_servico_disponiveis.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        dataList.add(DetalheDataModel("27/05/2019"))
        dataList.add(DetalheDataModel("28/05/2019"))

        val adapterData = DetalheDataAdapter(dataList)
        recycler_view_datas_servico_disponiveis.adapter = adapterData
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
