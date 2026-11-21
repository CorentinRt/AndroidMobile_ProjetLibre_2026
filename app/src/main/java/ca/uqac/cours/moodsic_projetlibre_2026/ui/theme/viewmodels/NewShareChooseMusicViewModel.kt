package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.uqac.cours.moodsic_projetlibre_2026.R
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerApi
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class NewShareChooseMusicViewModel: ViewModel() {

    var searchText by mutableStateOf("")
        private set

    var tracks by mutableStateOf<List<DeezerTrack>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<Int?>(null)
        private set

    var selectedTrack by mutableStateOf<DeezerTrack?>(null)
        private set

    fun defineSearchText(searchText: String) {
        this.searchText = searchText
        this.searchTextFlow.value = searchText
    }

    fun defineSelectedTrack(selectedTrack: DeezerTrack?) {
        this.selectedTrack = selectedTrack
    }

    private val searchTextFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            searchTextFlow
                .debounce(500)  // Wait for 500ms after last change of searchTextflow -> if not, too much call
                .collectLatest { query ->

                    if (query.isBlank()) {
                        tracks = emptyList()
                        errorMessage = null
                        isLoading = false
                        return@collectLatest
                    }

                    isLoading = true
                    errorMessage = null

                    try {
                        val response = DeezerApi.DeezerClient.api.searchTracks(query)
                        tracks = response.data

                    } catch (e: CancellationException) {
                        throw e

                    } catch (e: Exception) {
                        tracks = emptyList()
                        errorMessage = R.string.choose_music_cantgettitleerror

                    } finally {
                        isLoading = false
                    }
                }
        }
    }
}