package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.UserAgendamentoAdapter
import com.devarthur.easyshave.dataModel.UserAgendamento
import org.jetbrains.anko.support.v4.toast


class AgendaFragment : BaseFragment() {

    val agendaList = ArrayList<UserAgendamento>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.agenda_fragment, container, false)



        initActions(view)

        return view

    }



    private fun initActions(view: View) {

        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)

        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)


        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Cabelo Feminino",
                    "12:00 ás 13:00",
                    "Agendado"
                )
            )

        }


        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Cabelo Masculino",
                    "14:00 ás 15:00",
                    "A confirmar"
                )
            )

        }


        val adapter = UserAgendamentoAdapter(agendaList)

        mRecyclerView?.adapter = adapter



    }




}
