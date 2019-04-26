package com.supermatch.smplay.activity


import android.os.Bundle
import com.supermatch.smplay.R
import com.supermatch.smplay.domain.tipoCarro
import com.supermatch.smplay.extensions.addFragment
import com.supermatch.smplay.extensions.setupToolbar
import com.supermatch.smplay.fragments.CarrosFragment

class CarrosActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)

        //get values from bundle and use it to construct your fragment behavior/title
        val type = intent.getSerializableExtra("tipo") as tipoCarro
        val title = getString(type.string)

        //Toolbar : set up toolbar title and "up navigation"
        setupToolbar(R.id.toolbar, title, true)


        if(savedInstanceState == null){
            //Adds fragment to the layout
            addFragment(R.id.container, CarrosFragment())
        }

    }
}
