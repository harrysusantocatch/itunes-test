package com.example.myapplication.ui.song

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.myapplication.R
import com.example.myapplication.data.model.Song

object SongBindingAdapter {

    @JvmStatic
    @BindingAdapter("srcCoverImage")
    fun bindSrcCoverImage(imageView: ImageView, url: String?) {
        imageView.load(url) {
            transformations(RoundedCornersTransformation(14f))
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["adapter"])
    fun bindAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
        recyclerView.run {
            this.setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("adapterSongItems")
    fun bindAdapterSongItems(view: RecyclerView, pokemonList: List<Song>?) {
        pokemonList?.let { itemList ->
            (view.adapter as? SongListAdapter)?.refresh(itemList)
        }
    }
}
