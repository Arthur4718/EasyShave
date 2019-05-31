package com.devarthur.easyshave.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.AgendamentoAdapter
import com.devarthur.easyshave.dataModel.AgendamentoModel
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import com.google.firebase.auth.FirebaseAuth

//Mostra para o usuário que serviços ele agendou
class AgendaPerfilUsuario : BaseFragment() {

    private lateinit var adapter: AgendamentoAdapter
    private val TAG: String? = "arthurdebug"
    //Data model que define os dados do agendamento.
    private var agendamentoList = ArrayList<AgendamentoModel>()

    //Database
    val db = FirebaseFirestore.getInstance()

    private var nome = ""
    private var servico = ""
    private var valor = ""
    private var status = ""
    private var data = ""
    private var hora = ""

    private var querryUid = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.agenda_fragment, container, false)

        val progressDialog = indeterminateProgressDialog ("Carregando...")

        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        querryUid = currentFirebaseUser?.uid ?: "blank"
        Log.d("agendadebug" , "uid : ${currentFirebaseUser?.uid}")

        //Database data
        db.collection("userAgendamento")
            .whereEqualTo("uid", querryUid)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("agendadebug" , "document: ${document.data}")


                    nome = document.getString("username").toString()
                    servico = document.getString("servico").toString()
                    valor = document.getString("valor").toString()
                    data = document.getString("data").toString()
                    hora = document.getString("hora").toString()
                    status = ""

                    createListAgendamento(view, nome,servico, valor, data, hora, status)

                }
            }
            .addOnFailureListener { exception ->

            }

        progressDialog.dismiss()

        return view

    }


    //Cria uma lista com dados locais para preencher a listagem.
    private fun createListAgendamento(
        view: View?,
        nome: String,
        servico: String,
        valor: String,
        data: String,
        status: String,
        status1: String
    ) {
        var mRecyclerView = view?.findViewById<RecyclerView>(R.id.recylerAgendamento)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)


        agendamentoList.add(
            AgendamentoModel(
                nome,
                servico,
                hora,
                data,
                status,
                valor
            )
        )


        adapter = AgendamentoAdapter(agendamentoList)
        mRecyclerView?.adapter = adapter


    }


}
