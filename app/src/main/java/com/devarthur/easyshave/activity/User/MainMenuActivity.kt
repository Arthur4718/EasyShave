package com.devarthur.easyshave.activity.User


import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.devarthur.easyshave.extensions.toast
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button

import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.RegisterActivities.LoginEmailActivity
import com.devarthur.easyshave.dataModel.UserProfile
import com.devarthur.easyshave.dataModel.UserSnapshot

import com.devarthur.easyshave.extensions.addFragment
import com.devarthur.easyshave.extensions.replaceFragment
import com.devarthur.easyshave.fragments.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.agenda_fragment.*
import org.jetbrains.anko.startActivity


class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val RECORD_REQUEST_CODE: Int = 1
    //UserToken
    var userUId : String = ""
    var userType : Int = 0

    //var datalist : MutableList<UserProfile>

    //Localização.
    lateinit var locationManager : LocationManager
    private var hasGps = false
    private var hasNetwork = false

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



        if(email.equals("user1@gmail.com")){
            //Menu itens
            val menu = navView.menu

            menu.removeItem(R.id.nav_serviços_)
            navView.invalidate()
        }


        navUserName.setText(name)
        navUserEmail.setText(email)

        selectButton.setOnClickListener { toast("Selecione uma nova foto.") }

        checkUserType()
        initActions()
        setupPermissions()


        //Loads the first page
        if(savedInstanceState == null){
            //Adds fragment to the layout
            addFragment(R.id.layout_content, AgendaFragment())
            toolbar.setTitle("Minha Agenda")

        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)

        if (permission != PackageManager.PERMISSION_GRANTED) {

            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            RECORD_REQUEST_CODE)
    }

    private fun checkUserType() {
        if(userType == 1){
            //admin
        }else{
            // not admin
        }
    }


    private fun initActions() {

        listenForDatabase()



    }

    private fun listenForDatabase() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")

        ref.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                //Todo retrieve data from firebase



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

    //Adicionar o filtro ao toolbar quando necessário.
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
//
//    //Filtro quando necessario
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

                if(userType == 1){
                    toolbar.setTitle("Gerenciar Serviços")
                }
                else{
                    toolbar.setTitle("Buscar Serviços.")
                }
                replaceFragment(R.id.layout_content, ServicosFragment())

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

    //Verifica se o usuário deu permissão pra acessar localização.
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.d("arthurdebug", "Permission has been denied by user")
                    toast("Sem as devidas permissões o app pode não funcionar completamente.")
                } else {
                    Log.d("arthurdebug", "Permission has been granted by user")
                }
            }
        }
    }
}
