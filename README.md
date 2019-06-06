# EasyShave

Sobre o projeto. 

Mostrar duas detas. 

# Estrutura do projeto



![alt text](https://i.imgur.com/4OmoBc6.jpg)


# Tela inicial e login. 

A primeira tela a ser carregada no app é a WellcomeActivity. Ela possui um timer para disparar a tela de login do usuário. 

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

Na tela seguinte, implementamos o cadastro, a reucperação de senha e o login através do objeto FirebaseAuth, com esse 
sdk podemos gerenciar a tabela users no Firebase Console. 

```kotlin
package com.devarthur.easyshave.activity.RegisterActivities

import android.os.Bundle
import android.util.Log
import com.devarthur.easyshave.R
import com.devarthur.easyshave.activity.BaseActivities.BaseActivity
import com.devarthur.easyshave.activity.User.MainMenuActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_email.*
import org.jetbrains.anko.*

//Criar a tela de login do usuário e permite acesso a recuperação de senha ou criação de conta.
class LoginEmailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_email)

        initActions()
        debug()

    }

    //Add eventos de click a este arquivo.
    private fun initActions() {

        btnSignIn.setOnClickListener { logWithUserData()}

        txtEsqueciSenha.setOnClickListener {startActivity<RecoverPassword>() }

        txtCadastro.setOnClickListener { startActivity<CreateAccount>() }

    }

    //Debug - carrega uma conta padrão para ser carregada na tela de login, deixe o espaço em branco caso não queira utilizar
    private fun debug(){
//         edtUserNameSignUp.setText("arthur.gomes_4718@hotmail.com")
//         edtUserPassword.setText("123456")
    }

    //Lê e-mail e senha digitados na tela e envia para o firebase login.
    private fun logWithUserData() {

        //Indica ao usuário que o processo com o banco se iniciou .
        val progressDialog = indeterminateProgressDialog ("Verificando conta")

        //Acessando dos campos da tela e pegando seu conteúdo.
        val email = edtUserNameSignUp.text.toString()
        val pw = edtUserPassword.text.toString()

        //FirebaseAuth é o objeto responsável por autenticar logins, recebe e-mail e pw como parâmetro.
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pw)
            .addOnCompleteListener {//A partir desta linha são executadas as ações caso o login tenha sido feito com sucesso.
                if(!it.isSuccessful) return@addOnCompleteListener

                Log.d("Login", "User logged with uid: ${it.result?.user?.uid}")
                progressDialog.dismiss() //Encerra a barra de progresso.
                startActivity(intentFor<MainMenuActivity>().newTask().clearTask())



            }.addOnFailureListener {//Caso o login tenha falhado, exibe a mensagem com o erro
                Log.d("Login", "Failed to log user : ${it.message}")
                alert("Erro - Este usuário não existe").show()
                progressDialog.dismiss()
            }
    }
}


```

# Library utilzadas


# Banco 



