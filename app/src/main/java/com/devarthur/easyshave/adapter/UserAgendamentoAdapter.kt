package com.devarthur.easyshave.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devarthur.easyshave.R
import com.devarthur.easyshave.dataModel.UserAgendamento
import java.util.ArrayList

class UserAgendamentoAdapter(val userList : ArrayList<UserAgendamento>) : RecyclerView.Adapter<UserAgendamentoAdapter.ViewHolder>()  {


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): UserAgendamentoAdapter.ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.agenda_item, viewGroup, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return userList.size
    }

    override fun onBindViewHolder(p0: UserAgendamentoAdapter.ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }
}