package com.devarthur.easyshave.activity.BaseActivities


import android.os.Bundle
import com.devarthur.easyshave.R
import com.devarthur.easyshave.domain.tipoCarro
import com.devarthur.easyshave.extensions.addFragment
import com.devarthur.easyshave.extensions.setupToolbar
import com.devarthur.easyshave.fragments.CarrosFragment

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
