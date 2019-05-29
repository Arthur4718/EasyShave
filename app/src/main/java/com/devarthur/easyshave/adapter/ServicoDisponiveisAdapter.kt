package com.devarthur.easyshave.adapter


import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.startActivity
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.Estabelecimento.DetalheDatasEstabelecimento
import com.devarthur.easyshave.dataModel.ServicoDataMotel
import java.util.ArrayList

class ServicoDisponiveisAdapter(val userList : ArrayList<ServicoDataMotel>) : RecyclerView.Adapter<ServicoDisponiveisAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ServicoDisponiveisAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.servico_disponivel_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userCard : ServicoDataMotel = userList[position]


        holder.txtServicoDisponivel.text = userCard.tituloServico
        holder.txtValorServicoDisponivel.text = userCard.precoServico

        holder.servicoCard.setOnClickListener {


            val title : String = holder.txtServicoDisponivel.text.toString()
            holder.itemView.context.startActivity<DetalheDatasEstabelecimento>("titulo" to title)

        }

    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        //capturar as views do card
        val txtServicoDisponivel = itemView.findViewById(R.id.txtServicoDisponivel) as TextView
        val txtValorServicoDisponivel = itemView.findViewById(R.id.txtPrecoServicoDisponivel) as TextView
        val servicoCard = itemView.findViewById(R.id.cardDataItem) as CardView


    }


}