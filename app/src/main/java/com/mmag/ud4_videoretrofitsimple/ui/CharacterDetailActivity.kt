package com.mmag.ud4_videoretrofitsimple.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mmag.ud4_videoretrofitsimple.R
import com.mmag.ud4_videoretrofitsimple.databinding.ActivityCharacterDetailBinding
import com.mmag.ud4_videoretrofitsimple.network.APIManager
import com.mmag.ud4_videoretrofitsimple.network.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCharacterDetailBinding
    private val binding: ActivityCharacterDetailBinding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val characterID = getCharacterId()
        if (characterID != null) {
            getCharacterFromAPI(characterID)
        } else {
            Toast.makeText(this, "no se ha recibido un id", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getCharacterFromAPI(characterID: Int) {
        val call = APIManager.service.getSingleCharacter(characterID)
        call.enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()
                    showCharacterData(data)
                } else {
                    Toast.makeText(this@CharacterDetailActivity, "Algo fue mal", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Toast.makeText(this@CharacterDetailActivity, "Algo fue mal", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun showCharacterData(data: Character?) {
        binding.tvDetailName.text = data!!.name
        binding.tvOriginDetail.text = "Origen: ${data.origin.name}"
        binding.tvStatusDetail.text = "Status: ${data.status}"
        binding.tvSpecieDetail.text = "Specie: ${data.species}"
        Glide.with(this@CharacterDetailActivity)
            .load(data.image)
            .into(binding.ivDetailPhoto)
    }

    private fun getCharacterId(): Int? {
        if (intent.hasExtra("id")) {
            return intent.getIntExtra("id", 0)
        } else {
            return null
        }
    }
}