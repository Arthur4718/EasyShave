package com.devarthur.easyshave.activity.User

import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
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
import android.view.View
import android.widget.TextView
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.RegisterActivities.LoginEmailActivity
import com.devarthur.easyshave.extensions.addFragment
import com.devarthur.easyshave.extensions.replaceFragment
import com.devarthur.easyshave.fragments.*
import com.devarthur.easyshave.utils.FireStoreUtil
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.startActivity

//Carregada após o login - É a tela responsável por carregar o menu lateral para os dois tipos de usuário
class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //Codigo para solicitar gps do user.
    private val RECORD_REQUEST_CODE: Int = 1

    //User data
    private var databaseUserType : String = ""

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

        //Todo upload user photo
        //Header to navigation drawer.
        //val selectButton : Button = headerView.findViewById(R.id.btnSelectButton)
        //selectButton.setOnClickListener { toast("Selecione uma nova foto.") }


        initActions()
        setupPermissions()

        //Loads the first page
        if(savedInstanceState == null){
            //Adds fragment to the layout
            addFragment(R.id.layout_content, AgendaPerfilUsuario())
            toolbar.setTitle("Minha Agenda")

        }
    }

    private fun initActions() {
        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerView : View = navView.getHeaderView(0)

        val navUserName : TextView = headerView.findViewById(R.id.txtUserNameHeader)
        val navUserEmail : TextView = headerView.findViewById(R.id.txtUserNameEmail)

        //Dados da sessão do usuario
        FireStoreUtil.getCurrentUser { user ->

            navUserName.setText(user.username)
            navUserEmail.setText(user.useremail)
            databaseUserType = user.userType

            if(databaseUserType.equals("1")){
                //Estabelecimento - Mostra os itens que pertencem a este usuário
                //Agenda - Usuário
                // Busca - Serviço

                val menu = navView.menu
                menu.removeItem(R.id.nav_agenda_perfil_usuario)
                menu.removeItem(R.id.nav_buscar_servicos_perfil_usuario)
                navView.invalidate()

            }

            if(databaseUserType.equals("0")){
                val menu = navView.menu
                menu.removeItem(R.id.nav_agenda_perfil_estabelecimento)
                menu.removeItem(R.id.nav_serviços_perfil_estabelecimento)
                navView.invalidate()

            }
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


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        val toolbar: Toolbar = findViewById(R.id.toolbar)

        when (item.itemId) {
            R.id.nav_agenda_perfil_usuario -> {
                //Somente visivel para o perfil usuário
                //Mostra os serviços agendados pelo usuário.

                replaceFragment(R.id.layout_content, AgendaPerfilUsuario())
                toolbar.setTitle("Minha Agenda")
            }

            R.id.nav_agenda_perfil_estabelecimento -> {
                //Somente visivel para o perfil estabelecimento
                //Mostra os agendamentos que foram confirmados pelos usuários para este estabelecimento

                replaceFragment(R.id.layout_content, AgendaPerfilEstabelecimento())
                toolbar.setTitle("Minha Agenda")
            }


            R.id.nav_serviços_perfil_estabelecimento -> {
                //Somente visível para o perfil estabelecimento
                //Add ou remove horários para os serviços do salão.

                replaceFragment(R.id.layout_content, EditarServicos())
                toolbar.setTitle("Gerenciar Serviços")


            }
            R.id.nav_buscar_servicos_perfil_usuario -> {
                //Somente visível para o perfil usuário.
                //Busca os estabelecimentos próximos.

                replaceFragment(R.id.layout_content, BuscarServicos())
                toolbar.setTitle("Buscar Serviços")



            }
            R.id.nav_perfil -> {
                //Todos os perfils veem
                //Edita detalhes do perfil
                replaceFragment(R.id.layout_content, PerfilFragment())
                toolbar.setTitle("Meu Perfil")

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
