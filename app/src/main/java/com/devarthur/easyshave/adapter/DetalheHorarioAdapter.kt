package com.devarthur.easyshave.adapter


import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.dataModel.DetalheHorarioModel
import org.jetbrains.anko.startActivity

import java.util.ArrayList

class DetalheHorarioAdapter(val dataList : ArrayList<DetalheHorarioModel>) : RecyclerView.Adapter<DetalheHorarioAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): DetalheHorarioAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.horarioservico_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userCard : DetalheHorarioModel = dataList[position]

        holder.txtData.text = userCard.mHorario


        holder.card.setOnClickListener {
            //Abrir detalhes pagamento com hora, dia e servico.
            //holder.itemView.context.startActivity<CheckoutMercadoPago>()

        }

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        //capturar as views do card
        val txtData = itemView.findViewById(R.id.txtDataDetalheHorario) as TextView
        val card = itemView.findViewById(R.id.CardHorarioItem) as CardView


    }


}