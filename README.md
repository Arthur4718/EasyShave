# EasyShave

Sobre o projeto. 

Mostrar duas detas. 

# Estrutura do projeto




# Tela inicial e login. 

A primeira tela a ser carregada no app é a WellcomeActivity. Ela possui um timer par 

```kotlin
package com.devarthur.easyshave.activity.RegisterActivities

import android.os.Bundle
import android.os.Handler
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import org.jetbrains.anko.startActivity

//Primeira atividade do projeto - Carrega a splash screen para seguir para a área de login.
class WellcomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome)

    }


    override fun onResume() {
        super.onResume()


        //Tempo minimo da tela de splash.
        val timer : Long = 1000
        val handler = Handler()
        handler.postDelayed({
            //Do something after n Seconds
            //Inicia a proxima tela e na sequência encerramos a execução.
            startActivity<LoginEmailActivity>()
            finish()


        }, timer)

    }
}

```




# Library utilzadas


# Banco 



