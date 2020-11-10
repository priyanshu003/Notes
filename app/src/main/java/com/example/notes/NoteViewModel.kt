package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){


    val allNotes:LiveData<List<Note>>
    private val repository: Noterepository

    init {
        val dao = NoteDataBase.getDatabase(application).getNoteDao()
        repository= Noterepository(dao)
        allNotes = repository.allNottes

    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }

}