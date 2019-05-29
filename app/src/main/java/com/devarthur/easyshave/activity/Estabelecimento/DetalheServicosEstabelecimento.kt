package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.ServicoDisponiveisAdapter
import com.devarthur.easyshave.dataModel.ServicoDataMotel
import com.devarthur.easyshave.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_detalhe_servicos_estabelecimento.*
import java.util.ArrayList

//Exibe uma navegação dos serviços de um salão, com o bottom navigation.
//Ao clicar em um serviço, mostrar os horários disponíveis daquele serviço.
class DetalheServicosEstabelecimento : AppCompatActivity() {


    val dataList = ArrayList<ServicoDataMotel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_servicos_estabelecimento)

        var title = "Serviços"
        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        setupToolbar(R.id.toolbar, title, true)

        setupBottomNavBar()

        //Carrega o primeiro menu e serviço e a primeira lista.
        if(savedInstanceState == null){
            bottomNavBar.selectedItemId = R.id.id_nav_cabelo
        }

    }

    private fun setupBottomNavBar() {

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        //Setting up bottom navigation with fragments. - functions that handles fragment are from kotlin extensions
        val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->

            when(item.itemId){
                R.id.id_nav_cabelo -> {
                    //carregar apenas todos serviços de cabelo
                    loadDataList(item.itemId)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_barba -> {
                    //carregar apenas barba
                    loadDataList(item.itemId)
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_manicure -> {
                    //carregar apenas manicure
                    loadDataList(item.itemId)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_pedicure -> {
                    //carregar apenas pedicure
                    loadDataList(item.itemId)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_sobrancelha -> {
                    loadDataList(item.itemId)
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

        //Setting up bottom navigation listener
        bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
    }

    private fun loadDataList(itemId: Int) {

        when(itemId){
            R.id.id_nav_cabelo -> {
                //carregar apenas todos serviços de cabelo
                loadStaticData()

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
