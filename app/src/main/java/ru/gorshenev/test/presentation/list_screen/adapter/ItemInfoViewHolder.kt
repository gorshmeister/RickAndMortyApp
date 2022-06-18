package ru.gorshenev.test.presentation.list_screen.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ru.gorshenev.test.R
import ru.gorshenev.test.presentation.list_screen.model.ItemInfo

class CharacterViewHolder(view: View) : BaseViewHolder(view) {

    private val imPhoto: ImageView = view.findViewById(R.id.imageView)

    private val tvName: TextView = view.findViewById(R.id.tvName)

    private val tvGender: TextView = view.findViewById(R.id.tvGenderValue)

    private val tvSpecies: TextView = view.findViewById(R.id.tvSpeciesValue)

    private val tvLocation: TextView = view.findViewById(R.id.tvLocationValue)

    override fun bind(item: ItemInfo) {
        (item as? ItemInfo.Character)?.let {
            Glide.with(itemView)
                .load(it.imageUrl)
                .into(imPhoto)
            tvName.text = it.name
            tvGender.text = it.gender
            tvSpecies.text = it.species
            tvLocation.text = it.location
        }
    }
}

class LocationViewHolder(view: View) : BaseViewHolder(view) {

    private val tvName: TextView = view.findViewById(R.id.tvName)

    private val tvType: TextView = view.findViewById(R.id.tvTypeValue)

    private val tvDimension: TextView = view.findViewById(R.id.tvDimensionValue)

    override fun bind(item: ItemInfo) {
        (item as? ItemInfo.Location)?.let {
            tvName.text = it.name
            tvType.text = it.type
            tvDimension.text = it.dimension
        }
    }
}

class EpisodeViewHolder(view: View) : BaseViewHolder(view) {

    private val tvName: TextView = view.findViewById(R.id.tvName)

    private val tvAirDate: TextView = view.findViewById(R.id.tvAirDateValue)

    private val tvSeason: TextView = view.findViewById(R.id.tvSeasonValue)

    private val tvEpisode: TextView = view.findViewById(R.id.tvEpisodeValue)

    override fun bind(item: ItemInfo) {
        (item as? ItemInfo.Episode)?.let {
            tvName.text = it.name
            tvAirDate.text = it.airDate
            tvSeason.text = it.season
            tvEpisode.text = it.episode
        }
    }
}