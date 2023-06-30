package com.example.dbsqlite_v01

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.dbsqlite_v01.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val retrofit =Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.0.2.2/").build().create(MainActivity.enviaUsuario::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //var context= this
        binding.btnCadastrar.setOnClickListener {
            val nome = binding.eNome.text.toString().trim()
            val senha = binding.eFone.text.toString().trim()
            if (nome.isEmpty() || senha.isEmpty()){
                Toast.makeText(applicationContext,"Favor digitar o Nome e Fone", Toast.LENGTH_SHORT).show()
            }else{
                retrofit.setUsuario(nome,senha).enqueue(object : Callback<User>{
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.d("Erro", t.toString())
                    }
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                       if(response.isSuccessful){
                           response.body()?.let {
                               if(response.body()!!.nome.equals("vazio")){
                                   Toast.makeText(applicationContext,"resposta Vazio", Toast.LENGTH_SHORT).show()
                               }else{
                                   var c=response.body()!!.nome
                                   Toast.makeText(applicationContext,"Autenticado $c", Toast.LENGTH_SHORT).show()

                                   }
                           }

                       }
                    }

                })
                /*
                // below we have created
                // a new DBHelper class,
                // and passed context to it
                val db = DBHelper(this, null)
                // calling method to add
                // name to our database
                db.addName(nome, fone)

                // Toast to message on the screen
                Toast.makeText(this, nome + " added to database", Toast.LENGTH_LONG).show()

                // at last, clearing edit texts
                binding.eNome.text.clear()
                binding.eFone.text.clear()

            }
               */
         }

        /*
        binding.btnListar.setOnClickListener {
            // creating a DBHelper class
            // and passing context to it
            binding.txtNome.setText("")
            binding.txtFone.setText("")

            val db = DBHelper(this, null)

            // below is the variable for cursor
            // we have called method to get
            // all names from our database
            // and add to name text view
            val cursor = db.getName()

            // moving the cursor to first position and
            // appending value in the text view
            cursor!!.moveToFirst()
            binding.txtNome.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            binding.txtFone.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            /*
            Attributes.Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
            Age.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")

             */

            // moving our cursor to next
            // position and appending values
            while (cursor.moveToNext()) {
                binding.txtNome.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
                binding.txtFone.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL)) + "\n")
            }

            // at last we close our cursor
            cursor.close()
        }
        binding.btnLimpar.setOnClickListener {
            binding.txtNome.setText("")
            binding.txtFone.setText("")
        }

        //Instaciando DB

        val db = DBHelper(this, null)

        val listaUtilizadores =db.utilizadorListSelectAll()

        binding.listUser.adapter= ArrayAdapter(this, R.layout.simple_list_item_1,listaUtilizadores)

         */
        }
     }
    interface enviaUsuario{
        @FormUrlEncoded
        @POST("bd_crud.php")
        fun setUsuario(@Field("nome") nome:String,
                       @Field("senha") senha:String): Call<User>
        //-------- FIM ------
      }
    }


