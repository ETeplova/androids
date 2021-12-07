package com.example.lab1_android

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1_android.data.CharacterDataWrapper
import com.example.lab1_android.data.SingleCharacter
import com.example.lab1_android.data.api.RetrofitFactory
import com.example.lab1_android.utils.GlideUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OneCharacterActivity : AppCompatActivity() {
    private var characterId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.one_character)

        characterId = intent.getIntExtra("CHARACTER_ID", 0)
        fetchCharacter()
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun fetchCharacter() {
        RetrofitFactory.api().getCharacter(characterId!!).enqueue(object : Callback<CharacterDataWrapper> {
                override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                    showError(t.message)
                }

                override fun onResponse(
                    call: Call<CharacterDataWrapper>,
                    response: Response<CharacterDataWrapper>
                ) {
                    response.body()?.data?.results?.get(0)?.let { initUi(it) }
                }
            })
    }

    private fun initUi(it: SingleCharacter) {
        val full = findViewById<ImageView>(R.id.imageView)
        val descr = findViewById<TextView>(R.id.textView)
        val circle = findViewById<ImageView>(R.id.sign)
        descr.text = it.name

        val imageUrl = it.thumbnail?.path + "." + it.thumbnail?.extension
        GlideUtil.setImageRound(circle, imageUrl)
        GlideUtil.setImage(full, imageUrl)
    }
}