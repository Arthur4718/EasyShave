
package com.devarthur.easyshave


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.adapter.DataItemAdapter
import com.devarthur.easyshave.adapter.HorarioItemAdapter
import com.devarthur.easyshave.dataModel.DataItem
import com.devarthur.easyshave.dataModel.HorarioItem
import com.devarthur.easyshave.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_agenda_detalhe.*
import kotlinx.android.synthetic.main.include_toolbar.*


class AgendaDetalhe : BaseActivity() {

    val dataList = ArrayList<DataItem>()
    val horaList = ArrayList<HorarioItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_detalhe)

        val title = intent.getSerializableExtra("titulo") as String
        setupToolbar(R.id.toolbar, title, false)


        initActions()

    }

    private fun initActions() {

        val recyclerDatas = findViewById<RecyclerView>(R.id.recyclerDatas)
        val recyclerHorario = findViewById<RecyclerView>(R.id.recyclerDisponiveis)

        recyclerDatas?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)
        recyclerHorario?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL, false)

        dataList.add(DataItem("27/05/2019"))
        dataList.add(DataItem("28/05/2019"))

        horaList.add(HorarioItem("12:00"))
        horaList.add(HorarioItem("13:00"))

        val adapterData = DataItemAdapter(dataList)
        val adapterHora = HorarioItemAdapter(horaList)

        recyclerDatas?.adapter = adapterData
        recyclerHorario?.adapter = adapterHora

        btnAddData.setOnClickListener {

            addDataDialog()
        }

        btnAddHorario.setOnClickListener {

        }

        btnEditarValor.setOnClickListener {

        }
    }

    private fun addDataDialog() {

        val mBuilder = AlertDialog.Builder(this.context)
        val mLayout = LinearLayout(this.context)
        val edtData = EditText(this.context)
        val txtCodeServico = TextView(this.context)
        val alertTitle = TextView(this.context)



        edtData.hint = "Digite uma Data"
        alertTitle.text = "Criar novo serviço"
        alertTitle.setTextColor(resources.getColor(R.color.black))

        txtCodeServico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        edtData.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

        alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        alertTitle.setSingleLine()

        edtData.setSingleLine()


        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.addView(edtData)
        mLayout.addView(txtCodeServico)
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
