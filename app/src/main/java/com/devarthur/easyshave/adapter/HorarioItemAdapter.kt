package com.devarthur.easyshave.adapter


import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import android.widget.Toast
import com.devarthur.easyshave.AgendaDetalhe
import org.jetbrains.anko.startActivity
import com.devarthur.easyshave.R
import com.devarthur.easyshave.dataModel.DataItem
import com.devarthur.easyshave.dataModel.HorarioItem
import com.devarthur.easyshave.dataModel.Servico
import java.util.ArrayList

class HorarioItemAdapter(val dataList : ArrayList<HorarioItem>) : RecyclerView.Adapter<HorarioItemAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): HorarioItemAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.horarioServico_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userCard : HorarioItem = dataList[position]

        holder.txtData.text = userCard.mHorario


        holder.card.setOnClickListener {

            Toast.makeText(holder.itemView.context, "Item clicked", Toast.LENGTH_SHORT).show()

        }

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        //capturar as views do card
        val txtData = itemView.findViewById(R.id.txtDataDetalheHorario) as TextView
        val card = itemView.findViewById(R.id.cardContent) as CardView


    }


}