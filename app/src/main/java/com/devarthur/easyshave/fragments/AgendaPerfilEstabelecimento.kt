package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devarthur.easyshave.R


//Mostra os agendamentos que foram confirmados pelos usuários para este estabelecimento
class AgendaPerfilEstabelecimento : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_agenda_perfil_usuario, container, false)
        createListAgendamento(view)
        return view
    }

    private fun createListAgendamento(view: View?) {

    }


}
