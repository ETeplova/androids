package com.example.lab1_android.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab1_android.data.api.ApiInterface
import com.example.lab1_android.data.model.CharacterDataWrapper
import com.example.lab1_android.data.model.SingleCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class OneCharacterViewModel @Inject constructor(private val api: ApiInterface) : ViewModel() {
    val error: MutableLiveData<String> = MutableLiveData()
    var character: MutableLiveData<SingleCharacter> = MutableLiveData()

    fun fetchCharacter(characterId: Int) {
        api.getCharacter(characterId)
            .enqueue(object : Callback<CharacterDataWrapper> {
                override fun onFailure(call: Call<CharacterDataWrapper>, t: Throwable) {
                    error.postValue(t.message)
                }

                override fun onResponse(
                    call: Call<CharacterDataWrapper>,
                    response: Response<CharacterDataWrapper>
                ) {
                    response.body()?.data?.results?.get(0)?.let { character.postValue(it) }
                }
            })
    }
}