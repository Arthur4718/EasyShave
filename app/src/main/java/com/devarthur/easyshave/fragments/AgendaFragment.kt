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
import com.devarthur.easyshave.adapter.UserAgendamentoAdapter
import com.devarthur.easyshave.dataModel.UserAgendamento
import kotlinx.android.synthetic.main.agenda_fragment.*
import org.jetbrains.anko.support.v4.toast


class AgendaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.agenda_fragment, container, false)



        initActions()
    }

    private fun initActions() {




    }


}
