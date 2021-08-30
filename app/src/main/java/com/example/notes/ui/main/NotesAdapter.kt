package com.example.notes.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.notes.room.Note

class NotesAdapter : ListAdapter<Note, NoteViewHolder>(NoteDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder = NoteViewHolder.create(parent)

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) = holder.onBind(getItem(position))
}