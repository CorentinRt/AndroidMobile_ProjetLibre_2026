package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.datas.mood.MoodLocation
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.repositories.MoodRepository
import kotlinx.coroutines.launch

class UploadMoodViewModel(
    context: Context
): ViewModel() {

    private val repository = MoodRepository(context)

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    var isUploaded = mutableStateOf(false)
        private set

    fun uploadMood(
        photoUri: Uri,
        track: DeezerTrack,
        location: MoodLocation
    ){
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            isUploaded.value = false

            try {
                repository.createMood(
                    photoUri = photoUri,
                    track = track,
                    location = location
                )
                isUploaded.value = true

            } catch (e: Exception) {
                errorMessage.value =
                    e.message ?: "Error : Failed when trying to upload mood to supabase"
            } finally {
                isLoading.value = false
            }
        }
    }
}