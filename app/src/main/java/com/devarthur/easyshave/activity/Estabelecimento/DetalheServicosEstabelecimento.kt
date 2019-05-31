package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.ServicoDisponiveisAdapter
import com.devarthur.easyshave.dataModel.ServicoDataMotel
import com.devarthur.easyshave.extensions.setupToolbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detalhe_servicos_estabelecimento.*
import java.util.*

//Exibe uma navegação dos serviços de um salão, com o bottom navigation.
//Ao clicar em um serviço, mostrar os horários disponíveis daquele serviço.
class DetalheServicosEstabelecimento : AppCompatActivity() {

    //Database
    private var db = FirebaseFirestore.getInstance()
    private val TAG: String? = "arthurdebug"
    private var salaoUid : String = ""

    private val dataList = ArrayList<ServicoDataMotel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_servicos_estabelecimento)

        val toolbarTitle = intent.getSerializableExtra("estabelecimento") as String
        salaoUid = intent.getSerializableExtra("salaoUid") as String
        Log.d(TAG, "intent id : " + salaoUid)
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        setupToolbar(R.id.toolbar, toolbarTitle, true)

        setupBottomNavBar()

        //Carrega o primeiro menu e serviço e a primeira lista.
        if(savedInstanceState == null){
            bottomNavBar.selectedItemId = R.id.id_nav_cabelo
        }

        //Database data.
        db.collection("servicos")
            .whereEqualTo("salaoUid", salaoUid)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    var servico = document.getString("servico").toString()
                    var valor = document.getString("valor").toString()
                    loadFirebaseData(servico, valor)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }


    }

    //Navegação inferior.
    private fun setupBottomNavBar() {

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->

            when(item.itemId){
                R.id.id_nav_cabelo -> {
                    //carregar apenas todos serviços de cabelo
                    loadDataList(item.itemId)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_barba -> {
                    //carregar apenas barba

                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_manicure -> {
                    //carregar apenas manicure

                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_pedicure -> {
                    //carregar apenas pedicure

                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_sobrancelha -> {
                    //carregar apenas sobrancelha

                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

        bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
    }

    //Dados de teste.
    private fun loadDataList(itemId: Int) {

        when(itemId){
            R.id.id_nav_cabelo -> {
                //carregar apenas todos serviços de cabelo
                //loadStaticData()


            }

            R.id.id_nav_barba -> {
                //carregar apenas barba



            }

            R.id.id_nav_manicure -> {
                //carregar apenas manicure


            }
            R.id.id_nav_pedicure -> {
                //carregar apenas pedicure


            }
            R.id.id_nav_sobrancelha -> {


            }

        }



    }

    private fun loadFirebaseData(servico: String, valor: String) {
        recyler_view_servicos_disponiveis.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL,false)


            dataList.add(
                ServicoDataMotel(
                    servico,
                    valor
                ))


        val adapter = ServicoDisponiveisAdapter(dataList)
        recyler_view_servicos_disponiveis.adapter = adapter
    }

    //Debug data
    private fun loadStaticData() {
        recyler_view_servicos_disponiveis.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL,false)

        for(i in 1..10){
            dataList.add(
                ServicoDataMotel(
                    "Cabelo $i",
                    "$i"+"00" + ",00"
                    ))
        }

        val adapter = ServicoDisponiveisAdapter(dataList)
        recyler_view_servicos_disponiveis.adapter = adapter
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
