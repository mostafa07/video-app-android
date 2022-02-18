package com.example.android.videoapp.data.model.domain

import com.example.android.videoapp.data.model.source.local.VideoEntity

data class Video(
    val remoteUrl: String?,
    val localPath: String,
    val title: String?
)

fun Video.toDatabaseModel(): VideoEntity {
    return VideoEntity(
        remoteUrl = this.remoteUrl,
        localPath = this.localPath,
        title = this.title
    )
}