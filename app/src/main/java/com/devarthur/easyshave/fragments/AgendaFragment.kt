package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.UserAgendamentoAdapter
import com.devarthur.easyshave.dataModel.UserAgendamento



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
        setupBottomNavBar(view)

        return view

    }

    private fun setupBottomNavBar(view: View) {

        val bottomNavBar = view?.findViewById<BottomNavigationView>(R.id.bottomNavBar)

        //Setting up bottom navigation with fragments. - functions that handles fragment are from kotlin extensions
        val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->

            when(item.itemId){
                R.id.id_nav_cabelo -> {
                    //carregar apenas cabelo masculino
                    createListCabelo()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_barba -> {
                    //carregar apenas barba
                    createListBarba()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_manicure -> {
                    //carregar apenas manicure
                    createListManicure()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_pedicure -> {
                    //carregar apenas pedicure
                    createListPedicure()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_sobrancelha -> {
                    createListSobrancelha()
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

        //Setting up bottom navigation listener
        bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
    }

    private fun createListSobrancelha() {
        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()


        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Sobrancelha",
                    "29/06/2019",
                    "13:00",
                    "Agendar"
                )
            )

        }
        val adapter = UserAgendamentoAdapter(agendaList)
        mRecyclerView?.adapter = adapter
    }

    private fun createListPedicure() {
        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()


        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Manicure",
                    "29/06/2019",
                    "13:00",
                    "Agendar"
                )
            )

        }
        val adapter = UserAgendamentoAdapter(agendaList)
        mRecyclerView?.adapter = adapter
    }

    private fun createListManicure() {
        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()


        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Manicure",
                    "29/06/2019",
                    "13:00",
                    "Agendar"
                )
            )

        }
        val adapter = UserAgendamentoAdapter(agendaList)
        mRecyclerView?.adapter = adapter
    }

    private fun createListBarba() {

        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()


        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Barba",
                    "29/06/2019",
                    "13:00",
                    "Agendar"
                )
            )

        }
        val adapter = UserAgendamentoAdapter(agendaList)
        mRecyclerView?.adapter = adapter


    }

    private fun createListCabelo() {

        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()

        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Cabelo Feminino",
                    "29/06/2019",
                    "13:00",
                    "Agendado"
                )
            )

        }
        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Cabelo Masculino",
                    "30/06/2019",
                    "14:00",
                    "A confirmar"
                )
            )

        }
        val adapter = UserAgendamentoAdapter(agendaList)
        mRecyclerView?.adapter = adapter

    }

    private fun initActions(view: View) {
        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()

        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Cabelo Feminino",
                    "29/06/2019",
                    "13:00",
                    "Agendado"
                )
            )

        }
        for (i in 1..3) {

            agendaList.add(
                UserAgendamento(
                    "Sandy $i",
                    "Cabelo Masculino",
                    "30/06/2019",
                    "14:00",
                    "A confirmar"
                )
            )

        }
        val adapter = UserAgendamentoAdapter(agendaList)
        mRecyclerView?.adapter = adapter


    }
}
