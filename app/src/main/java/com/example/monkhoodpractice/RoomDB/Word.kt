package com.example.monkhoodpractice.RoomDB

import android.text.Spanned
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val uid:Int,
    @ColumnInfo(name = "id") val ID: String,
    @ColumnInfo(name = "username") val name: String,
    @ColumnInfo(name = "password") val pass: String,

    )
