package com.logical.mydictionary.ui.saved

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.logical.mydictionary.data.database.entities.WordsEntity
import com.logical.mydictionary.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _readSavedWords: LiveData<List<WordsEntity>> =
        repository.loacl.readWords().asLiveData()
    val readSavedWords: LiveData<List<WordsEntity>> = _readSavedWords

}