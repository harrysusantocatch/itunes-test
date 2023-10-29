package com.example.myapplication.ui.song

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.model.Song
import com.example.myapplication.databinding.AdapterSongListBinding

class SongListAdapter : RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    private val itemList: MutableList<Song> = mutableListOf()
    private var mItemClickListener: ((Song) -> Unit)? = null
    companion object {
        private var lastPost: Int? = null
    }

    fun setItemClickListener(listener: ((Song) -> Unit)) {
        mItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterSongListBinding.inflate(
            inflater,
            parent,
            false
        )
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val songEntity = itemList[position]
        holder.bind(songEntity, position)
    }

    override fun getItemCount(): Int = itemList.size

    fun refresh(list: List<Song>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    inner class SongViewHolder(private val viewBinding: AdapterSongListBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(song: Song, position: Int) {
            viewBinding.root.tag = song
            viewBinding.entity = song
            viewBinding.executePendingBindings()
            viewBinding.root.setOnClickListener {
                lastPost = position
                mItemClickListener?.invoke(it.tag as Song)
            }
            Log.w("loxx", "$lastPost")
            if (lastPost == position) {
                viewBinding.cvSong.setBackgroundResource(R.color.grey)
            } else {
                viewBinding.cvSong.setBackgroundResource(R.color.white)
            }
        }
    }
}