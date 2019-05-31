package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.DetalheDataAdapter
import com.devarthur.easyshave.dataModel.DetalheDataModel
import com.devarthur.easyshave.extensions.setupToolbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detalhe_datas_estabelecimento.*
import java.util.ArrayList


//Mostra as datas disponíveis de um serviço selecionado pelo usuário.
class DetalheDatasEstabelecimento : AppCompatActivity() {

    private var dataList = ArrayList<DetalheDataModel>()
    private var db = FirebaseFirestore.getInstance()
    private var servicoUid : String = ""
    private var TAG = "datasdebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_datas_estabelecimento)

        servicoUid = intent.getSerializableExtra("servicoUid") as String
        val toolbarTitle = intent.getSerializableExtra("servico") as String

        setupToolbar(R.id.toolbar, toolbarTitle, true)
        initActions()

        Log.d(TAG, servicoUid)

        db.collection("datas")
            .whereEqualTo("servicoUid", servicoUid)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    var data = document.getString("data").toString()
                    var id = document.id
                    loadDataIntoRecylerView(data, id)

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    private fun loadDataIntoRecylerView(data: String, id: String) {
        recycler_view_datas_servico_disponiveis.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        dataList.add(DetalheDataModel(data, id))

        val adapterData = DetalheDataAdapter(dataList)
        recycler_view_datas_servico_disponiveis.adapter = adapterData
    }

    private fun initActions() {
//        loadDebugData()
    }

//    private fun loadDebugData() {
//        recycler_view_datas_servico_disponiveis.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
////        dataList.add(DetalheDataModel("27/05/2019"))
////        dataList.add(DetalheDataModel("28/05/2019"))
//
//        val adapterData = DetalheDataAdapter(dataList)
//        recycler_view_datas_servico_disponiveis.adapter = adapterData
//    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
