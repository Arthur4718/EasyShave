package com.supermatch.smplay.fragments


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.supermatch.smplay.domain.tipoCarro

import com.supermatch.smplay.R

private var type : tipoCarro = tipoCarro.classicos

class CarrosFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Check for the paramater that has been sent for the fragment
        type = arguments?.getSerializable("tipo") as tipoCarro
    }

    //Creates a view for the fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_carros, container, false)
        val textView = view?.findViewById<TextView>(R.id.text)
        //Conversts string resource to string
        val typeString = getString(type.string)
        textView!!.text = "Carros $typeString"

        return view
    }


}
