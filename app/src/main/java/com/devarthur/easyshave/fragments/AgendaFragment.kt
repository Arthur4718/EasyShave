package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton


import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.UserAgendamentoAdapter
import com.devarthur.easyshave.dataModel.UserAgendamento
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.agenda_fragment.*



class AgendaFragment : BaseFragment() {

    private var nomeUsuario: String = ""
    private var email: String? = ""
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

        val user = FirebaseAuth.getInstance().currentUser
        val name = user?.displayName
        email = user?.email

        if(email.equals("user1@gmail.com")){
            nomeUsuario = "Salão"
        }else{
            nomeUsuario = "Usuario"
        }

        initActions(view)
        setupBottomNavBar(view)

        return view

    }

    private fun setupBottomNavBar(view: View) {

        val bottomNavBar = view?.findViewById<BottomNavigationView>(R.id.bottomNavBar)

        val user = FirebaseAuth.getInstance().currentUser
        val name = user?.displayName
        email = user?.email

        if(email.equals("user1@gmail.com")){

            //Setting up bottom navigation with fragments. - functions that handles fragment are from kotlin extensions
            val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->

                when(item.itemId){
                    R.id.id_nav_cabelo -> {
                        //carregar apenas cabelo masculino
                        toolbarFiltro.visibility = View.VISIBLE
                        createListCabelo()
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.id_nav_barba -> {
                        //carregar apenas barba
                        toolbarFiltro.visibility = View.GONE
                        createListBarba()
                        return@OnNavigationItemSelectedListener true
                    }

                    R.id.id_nav_manicure -> {
                        //carregar apenas manicure
                        toolbarFiltro.visibility = View.GONE
                        createListManicure()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.id_nav_pedicure -> {
                        //carregar apenas pedicure
                        toolbarFiltro.visibility = View.GONE
                        createListPedicure()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.id_nav_sobrancelha -> {
                        toolbarFiltro.visibility = View.GONE
                        createListSobrancelha()
                        return@OnNavigationItemSelectedListener true
                    }

                }
                false
            }

            //Setting up bottom navigation listener
            bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
        }else{

            bottomNavBar.visibility = View.GONE
        }



    }

    private fun createListSobrancelha() {
        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()


        for (i in 1..3) {

            
            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario $i",
                    "Sobrancelha",
                    "29/06/2019",
                    "13:00",
                    "Agendar",
                    "R$ 80,00",
                    "Km: $i"
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
                    "$nomeUsuario $i",
                    "Manicure",
                    "29/06/2019",
                    "13:00",
                    "Agendar",
                    "R$ 50,00",
                    "Km: $i"
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
            val distancia = i * 2
            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario $i",
                    "Manicure",
                    "29/06/2019",
                    "13:00",
                    "Agendar",
                    "R$ 50,00",
                    "Km: $i"
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

            val distancia = i * 2

            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario $i",
                    "Barba",
                    "29/06/2019",
                    "13:00",
                    "Agendar",
                    "R$ 70,00",
                    "Km: $i"
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

        
            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario",
                    "Cabelo Feminino",
                    "29/06/2019",
                    "13:00",
                    "Agendado",
                    "R$ 190,00",
                    "Km: 2"
                )
            )


        for (i in 1..3) {

            val distancia = i * 2
            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario $i",
                    "Cabelo Masculino",
                    "30/06/2019",
                    "14:00",
                    "A confirmar",
                    "R$ 80,00",
                    "Km: $i"
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

            var distancia = i * 2
            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario $i",
                    "Cabelo Feminino",
                    "29/06/2019",
                    "13:00",
                    "Agendado",
                    "R$ 150,00",
                    "Km: $i"
                )
            )

        }
        for (i in 1..3) {

            var distancia = i * 2
            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario $i",
                    "Cabelo Masculino",
                    "30/06/2019",
                    "14:00",
                    "A confirmar",
                    "R$ 250,00",
                    "Km: $i"
                )
            )

        }
        val adapter = UserAgendamentoAdapter(agendaList)
        mRecyclerView?.adapter = adapter


        val rbFiltroMasculino = view?.findViewById<RadioButton>(R.id.rbFiltroMasculino)
        //Filtro com radiobutton

        rbFiltroMasculino.setOnClickListener {
            if(rbFiltroMasculino.isChecked){
                createListMasculino()
            }
        }

        val rbFiltroFeminino = view?.findViewById<RadioButton>(R.id.rbFiltroFeminino)
        rbFiltroFeminino.setOnClickListener {
            if(rbFiltroFeminino.isChecked){
                createListFeminino()
            }
        }


    }

    private fun createListFeminino() {

        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()


        for (i in 1..3) {
            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario",
                    "Cabelo Feminino",
                    "29/06/2019",
                    "13:00",
                    "Agendado",
                    "R$ 190,00",
                    "Km: 2"
                )
            )

        }

    }

    private fun createListMasculino() {

        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerView)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        agendaList.clear()

        for (i in 1..3) {

            var distancia = i * 2
            agendaList.add(
                UserAgendamento(
                    "$nomeUsuario $i",
                    "Cabelo Masculino",
                    "30/06/2019",
                    "14:00",
                    "A confirmar",
                    "R$ 250,00",
                    "Km: $i"
                )
            )

        }



    }
}
