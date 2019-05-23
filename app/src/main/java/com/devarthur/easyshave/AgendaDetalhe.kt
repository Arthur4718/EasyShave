
package com.devarthur.easyshave


import android.os.Bundle
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.extensions.setupToolbar


class AgendaDetalhe : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda_detalhe)

        val title = intent.getSerializableExtra("titulo") as String
        setupToolbar(R.id.toolbar, title, true)

    }
}
