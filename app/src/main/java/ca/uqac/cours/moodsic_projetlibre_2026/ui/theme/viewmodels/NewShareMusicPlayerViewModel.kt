package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class NewShareMusicPlayerViewModel(context: Context): ViewModel() {

    val player = ExoPlayer.Builder(context).build().apply {
        repeatMode = Player.REPEAT_MODE_ONE
    }

    fun play() {
        player.play()
    }

    var isPlaying by mutableStateOf(false)
        private set

    private var currentTrackPreviewUrl: String? = null

    private val listener = object : Player.Listener {
        override fun onIsPlayingChanged(playing: Boolean) {
            isPlaying = playing
        }
    }

    init {
        player.addListener(listener)
    }

    fun pause() {
        player.pause()
    }

    fun loadTrack(trackPreviewUrl: String){
        if (trackPreviewUrl.isBlank())
            return

        if (currentTrackPreviewUrl == trackPreviewUrl)
            return

        currentTrackPreviewUrl = trackPreviewUrl

        player.setMediaItem(
            MediaItem.fromUri(trackPreviewUrl)
        )
        player.prepare()
    }

    override fun onCleared() {
        player.release()
        super.onCleared()
    }
}