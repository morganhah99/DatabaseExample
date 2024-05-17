package com.logical.mydictionary.ui.search

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.logical.mydictionary.data.database.entities.WordsEntity
import com.logical.mydictionary.model.ResultModel
import com.logical.mydictionary.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _longForm = MutableLiveData<ResultModel>()
    val longForm: LiveData<ResultModel> = _longForm

    private val _readSavedWords: LiveData<List<WordsEntity>> =
        repository.loacl.readWords().asLiveData()
    val readSavedWords: LiveData<List<WordsEntity>> = _readSavedWords

    fun getAcronym(shortForm: String) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.remote.getAcronym(shortForm)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _longForm.postValue(it)
                        saveToWordsTable(it)
                    } ?: throw Exception("Data null")

                } else {
                    throw Exception(response.errorBody()?.toString())
                }


            } catch (e: Exception) {
                Log.e("error", e.toString())
            }

        }


    private fun saveToWordsTable(newModel: ResultModel) {
        val wordsEntity = WordsEntity(newModel)
        insertWords(wordsEntity)

    }

    private fun insertWords(wordsEntity: WordsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loacl.insertWords(wordsEntity)

        }

    }

}