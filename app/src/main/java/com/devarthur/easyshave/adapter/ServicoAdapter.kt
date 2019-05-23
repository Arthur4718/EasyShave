package com.devarthur.easyshave.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import android.widget.Toast
import com.devarthur.easyshave.R
import com.devarthur.easyshave.dataModel.Servico
import java.util.ArrayList




class ServicoAdapter(val userList : ArrayList<Servico>) : RecyclerView.Adapter<ServicoAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ServicoAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.servico_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userCard : Servico = userList[position]

        holder.txtCardServico.text = userCard.nomeServico

        holder.servicoCard.setOnClickListener {
            Toast.makeText(it.context, "item clicked $position", Toast.LENGTH_SHORT).show()
        }

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        //capturar as views do card


        val txtCardServico = itemView.findViewById(R.id.txtNomeServicoCard) as TextView
        val txtCodServico = itemView.findViewById(R.id.txtCod) as TextView
        val servicoCard = itemView.findViewById(R.id.cardServico) as TextView


    }


}