package com.example.notes.room

import kotlinx.coroutines.flow.Flow

class Repository(
    private val db:NotesDatabase
) {
    private val dao get() = db.notesDao

    fun getAll(): Flow<List<Note>> = dao.getAll()

    suspend fun save(note: Note) = dao.add(note)

    suspend fun delete(note: Note) = dao.delete(note)
}