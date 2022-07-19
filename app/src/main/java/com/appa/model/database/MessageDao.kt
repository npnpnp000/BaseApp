package com.appb.model.database.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.appa.model.entities.Message
import kotlinx.coroutines.flow.Flow


@Dao
interface MessageDao {

    // add message to DB
    @Insert
    suspend fun insertMessage(message: Message)

    // get all the message from DB
    @Query("SELECT * FROM message_table ORDER BY id")
    fun getAllMessagesList() : Flow<List<Message>?>
}