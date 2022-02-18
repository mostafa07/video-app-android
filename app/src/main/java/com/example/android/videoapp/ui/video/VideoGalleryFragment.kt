package com.example.android.videoapp.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.videoapp.R
import com.example.android.videoapp.databinding.FragmentVideoGalleryBinding
import com.example.android.videoapp.extension.disableUserInteraction
import com.example.android.videoapp.extension.reEnableUserInteraction
import com.example.android.videoapp.extension.showSnackbar
import com.example.android.videoapp.ui.adapter.VideoAdapter

class VideoGalleryFragment : Fragment() {

    private var _viewBinding: FragmentVideoGalleryBinding? = null
    private val viewBinding get() = _viewBinding!!

    // TODO research how to use factory for initializing this view model if needed
    private val viewModel: VideoViewModel by activityViewModels()

    private var videoAdapter: VideoAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_video_gallery,
            container,
            false
        )

        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.viewModel = viewModel

        videoAdapter = VideoAdapter { video, _ ->
            // TODO navigate to video preview fragment or create an intent to open video in player
//            val directions = ____Fragment Directions.action____(recipe)
//            findNavController().navigate(directions)
        }
        viewBinding.videosRecyclerView.adapter = videoAdapter

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        setupViewModelObservations()
    }

    private fun initUi() {
        setUpButtonClickListeners()
    }

    private fun setUpButtonClickListeners() {
        viewBinding.addFab.setOnClickListener {
            findNavController().navigate(R.id.action_videoGalleryFragment_to_videoCaptureFragment)
        }
    }

    private fun setupViewModelObservations() {
        viewModel.successMessage.observe(viewLifecycleOwner) {
            showSnackbar(viewBinding.root, it, true)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showSnackbar(viewBinding.root, it, false)
        }
        viewModel.isContentLoading.observe(viewLifecycleOwner) { isLoading ->
            viewBinding.shimmerLayout.shimmerFrameLayout.showShimmer(isLoading)

            if (isLoading) {
                disableUserInteraction()
            } else {
                reEnableUserInteraction()
            }
        }

        viewModel.videos.observe(viewLifecycleOwner) {
            it?.apply {
                videoAdapter?.dataList = it
                viewBinding.videosRecyclerView.smoothScrollToPosition(0)
            }
        }
    }
}