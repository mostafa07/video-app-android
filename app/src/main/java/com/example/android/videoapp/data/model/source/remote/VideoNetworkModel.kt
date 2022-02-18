package com.example.android.videoapp.data.model.source.remote

import com.example.android.videoapp.data.model.domain.Video
import com.example.android.videoapp.data.model.source.local.VideoEntity

data class VideoNetworkModel(
    val remoteUrl: String?,
    val localPath: String,
    val title: String?
)


fun List<VideoNetworkModel>.toDomainModel(): List<Video> {
    return map {
        Video(
            remoteUrl = it.remoteUrl,
            localPath = it.localPath,
            title = it.title
        )
    }
}

fun List<VideoNetworkModel>.toDatabaseModel(): Array<VideoEntity> {
    return map {
        VideoEntity(
            remoteUrl = it.remoteUrl,
            localPath = it.localPath,
            title = it.title
        )
    }.toTypedArray()
}