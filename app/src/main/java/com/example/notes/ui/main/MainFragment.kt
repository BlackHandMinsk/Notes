package com.example.notes.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.MainFragmentBinding
import com.example.notes.room.Note
import kotlinx.coroutines.flow.onEach

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }


    private val viewModel:MainViewModel by viewModels()
    private val adapter:NotesAdapter? get() = views { notesList.adapter as? NotesAdapter }

    private var binding:MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = MainFragmentBinding.inflate(inflater).also {
        binding = it
    }.root



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        views{
            notesList.adapter = NotesAdapter()
            SwipeHelper(viewModel::delete).attachToRecyclerView(notesList)
            addButton.setOnClickListener { saveNote() }
        }


        viewModel.notes.onEach(::renderNotes)
    }

    private fun saveNote() {
        views {
           val noteText =  addNoteEditText.text.toString().takeIf { it.isNotBlank() }?:return@views

            viewModel.save(noteText)
        }
    }


    private fun renderNotes(notes:List<Note>){
        adapter?.submitList(notes)
    }



    private fun <T> views(block:MainFragmentBinding.()->T):T?=binding?.block()

}


class SwipeHelper(onSwiped:(Note)->Unit):ItemTouchHelper(SwipeCallBack(onSwiped)){

    class SwipeCallBack(
        private val onSwiped:(Note)->Unit
    ) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            (viewHolder as? NoteViewHolder)?.item?.let { onSwiped(it) }
        }

    }
}


