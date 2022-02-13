package com.example.android.videoapp.exception

import com.example.android.videoapp.data.model.app.CustomMessage

class BusinessException(val businessMessage: CustomMessage) : Exception()