package ru.gorshenev.test.presentation.list_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.gorshenev.test.R
import ru.gorshenev.test.presentation.list_screen.model.ItemInfo

class ItemInfoAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var items: List<ItemInfo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val viewHolder = when (viewType) {
            ViewType.CHARACTER.value -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
                CharacterViewHolder(view)
            }
            ViewType.LOCATION.value -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
                LocationViewHolder(view)
            }
            ViewType.EPISODE.value -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
                EpisodeViewHolder(view)
            }
            else -> throw Exception("Unknown viewType $viewType")
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ItemInfo.Character -> ViewType.CHARACTER.value
            is ItemInfo.Episode -> ViewType.EPISODE.value
            is ItemInfo.Location -> ViewType.LOCATION.value
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<ItemInfo>) {
        items = newItems
        notifyDataSetChanged()
    }

    private enum class ViewType(val value: Int) {
        CHARACTER(0),
        LOCATION(1),
        EPISODE(2)
    }

}

