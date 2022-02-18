package com.example.android.videoapp.data.model.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.videoapp.data.model.domain.Video

@Entity(tableName = "VIDEO")
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val remoteUrl: String?,
    val localPath: String,
    val title: String?
)


fun List<VideoEntity>.toDomainModel(): List<Video> {
    return map {
        Video(
            remoteUrl = it.remoteUrl,
            localPath = it.localPath,
            title = it.title
        )
    }
}