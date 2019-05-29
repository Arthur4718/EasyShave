package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devarthur.easyshave.R



//Busca os estabelecimentos próximos.
//Ao click do card, abre uma pagina que detalhe os serviços do estabelecimento
//Ao click do serviço do estabelecimento,mostra os horários disponíveis.
class BuscarServicos : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_servicos_perfil_estabelecimento, container, false)

        createListData(view)
        return view
    }

    private fun createListData(view: View?) {

    }


}
