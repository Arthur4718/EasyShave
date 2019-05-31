package com.devarthur.easyshave.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.devarthur.easyshave.R
import com.devarthur.easyshave.adapter.EstabelecimentoAdapter
import com.devarthur.easyshave.dataModel.EstabelecimentoModel
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Toast
import com.google.type.LatLng
import android.location.Geocoder





//Busca os estabelecimentos próximos.
//Ao click do card, abre uma pagina que detalhe os serviços do estabelecimento
//Ao click do serviço do estabelecimento,mostra os horários disponíveis.
class BuscarServicos : Fragment() {

    private val TAG: String? = "arthurdebug"

    //Database
    val db = FirebaseFirestore.getInstance()


    val dataList = ArrayList<EstabelecimentoModel>()


    private var nome = ""
    private var local = ""
    private var salaoUid = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_servicos_perfil_estabelecimento, container, false)
        //Database data
        db.collection("estabelecimento")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    nome = document.getString("nome").toString()
                    local = document.getString("local").toString()
                    salaoUid = document.id

                    createListData(view, nome, local, salaoUid)
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }


        //createListData(view, nome, local)
        return view
    }

    private fun createListData(

        view: View?,
        nome: String,
        local: String,
        salaoUid: String
    ) {

        var mRecyclerView = view?.findViewById<RecyclerView>(R.id.recylerViewEstabelecimentosProximos)
        mRecyclerView?.layoutManager = LinearLayoutManager(this.context, LinearLayout.VERTICAL,false)


            dataList.add(
                EstabelecimentoModel(
                nome,
                  local,
                    "10" + "00",
                    salaoUid))


        val adapter = EstabelecimentoAdapter(dataList)

        mRecyclerView?.adapter = adapter


    }

//    fun getLocationFromAddress(context: Context, inputtedAddress: String): LatLng? {
//
//        val coder = Geocoder(context)
//        val address: List<Address>?
//        var resLatLng: LatLng? = null
//
//        try {
//            // May throw an IOException
//            address = coder.getFromLocationName(inputtedAddress, 5)
//            if (address == null) {
//                return null
//            }
//
//            if (address.size == 0) {
//                return null
//            }
//
//            val location = address[0]
//            location.getLatitude()
//            location.getLongitude()
//
//            resLatLng = LatLng(location.getLatitude(), location.getLongitude())
//
//        } catch (ex: IOException) {
//
//            ex.printStackTrace()
//            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show()
//        }
//
//        return resLatLng
//    }


}
