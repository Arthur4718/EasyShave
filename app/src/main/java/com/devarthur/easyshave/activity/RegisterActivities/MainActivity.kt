package com.devarthur.easyshave.activity.RegisterActivities

import android.os.Bundle
import com.devarthur.easyshave.R
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.devarthur.easyshave.extensions.setupToolbar
import com.devarthur.easyshave.extensions.toast
import android.content.Intent
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.activity.BaseActivities.CarrosActivity
import com.devarthur.easyshave.domain.tipoCarro

class MainActivity : BaseActivity() , NavigationView.OnNavigationItemSelectedListener {

    //This is only a sample class and its not in use in this project.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(R.id.toolbar)
        setupNavDrawer()
    }

    private fun setupNavDrawer(){
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toogle = ActionBarDrawerToggle(
            this,drawer,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)

        drawer.addDrawerListener(toogle)
        toogle.syncState()
        val nagivationView = findViewById<NavigationView>(R.id.nav_view)
        nagivationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_item_carros_todos -> {
                toast("Clicou em carros")
            }
            R.id.nav_item_carros_classicos -> {

                val intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", tipoCarro.classicos)
                startActivity(intent)


            }
            R.id.nav_item_carros_esportivos -> {

                val intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", tipoCarro.esportivos)
                startActivity(intent)
            }
            R.id.nav_item_carros_luxo -> {

                val intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", tipoCarro.luxo)
                startActivity(intent)

            }
            R.id.nav_item_site_livro -> {
                toast("Clicou em site livro")

            }
            R.id.nav_item_settings -> {
                toast("Clicou em configurações")

            }

        }
        //Closes the drawer after an event..
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)

        return true
    }
}
