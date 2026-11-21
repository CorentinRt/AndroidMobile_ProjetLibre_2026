package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.datastores.SettingsAppDataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(context: Context): ViewModel() {
    private val settingsDataStore = SettingsAppDataStore(
        context.applicationContext
    )

    val nightMode: StateFlow<Boolean> =
        settingsDataStore.nightMode.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )

    fun defineNightMode(nightMode: Boolean) {
        viewModelScope.launch {
            settingsDataStore.defineNightMode(nightMode)
        }
    }
}