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
import com.devarthur.easyshave.adapter.ServicoAdapter
import com.devarthur.easyshave.dataModel.Servico


class ServicosFragment : Fragment() {

    val servicoList = ArrayList<Servico>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(R.layout.fragment_servicos, container, false)


        initActions(view)

        return view
    }

    private fun initActions(view: View?) {

        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerViewServicos)

        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)

        servicoList.add(Servico("Cabelo Masculino", 0))
        servicoList.add(Servico("Barba", 1))
        servicoList.add(Servico("Cabelo Feminino", 2 ))
        servicoList.add(Servico("Manicure e Pedicure", 3))
        servicoList.add(Servico("Sobrancelha", 4))

        val adapter = ServicoAdapter(servicoList)

        mRecyclerView?.adapter = adapter

    }


}
