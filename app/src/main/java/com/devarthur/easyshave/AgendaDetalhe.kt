
package com.devarthur.easyshave


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.TypedValue

import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.adapter.DataItemAdapter

import com.devarthur.easyshave.dataModel.DataItem
import com.devarthur.easyshave.extensions.setupToolbar

import kotlinx.android.synthetic.main.activity_agenda_detalhe.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import kotlinx.android.synthetic.main.include_toolbar.*
import kotlinx.android.synthetic.main.include_toolbar.toolbar


class AgendaDetalhe : BaseActivity() {

    val dataList = ArrayList<DataItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_detalhe)

        val title = intent.getSerializableExtra("titulo") as String
        setupToolbar(R.id.toolbar, title, true)

        toolbar.setOnClickListener {


            finish()

        }





        initActions()


    }

    private fun initActions() {

        val recyclerDatas = findViewById<RecyclerView>(R.id.recyclerDatas)


        recyclerDatas?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)


        dataList.add(DataItem("27/05/2019"))
        dataList.add(DataItem("28/05/2019"))



        val adapterData = DataItemAdapter(dataList)


        recyclerDatas?.adapter = adapterData


        btnAddData.setOnClickListener {

            addDataDialog()
        }


        btnEditarValor.setOnClickListener {

            addValorDialog()
        }


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

        dataList.add(DataItem(data))
    }



}
