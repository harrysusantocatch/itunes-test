package com.example.myapplication.ui.song

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySongBinding
import com.example.myapplication.ui.base.BaseActivity

class SongActivity : BaseActivity<ActivitySongBinding, SongViewModel>() {
    private val mAdapter: SongListAdapter by lazy {
        SongListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewDataBinding.apply {
            viewModel = mViewModel
            adapter = mAdapter
        }

        initObserver()
        searchSong("BCA")
        initUI()
    }

    private fun initUI() {
        mAdapter.setItemClickListener { song ->
            mViewDataBinding.apply {
                clButton.visibility = View.VISIBLE
                btPlay.setOnClickListener {
                    mViewModel.playSong(song)
                }
            }
            mAdapter.notifyDataSetChanged()
        }
    }

    private fun searchSong(term: String) {
        mViewModel.searchSong(term)
    }

    private fun initObserver() {
        mViewModel.statusPLay.observe(
            this
        ) { status ->
            var statusText = "Play"
            if (status) {
                statusText = "Pause"
            }
            mViewDataBinding.btPlay.text = statusText
        }
        mViewModel.songsLiveData.observe(
            this
        ) { songs ->
            Toast.makeText(this, "Total Song: ${songs.size}", Toast.LENGTH_LONG).show()
        }

        // Observe Error
        mViewModel.errorLiveData.observe(
            this
        ) { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_song
    }

    override fun getViewModel(): SongViewModel {
        return ViewModelProvider(this)[SongViewModel::class.java]
    }

    override fun onDestroy() {
        mViewModel.releaseMediaPlayer()
        super.onDestroy()
    }
}