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
import com.devarthur.easyshave.dataModel.UserProfile

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
    val userType : Int = 0

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
        //setupBottomNavigation()

        //Loads the first page
        if(savedInstanceState == null){
            //Adds fragment to the layout
            addFragment(R.id.layout_content, AgendaFragment())
            toolbar.setTitle("Minha Agenda")

        }
    }

//    private fun setupBottomNavigaprivate fun setupBottomNavigation() {
////        val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
////
////            when(item.itemId){
////                R.id.id_nav_item_play -> {
////                    toast("play menu pressed")
////                    replaceFragment(R.id.layout_container, PlayFragment())
////                    return@OnNavigationItemSelectedListener true
////                }
////
////                R.id.id_nav_item_profile -> {
////                    toast("profile menu pressed")
////                    replaceFragment(R.id.layout_container, ProfileFragment())
////                    return@OnNavigationItemSelectedListener true
////                }
////
////                R.id.id_nav_item_bank -> {
////                    toast("bank menu pressed")
////                    replaceFragment(R.id.layout_container, BankFragment())
////                    return@OnNavigationItemSelectedListener true
////                }
////                R.id.id_nav_item_tutorials -> {
////                    toast("tutorials menu pressed")
////                    return@OnNavigationItemSelectedListener true
////                }
////
////            }
////            false
////        }
////
////        //Setting up bottom navigation listener
////        bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
////    }tion() {
//        val mNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
//
//            when(item.itemId){
//                R.id.id_nav_item_play -> {
//                    toast("play menu pressed")
//                    replaceFragment(R.id.layout_container, PlayFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//
//                R.id.id_nav_item_profile -> {
//                    toast("profile menu pressed")
//                    replaceFragment(R.id.layout_container, ProfileFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//
//                R.id.id_nav_item_bank -> {
//                    toast("bank menu pressed")
//                    replaceFragment(R.id.layout_container, BankFragment())
//                    return@OnNavigationItemSelectedListener true
//                }
//                R.id.id_nav_item_tutorials -> {
//                    toast("tutorials menu pressed")
//                    return@OnNavigationItemSelectedListener true
//                }
//
//            }
//            false
//        }
//
//        //Setting up bottom navigation listener
//        bottomNavBar.setOnNavigationItemSelectedListener(mNavigationItemSelectedListener)
//    }

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
        val ref = FirebaseDatabase.getInstance().getReference("/users/$userUId")

        ref.addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {


                //Log.d("arthurdebug", p0.toString())
                //Log.d("arthurdebug", p0.child("userType").toString())
                //Log.d("arthurdebug", p0.getValue(p0.key.equals("userType")).toString())



                //val data = p0.getValue(UserProfile::class.java)
                //og.d("arthurdebug", data?.userType)



                //Log.d("arthurdebug",  p0.toString())
                //Log.d("arthurdebug",   p0.key.toString())


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
}
