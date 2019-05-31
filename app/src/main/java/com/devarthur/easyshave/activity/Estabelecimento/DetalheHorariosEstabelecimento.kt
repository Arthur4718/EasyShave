package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.DetalheHorarioAdapter
import com.devarthur.easyshave.dataModel.DetalheHorarioModel
import com.devarthur.easyshave.extensions.setupToolbar
import com.google.firebase.firestore.FirebaseFirestore
import java.util.ArrayList

class DetalheHorariosEstabelecimento : AppCompatActivity() {

    private val horaList = ArrayList<DetalheHorarioModel>()
    private var db = FirebaseFirestore.getInstance()
    private var servicoUid : String = ""
    private var TAG = "datasdebug"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_horarios_estabelecimento)

        val toolbarTitle = intent.getSerializableExtra("data") as String
        servicoUid = intent.getSerializableExtra("servicoUid") as String
        setupToolbar(R.id.toolbar, toolbarTitle, true)
        //initActions()
        Log.d(TAG, "querry: $servicoUid")


        db.collection("horarios")
            .whereEqualTo("servicoUid", servicoUid)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    var horarios = document.getString("horario").toString()
                    var id = document.id
                    loadDataIntoRecyclerView(horarios, id)

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    private fun loadDataIntoRecyclerView(horarios: String, id: String) {
        val recyclerHora = findViewById<RecyclerView>(R.id.recyclerHorario)
        recyclerHora?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        horaList.add(DetalheHorarioModel(horarios))

        val adapterHora = DetalheHorarioAdapter(horaList)
        recyclerHora?.adapter = adapterHora

    }

    private fun initActions() {

        val recyclerHora = findViewById<RecyclerView>(R.id.recyclerHorario)

        recyclerHora?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

//        horaList.add(DetalheHorarioModel("12:00"))
//        horaList.add(DetalheHorarioModel("13:00"))

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
