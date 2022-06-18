package ru.gorshenev.test.presentation.list_screen.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.gorshenev.test.presentation.list_screen.model.ItemInfo

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: ItemInfo)
}