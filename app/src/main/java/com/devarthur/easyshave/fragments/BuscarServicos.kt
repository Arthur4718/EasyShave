package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.EstabelecimentoAdapter
import com.devarthur.easyshave.dataModel.EstabelecimentoModel


//Busca os estabelecimentos próximos.
//Ao click do card, abre uma pagina que detalhe os serviços do estabelecimento
//Ao click do serviço do estabelecimento,mostra os horários disponíveis.
class BuscarServicos : Fragment() {


    val dataList = ArrayList<EstabelecimentoModel>()

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

        var mRecyclerView = view?.findViewById<RecyclerView>(R.id.recylerViewEstabelecimentosProximos)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)

        for(i in 1..10){
            dataList.add(
                EstabelecimentoModel(
                "Salão $i",
                  "Rua, avenida, local , n $i",
                    "1$i" + "00"))
        }

        val adapter = EstabelecimentoAdapter(dataList)

        mRecyclerView?.adapter = adapter


    }


}
