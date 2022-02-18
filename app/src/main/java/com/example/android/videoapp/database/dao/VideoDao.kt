package com.example.android.videoapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.videoapp.data.model.source.local.VideoEntity

@Dao
interface VideoDao {

    @Query("SELECT * FROM VIDEO")
    fun getAll(): LiveData<List<VideoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(video: VideoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: VideoEntity)
}