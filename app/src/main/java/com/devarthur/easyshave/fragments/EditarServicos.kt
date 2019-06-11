package com.devarthur.easyshave.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.ServicoAdapter
import com.devarthur.easyshave.dataModel.Servico
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_perfil.*
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.support.v4.toast

//Add ou remove horários para os serviços do salão.
//Servicos são salvos com um uid na tabela servicos. Seus horários e datas são cadastrados em
//tabelas indepentes, sendo identificados pelo uid do servico.
class EditarServicos : Fragment() {

    private var querryUid: String = ""
    val servicoList = ArrayList<Servico>()

    //Database
    val db = FirebaseFirestore.getInstance()


    //databse fields
    private var servico : String = ""
    private var valor : String = ""
    private var tipo : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(R.layout.fragment_servicos, container, false)
        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        querryUid = currentFirebaseUser?.uid ?: "blank"


        initActionsAdmin(view)
        return view
    }


    private fun initActionsAdmin(view: View?) {



//        servicoList.add(Servico("Cabelo Masculino", 1))
//        servicoList.add(Servico("Barba", 2))
//        servicoList.add(Servico("Cabelo Feminino", 3 ))
//        servicoList.add(Servico("Manicure e Pedicure", 4))
//        servicoList.add(Servico("Sobrancelha", 5))

        //Database data
        db.collection("userAgendamento")
            .whereEqualTo("uid", querryUid)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("agendadebug" , "document: ${document.data}")

                    servico = document.getString("servico").toString()
                    valor = document.getString("valor").toString()
                    tipo = document.getString("tipo").toString()

                    createServicoList(servico, valor, tipo)
                }
            }
            .addOnFailureListener { exception ->
                toast("Erro ao conectar ao banco $exception")

            }

        //Exemplo : Ver todos os tipos de serviço para cabelo masculino, todos filtrando da tabela servicos


        val addServico = view?.findViewById<FloatingActionButton>(R.id.fabAddServico)

        addServico?.setOnClickListener {
            //Toast.makeText(it.context, "Add item", Toast.LENGTH_SHORT).show()
            addServicoDialog()
        }

        val removeServico = view?.findViewById<FloatingActionButton>(R.id.fabRemoveServico)

        removeServico?.setOnClickListener {
            //Toast.makeText(it.context, "Remove item", Toast.LENGTH_SHORT).show()
            removeServicoDialog()
        }

    }

    private fun createServicoList(servico: String, valor: String, tipo: String) {

        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerViewServicos)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)
        servicoList.add(Servico(servico, tipo.toInt() ))
        val adapter = ServicoAdapter(servicoList)
        mRecyclerView?.adapter = adapter
    }

    private fun removeServicoDialog() {

        val mBuilder = AlertDialog.Builder(this.context)
        val mLayout = LinearLayout(this.context)
        val edtCodeServico = EditText(this.context)


        edtCodeServico.hint = "Digite serviço"
        edtCodeServico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        edtCodeServico.setSingleLine()
        edtCodeServico.inputType = InputType.TYPE_CLASS_NUMBER

        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.setPadding(50, 40, 50, 10)
        mLayout.addView(edtCodeServico)

        mBuilder.setView(mLayout)

        mBuilder.setPositiveButton("Ok"){ dialog: DialogInterface?, which: Int ->
            val codeServico = edtCodeServico.text.toString()


            //add A lista e atualize o adapter
            removeItem(codeServico)
        }

        mBuilder.setNeutralButton("Cancelar"){dialog: DialogInterface?, which: Int ->
            dialog ?.dismiss()
        }

        mBuilder.create().show()


    }

    private fun addServicoDialog() {

        val mBuilder = AlertDialog.Builder(this.context)
        val mLayout = LinearLayout(this.context)
        val edtNomeServico = EditText(this.context)
        val edtTipo = EditText(this.context)
        val edtValor = EditText(this.context)
        val txtCodeServico = TextView(this.context)
        val alertTitle = TextView(this.context)

        edtNomeServico.hint = "Digite serviço"
        edtTipo.hint = "Digite o tipo"
        edtValor.hint = "Divite o Valor"
        alertTitle.text = "Criar novo serviço"

        txtCodeServico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        edtNomeServico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        edtTipo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        edtValor.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

        alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        alertTitle.setSingleLine()

        edtNomeServico.setSingleLine()
        edtTipo.setSingleLine()
        edtValor.setSingleLine()

        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.addView(edtNomeServico)
        mLayout.addView(edtTipo)
        mLayout.addView(txtCodeServico)
        mLayout.addView(edtValor)
        mLayout.setPadding(50, 40, 50, 10)

        mBuilder.setView(mLayout)

        mBuilder.setPositiveButton("Ok"){ dialog: DialogInterface?, which: Int ->
            val nomeServico = edtNomeServico.text.toString()
            val valorServico = edtValor.text.toString()
            val tipoServico = edtValor.text.toString()
            val position = servicoList.size
            val codeServico = position.toString()

            //add A lista e atualize o adapter
            addItem(nomeServico, codeServico)


            //Update database
            updateServicoOnFirebase(nomeServico, valorServico, tipoServico)
        }

        mBuilder.setNeutralButton("Cancelar"){dialog: DialogInterface?, which: Int ->
            dialog ?.dismiss()
        }

        mBuilder.create().show()


    }

    private fun addItem(nomeServico: String, codeServico: String) {
        //update data on firebase

        servicoList.add(Servico(nomeServico,  codeServico.toInt()))
    }

    private fun updateServicoOnFirebase(nomeServico: String, valorServico : String, tipoServico : String) {

        val servico = HashMap<String, Any>()
        servico["salaoUid"] = querryUid
        servico["servico"] =  nomeServico
        servico["tipo"] =  tipoServico
        servico["valor"] = valorServico

        //Salva na tabela estabelecimento - desse modo aparece na listagem para o usuário comum
        db.collection("estabelecimento")
            .add(servico)
            .addOnSuccessListener { documentReference ->

                toast("data updated")
            }
            .addOnFailureListener { e ->


                toast("Failure when updating... $e")
            }


    }


    private fun removeItem(codeServico: String) {
        //update data on firebase
        if( codeServico.toInt() > servicoList.size){
            return
        }

        servicoList.removeAt(codeServico.toInt())

    }


}
