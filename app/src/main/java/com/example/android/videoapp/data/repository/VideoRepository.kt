package com.example.android.videoapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.android.videoapp.data.model.domain.Video
import com.example.android.videoapp.data.model.domain.toDatabaseModel
import com.example.android.videoapp.data.model.source.local.toDomainModel
import com.example.android.videoapp.database.getDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideoRepository(context: Context) {

    private val database = getDatabase(context.applicationContext)

    val videos: LiveData<List<Video>> = Transformations.map(database.videoDao.getAll()) {
        it.toDomainModel()
    }


    suspend fun insertVideo(video: Video) {
        withContext(Dispatchers.IO) {
            database.videoDao.insert(video.toDatabaseModel())
        }
    }
}