package com.devarthur.easyshave.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.Estabelecimento.DetalheServicosEstabelecimento
import com.devarthur.easyshave.dataModel.EstabelecimentoModel
import org.jetbrains.anko.startActivity
import java.util.ArrayList


class EstabelecimentoAdapter(val userList : ArrayList<EstabelecimentoModel>) : RecyclerView.Adapter<EstabelecimentoAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): EstabelecimentoAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.estabelecimento_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userCard : EstabelecimentoModel = userList[position]

        holder.txtNomeEstabelecimento.text = userCard.nomeEstabelecimento
        holder.txtLocalEstabelecimento.text = userCard.localEstabelecimento
        holder.txtDistancia.text = userCard.distancia + "m"


        holder.cardView.setOnClickListener {
                //Ao clicar no salão, abre uma tela de detalhe com todos serviços deste salão.
                val nomeEstabelecimento = userCard.nomeEstabelecimento // Dado a ser enviado para proxima tela
                holder.itemView.context.startActivity<DetalheServicosEstabelecimento>("estabelecimento" to nomeEstabelecimento)

        }


    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        //Todo - Set up image path later..
        val txtNomeEstabelecimento = itemView.findViewById(R.id.txtNomeEstabelecimento) as TextView
        val txtLocalEstabelecimento = itemView.findViewById(R.id.txtLocalEstabelecimento) as TextView
        val txtDistancia = itemView.findViewById(R.id.textEstabelecimentoDistancia) as TextView

        val cardView = itemView.findViewById(R.id.cardDataItem) as CardView

    }
}