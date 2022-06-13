package com.appa.model.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

    @Parcelize
// Define the Table name
    @Entity(tableName = "message_table")
    data class Message(
        @ColumnInfo(name = "message") val message: String,
        @PrimaryKey(autoGenerate = true) val id: Int = 0
    ) : Parcelable {
        override fun toString(): String {
            return "message='$message', id=$id)"
        }
    }
