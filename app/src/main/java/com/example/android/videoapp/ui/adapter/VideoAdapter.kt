package com.example.android.videoapp.ui.adapter

import com.example.android.videoapp.BR
import com.example.android.videoapp.R
import com.example.android.videoapp.data.model.domain.Video
import com.example.android.videoapp.databinding.ItemVideoBinding
import com.example.android.videoapp.ui.adapter.base.BaseRecyclerViewAdapter

class VideoAdapter(onItemClickListener: OnItemClickListener<Video>) :
    BaseRecyclerViewAdapter<Video, ItemVideoBinding>(onItemClickListener) {

    override fun getItemLayoutId(): Int {
        return R.layout.item_video
    }

    override fun getViewBindingVariableId(): Int {
        return BR.video
    }

    override fun onViewHolderBinding(
        viewDataBinding: ItemVideoBinding,
        item: Video?,
        position: Int
    ) {
    }
}