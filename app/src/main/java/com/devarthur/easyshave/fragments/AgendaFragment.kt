package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.devarthur.easyshave.R
import kotlinx.android.synthetic.main.agenda_fragment.*


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
