package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.datastores

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.settingsAppDataStore by preferencesDataStore(
    name = "settings"
)

class SettingsAppDataStore(private val context: Context) {
    private val NIGHT_MODE_KEY = booleanPreferencesKey("night_mode")

    val nightMode: Flow<Boolean> =
        context.settingsAppDataStore.data.map { preferences ->
            preferences[NIGHT_MODE_KEY] ?: false
        }

    suspend fun defineNightMode(night: Boolean) {
        context.settingsAppDataStore.edit { preferences ->
            preferences[NIGHT_MODE_KEY] = night
        }
    }
}