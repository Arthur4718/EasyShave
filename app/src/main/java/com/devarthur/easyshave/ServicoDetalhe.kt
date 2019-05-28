package com.devarthur.easyshave

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.devarthur.easyshave.extensions.setupToolbar
import com.devarthur.easyshave.extensions.toast
import kotlinx.android.synthetic.main.activity_servido_detalhe.*
import kotlinx.android.synthetic.main.include_toolbar.*

class ServicoDetalhe : AppCompatActivity() {

    private var preco: String = ""
    private var data: String = ""
    private var horario: String = ""
    private var nome: String = ""
    private var servico: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servido_detalhe)






        val title = "Detalhes do serviço"
        setupToolbar(R.id.toolbar, title, true)
        toolbar.setOnClickListener {finish()}

        initActions()


    }

    private fun initActions() {
        servico = intent.getSerializableExtra("servico") as String
        nome = intent.getSerializableExtra("nome") as String
        horario = intent.getSerializableExtra("horario") as String
        data = intent.getSerializableExtra("data") as String
        preco = intent.getSerializableExtra("preco") as String

        txtDetalheServico1.setText(servico)
        txtNomeUsuario1.setText(nome)
        txtHorario1.setText(horario)
        txtData1.setText(data)
        txtValorHora.setText(preco)

        btnAgendarServico.setOnClickListener {

            agendaDialog()
        }

    }

    private fun agendaDialog() {
        val mBuilder = AlertDialog.Builder(this)
        val mLayout = LinearLayout(this)
        val alertTitle = TextView(this)
        val detalhesServico = TextView(this)



        alertTitle.text = "Confirmar Serviço."
        detalhesServico.text = "Deseja confirmar $servico, ás $data, no dia $horario,  no valor de $preco ? "


        alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        detalhesServico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.addView(alertTitle)
        mLayout.addView(detalhesServico)

        mLayout.setPadding(50, 40, 50, 10)

        mBuilder.setView(mLayout)

        mBuilder.setPositiveButton("Confirmar"){ dialog: DialogInterface?, which: Int ->

            toast("Serviço agendado.")
            finish()


        }

        mBuilder.setNeutralButton("Cancelar"){ dialog: DialogInterface?, which: Int ->
            dialog?.dismiss()
        }

        mBuilder.create().show()
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
