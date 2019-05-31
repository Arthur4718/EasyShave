package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.AgendamentoEstAdapter
import com.devarthur.easyshave.dataModel.AgendamentoEstModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


//Mostra os agendamentos que foram confirmados pelos usuários para este estabelecimento
class AgendaPerfilEstabelecimento : Fragment() {

    private val TAG: String? = "admindebug"

    //Data model que define os dados do agendamento.
    val agendamentoEstList = ArrayList<AgendamentoEstModel>()

    //Database
    val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_agenda_perfil_usuario, container, false)
        //createListAgendamento(view)


        //Recuperar os dados deste estabelecimento na tabela estabelecimento.
        db.collection("estabelecimento")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        return view
    }

    private fun createListAgendamento(view: View?) {

        var mRecyclerView = view?.findViewById<RecyclerView>(R.id.recyclerAgendaEstabelecimento)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)

        for(i in 13..18){
            agendamentoEstList.add(AgendamentoEstModel(
                "Cliente",
                "Cabelo Masculino",
                "$i:00",
                "$i/06/2019",
                "Confirmar",
                "R$ 100,00"))
        }
        var adapter = AgendamentoEstAdapter(agendamentoEstList)

        mRecyclerView?.adapter = adapter
        adapter.itemCount
    }


}
