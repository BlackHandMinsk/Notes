package com.example.notes.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.NoteListItemBinding
import com.example.notes.room.Note

class NoteViewHolder(
    private val binding:NoteListItemBinding
):RecyclerView.ViewHolder(binding.root) {

    var item:Note? = null
    private set

    fun onBind(item: Note){
        this.item = item
        views {
            captionTextView.text = item.caption
            noteTextView.text = item.text
        }
    }


    private fun <T> views(block:NoteListItemBinding.()->T):T? = binding.block()

    companion object{
        fun create(parent: ViewGroup) = NoteListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        ).let(::NoteViewHolder)
    }
}