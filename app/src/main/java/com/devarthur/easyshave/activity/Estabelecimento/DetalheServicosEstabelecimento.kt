package com.devarthur.easyshave.activity.Estabelecimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import com.devarthur.easyshave.R
import com.devarthur.easyshave.extensions.setupToolbar
import com.devarthur.easyshave.extensions.toast

//Exibe uma navegação dos serviços de um salão, com o bottom navigation.
//Ao clicar em um serviço, mostrar os horários disponíveis daquele serviço.
class DetalheServicosEstabelecimento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_servicos_estabelecimento)

        var title = "Serviços"
        setupToolbar(R.id.toolbar, title, true)

        setupBottomNavBar()



    }

    private fun setupBottomNavBar() {

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavBar)
        //Setting up bottom navigation with fragments. - functions that handles fragment are from kotlin extensions
        val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->

            when(item.itemId){
                R.id.id_nav_cabelo -> {
                    //carregar apenas cabelo masculino
                    toast("Item clicked")
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_barba -> {
                    //carregar apenas barba
                    toast("Item clicked")
                    return@OnNavigationItemSelectedListener true
                }

                R.id.id_nav_manicure -> {
                    //carregar apenas manicure
                    toast("Item clicked")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_pedicure -> {
                    //carregar apenas pedicure
                    toast("Item clicked")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.id_nav_sobrancelha -> {
                    toast("Item clicked")
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }

        //Setting up bottom navigation listener
        bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
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
