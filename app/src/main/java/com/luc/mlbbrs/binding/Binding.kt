package com.luc.mlbbrs.binding

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luc.mlbbrs.core.BaseListAdapter


@BindingAdapter("app:color")
fun View.color(resId: Int) {
    setBackgroundColor(resId)
}


@BindingAdapter("app:adapter")
fun RecyclerView.adapter(resourceAdapter: BaseListAdapter) {
    layoutManager = GridLayoutManager(context, 3)
    adapter = resourceAdapter
}

@BindingAdapter("app:isBold")
fun setBold(view: TextView, isBold: Boolean) {
    if (isBold) {
        view.setTypeface(null, Typeface.BOLD)
    } else {
        view.setTypeface(null, Typeface.NORMAL)
    }
}