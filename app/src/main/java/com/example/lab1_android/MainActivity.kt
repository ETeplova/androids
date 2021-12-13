package com.example.lab1_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_android.data.CharacterDataWrapper
import com.example.lab1_android.data.SingleCharacter
import com.example.lab1_android.data.api.ApiInterface
import com.example.lab1_android.data.api.AppDatabase
import com.example.lab1_android.data.api.RetrofitFactory
import com.example.lab1_android.data.repository.SingleCharacterRepository
import com.example.lab1_android.utils.RecyclerViewClickListener
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    private var recyclerView: RecyclerView? = null
    private var repository: SingleCharacterRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val db = AppDatabase.getAppDataBase(this)
        repository = SingleCharacterRepository(db!!.singleCharacterDao())
        recyclerView = findViewById(R.id.image_cards)

        getAllCharacters()
    }

    private fun getAllCharacters() {
        RetrofitFactory.api().getCharacters().enqueue(object : Callback<CharacterDataWrapper> {
            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                //showError(t.message)
                // load from local
                initAdapter(repository?.characterList!!)
            }

            override fun onResponse(
                call: Call<CharacterDataWrapper>,
                response: Response<CharacterDataWrapper>
            ) {
                if(response.code() == 200) {
                    response.body()?.data?.results?.let { initAdapter(it) }
                    // save
                    response.body()?.data?.results?.let { saveToDb(it) }
                }
                if(response.code() == 404){
                    showError(getString(R.string.Code404))
                    initAdapter(repository?.characterList!!)
                }
                Log.i("Response", response.body().toString())
            }
        })
    }

    private fun saveToDb(list: List<SingleCharacter>) {
        runBlocking {
            withContext(Dispatchers.IO) {
                repository?.insertList(list)
            }
        }
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
