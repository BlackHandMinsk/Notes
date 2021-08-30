package com.example.notes.ui.main


import android.icu.text.MessageFormat.format
import android.text.format.DateFormat.format
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.notes.locateLazy
import com.example.notes.room.Note
import com.example.notes.room.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted

import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch

import java.text.DateFormat
import java.text.MessageFormat.format

import java.util.*

class MainViewModel : ViewModel() {

   private val repository:Repository by locateLazy()

   val notes  = repository.getAll().asLiveDataFlow()

   fun save(note: String){
      viewModelScope.launch {
         repository.save(createNote(note))
      }
   }

   fun delete(note: Note){
      viewModelScope.launch {
         repository.delete(note)
      }
   }

   private fun createNote(noteText:String) = Note(caption = createCaption(),
   text = noteText)

   private fun createCaption(): String = DateFormat.DATE_FIELD.toString()

   fun <T> Flow<T>.asLiveDataFlow() = shareIn(viewModelScope, SharingStarted.Eagerly,replay = 1)
}