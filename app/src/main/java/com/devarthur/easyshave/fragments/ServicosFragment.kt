package com.devarthur.easyshave.fragments



import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
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


class ServicosFragment : Fragment() {

    val servicoList = ArrayList<Servico>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(R.layout.fragment_servicos, container, false)


        initActions(view)

        return view
    }

    private fun initActions(view: View?) {

        val mRecyclerView = view?.findViewById<RecyclerView>(R.id.mRecyclerViewServicos)



        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)

        servicoList.add(Servico("Cabelo Masculino", 0))
        servicoList.add(Servico("Barba", 1))
        servicoList.add(Servico("Cabelo Feminino", 2 ))
        servicoList.add(Servico("Manicure e Pedicure", 3))
        servicoList.add(Servico("Sobrancelha", 4))

        val adapter = ServicoAdapter(servicoList)

        mRecyclerView?.adapter = adapter

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
        val txtCodeServico = TextView(this.context)
        val alertTitle = TextView(this.context)



        edtNomeServico.hint = "Digite serviço"
        alertTitle.text = "Criar novo serviço"

        txtCodeServico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        edtNomeServico.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)

        alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
        alertTitle.setSingleLine()

        edtNomeServico.setSingleLine()


        mLayout.orientation = LinearLayout.VERTICAL
        mLayout.addView(edtNomeServico)
        mLayout.addView(txtCodeServico)
        mLayout.setPadding(50, 40, 50, 10)

        mBuilder.setView(mLayout)

        mBuilder.setPositiveButton("Ok"){ dialog: DialogInterface?, which: Int ->
            val nomeServico = edtNomeServico.text.toString()
            val position = servicoList.size
            val codeServico = position.toString()

            //add A lista e atualize o adapter
            addItem(nomeServico, codeServico)
        }

        mBuilder.setNeutralButton("Cancelar"){dialog: DialogInterface?, which: Int ->
            dialog ?.dismiss()
        }

        mBuilder.create().show()


    }

    private fun addItem(nomeServico: String, codeServico: String) {

        servicoList.add(Servico(nomeServico,  codeServico.toInt()))
    }

    private fun removeItem(codeServico: String) {

        if( codeServico.toInt() > servicoList.size){
            return
        }

        servicoList.removeAt(codeServico.toInt())

    }


}
