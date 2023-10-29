package com.example.myapplication.ui.song

import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Song
import com.example.myapplication.data.remote.retrofit.model.NetworkResponse
import com.example.myapplication.data.repository.ITunesRepository
import com.example.myapplication.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SongViewModel : BaseViewModel(), KoinComponent {
    private val mITunesRepository by inject<ITunesRepository>()

    private val mSongsLiveData = MutableLiveData<List<Song>>()
    val songsLiveData: LiveData<List<Song>> = mSongsLiveData

    private val mErrorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = mErrorLiveData

    fun searchSong(term: String?) {
        viewModelScope.launch {
            val response = mITunesRepository.searchSong(term)
            if (response is NetworkResponse.Success) {
                val songsData = arrayListOf<Song>()
                songsData.addAll(response.data.listSong)
                mSongsLiveData.postValue(songsData)
            } else if (response is NetworkResponse.Error) {
                mErrorLiveData.postValue(response.error)
            }
        }
    }

    private var mMediaPlayer: MediaPlayer? = null

    private var mIsMediaPlayerPrepared = false

    private val mStatusPLay = MutableLiveData<Boolean>()
    val statusPLay: LiveData<Boolean> = mStatusPLay

    fun playSong(song: Song) {
        val player = mMediaPlayer ?: preparePlayer(song)

        if (player.isPlaying) {
            mStatusPLay.postValue(false)
            playOrPause(false)
        } else if (mIsMediaPlayerPrepared) {
            mStatusPLay.postValue(true)
            playOrPause(true)
        }
    }

    private fun playOrPause(play: Boolean) {
        if (play) {
            mMediaPlayer?.start()
        } else {
            mMediaPlayer?.pause()
        }
    }

    private fun preparePlayer(song: Song): MediaPlayer {
        if (mMediaPlayer != null) {
            releaseMediaPlayer()
        }

        mMediaPlayer = MediaPlayer()
        mMediaPlayer?.setOnPreparedListener {
            mIsMediaPlayerPrepared = true
            playOrPause(true)
        }

        mIsMediaPlayerPrepared = false
        val player = mMediaPlayer!!
        player.setDataSource(song.preview)
        player.prepareAsync()
        mMediaPlayer = player
        return player
    }

    fun releaseMediaPlayer() {
        mMediaPlayer?.stop()
        mMediaPlayer?.reset()
        mMediaPlayer?.release()
        mMediaPlayer = null
    }
}