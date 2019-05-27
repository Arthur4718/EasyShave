package com.devarthur.easyshave.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.ServicoDetalhe
import com.devarthur.easyshave.dataModel.ServicoModel
import com.devarthur.easyshave.dataModel.UserAgendamento
import java.util.ArrayList
import org.jetbrains.anko.startActivity


class UserAgendamentoAdapter(val userList : ArrayList<UserAgendamento>) : RecyclerView.Adapter<UserAgendamentoAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): UserAgendamentoAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.agenda_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userCard : UserAgendamento = userList[position]

        holder.txtUserName.text = userCard.nomeUsuario
        holder.txtCardServico.text = userCard.nomeServico
        holder.txtCardHorario.text = userCard.horario
        holder.txtCardStatus.text = userCard.statusAgendamento
        holder.txtCardData.text = userCard.data
        holder.txtValorHora.text = userCard.preco
        holder.txtDistancia.text = userCard.distancia


        holder.cardView.setOnClickListener {
            //Todo abrir detalhes do serviço
            val servico = userCard.nomeServico
            val nomeUsuario = userCard.nomeUsuario
            val horario = userCard.horario
            val status = userCard.statusAgendamento
            val data = userCard.data
            val valor = userCard.preco
            val distancia = userCard.distancia

            if(status.equals("Agendado")){
                return@setOnClickListener
            }


            holder.itemView.context.startActivity<ServicoDetalhe>(
                "servico" to servico,
                "nome" to nomeUsuario,
                "horario" to horario,
                "status" to status,
                "data" to data,
                "preco" to valor,
                "distancia" to distancia
                )
        }


    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        //Todo - capturar tb a imagem e construir cada card com sua imagem.
        val txtUserName = itemView.findViewById(R.id.txtNomeServicoCard) as TextView
        val txtCardServico = itemView.findViewById(R.id.txtCardServico) as TextView
        val txtCardHorario = itemView.findViewById(R.id.txtCardHorarioAgendamento) as TextView
        val txtCardStatus = itemView.findViewById(R.id.txtCardAgendamentoStatus) as TextView
        val txtCardData = itemView.findViewById(R.id.txtCardDataAgendamento) as TextView
        val txtValorHora = itemView.findViewById(R.id.txtValorHora) as TextView
        val txtDistancia = itemView.findViewById(R.id.txtDistanciaAgenda) as TextView
        val cardView = itemView.findViewById(R.id.cardDataItem) as CardView

    }
}