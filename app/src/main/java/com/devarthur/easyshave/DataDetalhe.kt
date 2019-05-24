package com.devarthur.easyshave

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.util.TypedValue
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.devarthur.easyshave.adapter.HorarioItemAdapter
import com.devarthur.easyshave.dataModel.HorarioItem
import com.devarthur.easyshave.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_data_detalhe.*
import kotlinx.android.synthetic.main.include_toolbar.*
import java.util.ArrayList

class DataDetalhe : AppCompatActivity() {

    val horaList = ArrayList<HorarioItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detalhe)

        val toolbarTitle = intent.getSerializableExtra("data") as String
        setupToolbar(R.id.toolbar, toolbarTitle, true)

        toolbar.setOnClickListener {

            finish()

        }

        initActions()

    }

    private fun initActions() {

        val recyclerHora = findViewById<RecyclerView>(R.id.recyclerHorario)

        recyclerHora?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        horaList.add(HorarioItem("12:00"))
        horaList.add(HorarioItem("13:00"))

        val adapterHora = HorarioItemAdapter(horaList)

        recyclerHora?.adapter = adapterHora


        btnAddHorario.setOnClickListener {

            addHoraDialog()
        }


    }

    private fun addHoraDialog() {

        val mBuilder = AlertDialog.Builder(this)
        val mLayout = LinearLayout(this)
        val edtHora = EditText(this)
        val alertTitle = TextView(this)

        edtHora.hint = "Digite uma nova hora"
        edtHora.inputType = InputType.TYPE_CLASS_NUMBER
        alertTitle.text = "Adicionar Hora"

        edtHora.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.addView(alertTitle)
        mLayout.addView(edtHora)

        mLayout.setPadding(50, 40, 50, 10)

        mBuilder.setView(mLayout)

        mBuilder.setPositiveButton("Ok"){ dialog: DialogInterface?, which: Int ->
            val hora = edtHora.text.toString()

            //add A lista e atualize o adapter
            addItemHora(hora)

        }

        mBuilder.setNeutralButton("Cancelar"){ dialog: DialogInterface?, which: Int ->
            dialog?.dismiss()
        }

        mBuilder.create().show()
    }

    private fun addItemHora(hora: String) {
        horaList.add(HorarioItem(hora))
    }
}
