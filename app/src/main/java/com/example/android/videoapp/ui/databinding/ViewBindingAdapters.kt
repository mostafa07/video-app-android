package com.example.android.videoapp.ui.databinding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("shown")
fun showOrGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}