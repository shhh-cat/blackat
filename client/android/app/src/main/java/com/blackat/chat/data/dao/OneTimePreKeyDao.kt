package com.blackat.chat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blackat.chat.data.model.*
import org.signal.libsignal.protocol.state.PreKeyRecord

@Dao
interface OneTimePreKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(oneTimePreKey: OneTimePreKey)

    @Query("SELECT * FROM one_time_pre_key WHERE keyId = :keyId")
    suspend fun get(keyId: Int): OneTimePreKey

    @Query("SELECT EXISTS (SELECT * FROM one_time_pre_key WHERE keyId = :keyId)")
    suspend fun contain(keyId: Int): Boolean

    @Query("DELETE FROM one_time_pre_key WHERE keyId = :keyId")
    suspend fun delete(keyId: Int)
}