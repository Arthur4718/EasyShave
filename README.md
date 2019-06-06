# EasyShave

App desenvolvido para busca e gestão de serviços. 

![](https://i.imgur.com/4OmoBc6.jpg)


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
        
        //Entre {} a ação que deve ser excutada ao click. StartActivity inicia uma nova tela, a partir desta. 
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


# Enviando informações para o banco. 

Todo chamada com o banco precisa do ID do usuário e de uma referência a base de dados. 

```kotlin
    //User Info 
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
    //Database
    private val db = FirebaseFirestore.getInstance()

```

Para gravar informações no firebase, criamos um objeto no mesmo formato que o banco de dados possui. Por exemplo, a tabela
estabelecimento contem: 

* nome
* local
* userUid
* email

Se formos cadastrar um novo estabelecimento, criamos um objeto com características idênticas, e armazenamos esses novos valores no objeto. 

```kotlin
val novoEstabelecimento = HashMap<String, Any>()
        novoEstabelecimento["local"] = editText_endereco.text.toString()
        novoEstabelecimento["nome"] =  editText_username.text.toString()
        novoEstabelecimento["email"] =  editText_email.text.toString()
        novoEstabelecimento["userUid"] = FirebaseAuth.getInstance().uid.toString()

```

Em seguida, usando a referencia ao banco de dados, chamamos o metodo db.collection.add para passar os novos dados, o método nos informa se a operação foi concluida ou se houve erro. 

```kotlin
private fun updateFireStore() {

       val novoEstabelecimento = HashMap<String, Any>()
        novoEstabelecimento["local"] = editText_endereco.text.toString()
        novoEstabelecimento["nome"] =  editText_username.text.toString()
        novoEstabelecimento["email"] =  editText_email.text.toString()
        novoEstabelecimento["userUid"] = FirebaseAuth.getInstance().uid.toString()

        //Salva na tabela estabelecimento - desse modo aparece na listagem para o usuário comum
        db.collection("estabelecimento")
            .add(estabelecimento)
            .addOnSuccessListener { documentReference ->
                //Ações caso a operação funcione
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                toast("data updated")
            }
            .addOnFailureListener { e ->
                //Ações caso a operação não funcione
                Log.w(TAG, "Error adding document", e)

                toast("Failure when updating... $e")
            }
    }
```

O método toast() é um alerta de sistema, caso algo de errado, o toast avisa e envia a mensagem de erro para o usuário. 

# Lendo informações do banco.

A referência permite a leitura do bd através do meto get(), onde é preciso informar o nome da tabela e os filtros da busca se necessário. 

```kotlin
//Database data
        db.collection("userAgendamento")
            .whereEqualTo("uid", querryUid) //Condição de busca - encontre os agendamentos que possuem o meu ID.
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //Log.d("agendadebug" , "document: ${document.data}")


                    nome = document.getString("username").toString()
                    servico = document.getString("servico").toString()
                    valor = document.getString("valor").toString()
                    data = document.getString("data").toString()
                    hora = document.getString("hora").toString()
                    status = ""
                    //Carrega os dados na tela. 
                    createListAgendamento(view, nome,servico, valor, data, hora, status) 

                }
            }
            .addOnFailureListener { exception ->
                //Informao usuário caso aconteça algum erro de conexão. 
                toast("Erro ao conectar ao banco $exception")

            } 
            
```            


# PagSeguro. 

Implementar a api de pagamentos do pag seguro é preciso acessar o sdk do desenvolvedor. No nosso arquivo de configuração e ele adicionado dessa forma: 

```kotlin
    implementation 'br.com.uol.pslibs:checkout-in-app:0.0.9'

``` 

Os tokens de acesso para teste são fornecidos no painel administrativo na área do desenvolvedor pag seguro. Sem estas informações as requisições com o pagseguro não funcionam. No modo sandbox(modo de teste), ele costuma apresentar um erro com o vendedor quando você não possui um cadastro. 

```kotlin
    private static final String SELLER_EMAIL = "devarthur4718@gmail.com";
    private static final String SELLER_TOKEN = "31C3F3E9281D4781A468722B0B9EFD19";
    private final String NOTIFICATION_URL_PAYMENT = "https://pagseguro.uol.com.br/lojamodelo-qa/RetornoAutomatico-OK.jsp";
 ``` 

Quando o clicamos em pagar, iniciamos a caixa de dialogo do pagSeguro, ela vai buscar informações adicionais sobre pagamento e valor do produto. 

```kotlin
private void initWallet(){
        //Inicialização a lib com parametros necessarios
        PSCheckoutConfig psCheckoutConfig = new PSCheckoutConfig();
        psCheckoutConfig.setSellerEmail(SELLER_EMAIL);
        psCheckoutConfig.setSellerToken(SELLER_TOKEN);
        //Informe o fragment container
        psCheckoutConfig.setContainer(R.id.fragment_container);

        //Inicializa apenas os recursos da carteira
        PSCheckout.initWallet(getActivity(), psCheckoutConfig);
    }

 ``` 

Alguns recursos como parcelamento e algumas bandeiras de crédito são limitados na versão sandbox. 
