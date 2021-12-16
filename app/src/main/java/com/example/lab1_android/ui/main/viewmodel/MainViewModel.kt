package com.example.lab1_android.ui.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab1_android.R
import com.example.lab1_android.data.api.ApiInterface
import com.example.lab1_android.data.model.CharacterDataWrapper
import com.example.lab1_android.data.model.SingleCharacter
import com.example.lab1_android.data.repository.SingleCharacterRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SingleCharacterRepository,
    private val api: ApiInterface
) : ViewModel() {
    val error: MutableLiveData<String> = MutableLiveData()
    var characters: MutableLiveData<List<SingleCharacter>> = MutableLiveData()

    // не должна вызываться при пвороте экрана поэтому будет жить во вью модели
    init {
        getAllCharacters()
        fetchFCMToken()
    }

    private fun getAllCharacters() {
        // в прошлый раз забыл инжектнуть хотя запровайдил в модуле и
        api.getCharacters().enqueue(object : Callback<CharacterDataWrapper> {
            override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                // load from local
                characters.postValue(repository.characterList)
            }

            override fun onResponse(
                call: Call<CharacterDataWrapper>,
                response: Response<CharacterDataWrapper>
            ) {
                if (response.code() == 200) {
                    response.body()?.data?.results?.let { characters.postValue(it) }
                    // save
                    response.body()?.data?.results?.let { saveToDb(it) }
                }
                if (response.code() == 404) {
                    error.postValue("getString(R.string.Code404)")
                    characters.postValue(repository.characterList)
                }
                Log.i("Response", response.body().toString())
            }
        })
    }

    private fun saveToDb(list: List<SingleCharacter>) {
        runBlocking {
            withContext(Dispatchers.IO) {
                repository.insertList(list)
            }
        }
    }

    private fun fetchFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            token?.let { sendTokenToServer(it) }
        })
    }

    private fun sendTokenToServer(token: String) {
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        val ref = db.getReference("server/saving-data/IDs")
        ref.push().setValue(token)
    }
}