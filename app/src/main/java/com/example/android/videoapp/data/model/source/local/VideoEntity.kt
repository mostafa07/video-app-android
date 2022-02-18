package com.example.android.videoapp.data.model.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.videoapp.data.model.domain.Video

@Entity(tableName = "VIDEO")
data class VideoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val remoteUrl: String,
    val localPath: String,
    val title: String
) {
    constructor(
        remoteUrl: String,
        localPath: String,
        title: String
    ) : this(
        id = Int.MIN_VALUE,
        remoteUrl = remoteUrl,
        localPath = localPath,
        title = title
    )
}


fun List<VideoEntity>.toDomainModel(): List<Video> {
    return map {
        Video(
            remoteUrl = it.remoteUrl,
            localPath = it.localPath,
            title = it.title
        )
    }
}