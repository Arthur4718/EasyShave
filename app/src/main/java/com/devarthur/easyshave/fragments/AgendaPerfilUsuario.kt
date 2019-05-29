package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.AgendamentoAdapter
import com.devarthur.easyshave.dataModel.AgendamentoModel

//Mostra para o usuário que serviços ele agendou
class AgendaPerfilUsuario : BaseFragment() {

    //Data model que define os dados do agendamento.
    val agendamentoList = ArrayList<AgendamentoModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.agenda_fragment, container, false)

        createListAgendamento(view)

        return view

    }


    //Cria uma lista com dados locais para preencher a listagem.
    private fun createListAgendamento(view: View?) {
        var mRecyclerView = view?.findViewById<RecyclerView>(R.id.recylerAgendamento)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)

        for(i in 13..18){
            agendamentoList.add(AgendamentoModel(
                "Cliente",
                "Cabelo Masculino",
                "$i:00",
                "$i/06/2019",
                "Confirmar",
                "R$ 100,00"))
        }


        var adapter = AgendamentoAdapter(agendamentoList)

        mRecyclerView?.adapter = adapter




    }


}
