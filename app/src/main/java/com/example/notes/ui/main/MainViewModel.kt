package com.example.notes.ui.main

import androidx.lifecycle.ViewModel
import com.example.notes.room.Repository

class MainViewModel : ViewModel() {
   private val repository:Repository by locateLazy()
}