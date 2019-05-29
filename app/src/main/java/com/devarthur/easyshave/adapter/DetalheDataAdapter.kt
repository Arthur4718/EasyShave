package com.devarthur.easyshave.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.Estabelecimento.DetalheHorariosEstabelecimento
import com.devarthur.easyshave.dataModel.DetalheDataModel
import org.jetbrains.anko.startActivity
import java.util.ArrayList

class DetalheDataAdapter(val dataList : ArrayList<DetalheDataModel>) : RecyclerView.Adapter<DetalheDataAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): DetalheDataAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.dataservico_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userCard : DetalheDataModel = dataList[position]

        holder.txtData.text = userCard.mData

        holder.card.setOnClickListener {
            //Abre os horários disponíveis para este serviço.
//            Toast.makeText(holder.itemView.context, "item clicked", Toast.LENGTH_SHORT).show()
            val data = userCard.mData
            holder.itemView.context.startActivity<DetalheHorariosEstabelecimento>("data" to data)
        }

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        //capturar as views do card
        val txtData = itemView.findViewById(R.id.txtDataDetalheHorario) as TextView
        val card = itemView.findViewById(R.id.cardDataItem) as CardView


    }


}