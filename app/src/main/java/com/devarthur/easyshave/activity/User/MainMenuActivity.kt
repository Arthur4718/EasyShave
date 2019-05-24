package com.devarthur.easyshave.activity.User

//https://www.youtube.com/watch?v=67hthq6Y2J8
import android.os.Bundle
import com.devarthur.easyshave.extensions.toast
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button

import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.RegisterActivities.LoginEmailActivity

import com.devarthur.easyshave.extensions.addFragment
import com.devarthur.easyshave.extensions.replaceFragment
import com.devarthur.easyshave.fragments.AgendaFragment
import com.devarthur.easyshave.fragments.PerfilFragment
import com.devarthur.easyshave.fragments.ServicosFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.startActivity


class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //UserToken
    var userUId : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        //Toolbar


        //Header to navigation drawer.
        val headerView : View = navView.getHeaderView(0)
        val navUserName : TextView = headerView.findViewById(R.id.txtUserNameHeader)
        val navUserEmail : TextView = headerView.findViewById(R.id.txtUserNameEmail)
        val selectButton : Button = headerView.findViewById(R.id.btnSelectButton)

        //Pegar a informação do usuário e apresentar

        val user = FirebaseAuth.getInstance().currentUser
        val name = user?.displayName
        val email = user?.email
        userUId = user?.uid.toString()


        Log.d("arthurdebug", " $userUId")



        navUserName.setText(name)
        navUserEmail.setText(email)

        selectButton.setOnClickListener { toast("Selecione uma nova foto.") }


        initActions()

        //Loads the first page
        if(savedInstanceState == null){
            //Adds fragment to the layout
            addFragment(R.id.layout_content, AgendaFragment())
            toolbar.setTitle("Minha Agenda")

        }
    }

    private fun initActions() {

        listenForDatabase()



    }

    private fun listenForDatabase() {
        val ref = FirebaseDatabase.getInstance().getReference("/users/$userUId")

        ref.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                p0.toString();
                Log.d("arthurdebug", "p0 $p0")
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        })
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        when (item.itemId) {
            R.id.nav_perfil -> {

                replaceFragment(R.id.layout_content, PerfilFragment())
                toolbar.setTitle("Meu Perfil")

            }
            R.id.nav_agenda -> {

                replaceFragment(R.id.layout_content, AgendaFragment())
                toolbar.setTitle("Minha Agenda")
            }
            R.id.nav_sugestoes -> {

            }
            R.id.nav_serviços_ -> {
                replaceFragment(R.id.layout_content, ServicosFragment())
                toolbar.setTitle("Gerenciar Serviços")
            }
            R.id.nav_share -> {

            }
            R.id.nav_sair_perfil -> {

                logoutFromApplication()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logoutFromApplication() {
         FirebaseAuth.getInstance().signOut()
         startActivity<LoginEmailActivity>()
         toast("Usuário desconectado")
         finish()

    }
}
