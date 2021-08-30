package com.example.notes.room

import androidx.room.*



@Entity (tableName = "notes")
data class Note(
 @PrimaryKey  val id:Int = 0,
val caption:String,
val text:String
)

