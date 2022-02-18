package com.example.android.videoapp.ui.video

import android.app.Application
import androidx.lifecycle.*
import com.example.android.videoapp.R
import com.example.android.videoapp.data.model.app.CustomMessage
import com.example.android.videoapp.data.model.domain.Video
import com.example.android.videoapp.data.repository.VideoRepository
import com.example.android.videoapp.exception.BusinessException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * This is a shared View Model used by the fragments
 * {@link VideoCaptureFragment} and {@link VideoCaptureFragment}.
 */
class VideoViewModel(application: Application) : AndroidViewModel(application) {

    private val videoRepository = VideoRepository(application)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _videos = videoRepository.videos
    val videos: LiveData<List<Video>>
        get() = _videos

    private val _successMessage: MutableLiveData<CustomMessage> = MutableLiveData()
    val successMessage: LiveData<CustomMessage>
        get() = _successMessage

    private val _errorMessage: MutableLiveData<CustomMessage> = MutableLiveData()
    val errorMessage: LiveData<CustomMessage>
        get() = _errorMessage

    private val _isContentLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isContentLoading: LiveData<Boolean>
        get() = _isContentLoading


    init {
        viewModelScope.launch {
            showLoading()

            // TODO load videos from database
            //  and then later from remote web service

            hideLoading()
        }
    }

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }


    private fun setSuccessMessage(message: CustomMessage) {
        _successMessage.value = message
    }

    private fun setErrorMessage(errorMessage: CustomMessage) {
        _errorMessage.value = errorMessage
    }

    private fun setErrorMessage(t: Throwable) {
        if (t is BusinessException) {
            setErrorMessage(t.businessMessage)
        } else {
            t.printStackTrace()
            setErrorMessage(CustomMessage(R.string.operation_failed))
        }
    }

    private fun showLoading() {
        _isContentLoading.value = true
    }

    private fun hideLoading() {
        _isContentLoading.value = false
    }


    // TODO research to see if this is really needed for passing application argument
    //  to the view model and remove it otherwise
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideoViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}