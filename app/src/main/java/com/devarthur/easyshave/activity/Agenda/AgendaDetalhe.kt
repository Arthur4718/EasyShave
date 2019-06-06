
package com.devarthur.easyshave.activity.Agenda


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.Log
import android.util.TypedValue

import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.adapter.DataItemAdapter

import com.devarthur.easyshave.dataModel.DataItemModel
import com.devarthur.easyshave.extensions.setupToolbar
import com.devarthur.easyshave.extensions.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_agenda_detalhe.*
import kotlinx.android.synthetic.main.include_toolbar.toolbar
import org.jetbrains.anko.support.v4.toast


//Add uma data para um servico, usando seu uid como Chave.
class AgendaDetalhe : BaseActivity() {


    val dataList = ArrayList<DataItemModel>()

    //Database access
    val db = FirebaseFirestore.getInstance()

    //MyID
    private var querryUid = ""
    //Servico
    private var servicoId: String = ""

    private val TAG = "agendaDetalhe"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_detalhe)

        val title = intent.getSerializableExtra("titulo") as String
        setupToolbar(R.id.toolbar, title, true)

        toolbar.setOnClickListener {finish()}

        initActions()
    }

    private fun initActions() {


        //Todo visualizar esse serviços e datas como usuário comum.

        btnAddData.setOnClickListener {

            addDataDialog()
        }

        btnEditarValor.setOnClickListener {

            addValorDialog()
        }

        //Fetch data

        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        querryUid = currentFirebaseUser?.uid ?: "blank"

        //Recolhe todas as datas deste serviço do banco de dados.
        db.collection("datas")
            .whereEqualTo("salaoUid", querryUid)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    var data = document.getString("data").toString()
                    servicoId = document.getString("servicoUid").toString()
                    loadDataIntoRecylerView(data, servicoId)

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    private fun loadDataIntoRecylerView(data: String, servicoId: String) {

        val recyclerDatas = findViewById<RecyclerView>(R.id.recyclerDatas)
        recyclerDatas?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)
        dataList.add(DataItemModel(data, servicoId))


        val adapterData = DataItemAdapter(dataList)
        recyclerDatas?.adapter = adapterData

    }

    private fun addValorDialog() {

        val mBuilder = AlertDialog.Builder(this.context)
        val mLayout = LinearLayout(this.context)
        val edtValor = EditText(this.context)
        val alertTitle = TextView(this.context)

        edtValor.hint = "Digite um novo valor"
        edtValor.inputType = InputType.TYPE_CLASS_NUMBER
        alertTitle.text = "Novo Valor"

        edtValor.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.addView(alertTitle)
        mLayout.addView(edtValor)


        mLayout.setPadding(50, 40, 50, 10)

        mBuilder.setView(mLayout)

        mBuilder.setPositiveButton("Ok"){ dialog: DialogInterface?, which: Int ->
            val data = edtValor.text.toString()

            //add A lista e atualize o adapter
            txtValor.setText(data)

        }

        mBuilder.setNeutralButton("Cancelar"){ dialog: DialogInterface?, which: Int ->
            dialog?.dismiss()
        }

        mBuilder.create().show()

    }

    private fun addDataDialog() {

        val mBuilder = AlertDialog.Builder(this.context)
        val mLayout = LinearLayout(this.context)
        val edtData = EditText(this.context)
        val txtCodeServico = TextView(this.context)
        val alertTitle = TextView(this.context)

        edtData.hint = "Digite uma Data"
        alertTitle.text = "Adicionar nova Data"
        txtCodeServico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        edtData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        edtData.setSingleLine()
        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.addView(alertTitle)
        mLayout.addView(edtData)
        mLayout.setPadding(50, 40, 50, 10)
        mBuilder.setView(mLayout)

        mBuilder.setPositiveButton("Ok"){ dialog: DialogInterface?, which: Int ->
            val data = edtData.text.toString()

            //add A lista e atualize o adapter
            addItemData(data)
        }

        mBuilder.setNeutralButton("Cancelar"){ dialog: DialogInterface?, which: Int ->
            dialog ?.dismiss()
        }

        mBuilder.create().show()
    }
    private fun addItemData(data: String) {

        dataList.add(DataItemModel(data,querryUid))

        //sending data do FireStore
        val dataItem = HashMap<String, Any>()
        dataItem["data"] = data
        dataItem["disponivel"] = true
        dataItem["salaoUid"] = querryUid
        dataItem["servicoUid"] = servicoId

        db.collection("datas")
            .add(dataItem)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                toast("data updated")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)

                toast("Failure when updating... $e")
            }


    }

//    private fun updateFireStore() {
//        val estabelecimento = HashMap<String, Any>()
//        estabelecimento["local"] = editText_endereco.text.toString()
//        estabelecimento["nome"] =  editText_username.text.toString()
//        estabelecimento["email"] =  editText_email.text.toString()
//        estabelecimento["userUid"] = FirebaseAuth.getInstance().uid.toString()
//
//        //Salva na tabela estabelecimento - desse modo aparece na listagem para o usuário comum
//        db.collection("estabelecimento")
//            .add(estabelecimento)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                toast("Profile Updated")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//
//                toast("Failure when updating... $e")
//            }
//    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }


}
