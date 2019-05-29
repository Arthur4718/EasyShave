package com.devarthur.easyshave.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.dataModel.AgendamentoEstModel
import com.devarthur.easyshave.dataModel.EstabelecimentoModel
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
        holder.txtDistancia.text = userCard.distancia


        holder.cardView.setOnClickListener {

//            toast("Click")

//            val servico = userCard.nomeServico
//            val nomeUsuario = userCard.nomeUsuario
//            val horario = userCard.horario
//            val status = userCard.statusAgendamento
//            val data = userCard.data
//            val valor = userCard.preco
//            val distancia = userCard.distancia
//
//            if(status.equals("Agendado")){
//                return@setOnClickListener
//            }
//
//
//            holder.itemView.context.startActivity<ServicoDetalhe>(
//                "servico" to servico,
//                "nome" to nomeUsuario,
//                "horario" to horario,
//                "status" to status,
//                "data" to data,
//                "preco" to valor,
//                "distancia" to distancia
//                )
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