package com.example.notes.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao{
    @Query("SELECT * FROM notes")
    fun getAll(): Flow<List<Note>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(note: Note)

    @Delete
    fun delete(note: Note)
}