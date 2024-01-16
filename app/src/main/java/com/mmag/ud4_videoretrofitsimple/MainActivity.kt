package com.mmag.ud4_videoretrofitsimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmag.ud4_videoretrofitsimple.databinding.ActivityMainBinding
import com.mmag.ud4_videoretrofitsimple.network.APIManager
import com.mmag.ud4_videoretrofitsimple.network.model.AllCharactersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding

    private val  adapter = CharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvCharacters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.rvCharacters.adapter = adapter

        getCharactersFromAPI()
    }

    private fun getCharactersFromAPI() {
        val call = APIManager.service.getAllCharacters()

        call.enqueue(object : Callback<AllCharactersResponse> {
            override fun onResponse(
                call: Call<AllCharactersResponse>,
                response: Response<AllCharactersResponse>
            ) {
                if (response.isSuccessful) {
                    //Recogemos la respuesta del sever
                    val body = response.body()
                    if (body != null) {
                        //Añadimos la respuesta a nuestra RV
                        adapter.submitList(body.results)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Ha fallado la petición", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<AllCharactersResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ha fallado la petición", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
}