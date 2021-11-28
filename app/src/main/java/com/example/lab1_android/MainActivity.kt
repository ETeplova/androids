package com.example.lab1_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_android.data.CharacterDataWrapper
import com.example.lab1_android.data.SingleCharacter
import com.example.lab1_android.data.api.ApiInterface
import com.example.lab1_android.data.api.RetrofitFactory
import com.example.lab1_android.utils.RecyclerViewClickListener
import retrofit2.*

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    private var recyclerView: RecyclerView? = null
    var apiInterface: ApiInterface? = null
    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerView = findViewById<RecyclerView>(R.id.image_cards)
        // у нас должнен быть один инстанс апи на все активности и он должен инжжектиться

        getAllCharacters()
    }

    private fun getAllCharacters() {
        RetrofitFactory.api().getCharacters().enqueue(object : Callback<CharacterDataWrapper> {
            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                showError(t.message)
            }
            override fun onResponse(call: Call<CharacterDataWrapper>, response: Response<CharacterDataWrapper>) {
                //Log.i("Response", response.body().toString())
                response.body()?.data?.results?.let { initAdapter(it) }
            }
        })
    }
    private fun initAdapter(list: List<SingleCharacter>) {
        recyclerView?.adapter = Adapter(list, this)
        recyclerView?.setHasFixedSize(true)
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onClick(view: View?, position: Int, id: Int) {
        // передать в интент какого фетчить
        val intent = Intent(this, OneCharacterActivity::class.java)
        intent.putExtra("CHARACTER_ID", id)
        startActivity(intent)
    }
}
