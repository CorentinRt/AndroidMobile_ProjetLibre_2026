package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.repositories

import android.content.Context
import android.net.Uri
import ca.uqac.cours.moodsic_projetlibre_2026.data.SupabaseClientProvider
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.datas.mood.MoodDto
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.datas.mood.MoodLocation
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID

class MoodRepository(
    private val context: Context
){
    suspend fun createMood(
        photoUri: Uri,
        track: DeezerTrack,
        location: MoodLocation
    ) = withContext(Dispatchers.IO) {
        val bytes = context.contentResolver
            .openInputStream(photoUri)
            ?.use {
                it.readBytes()
            } ?: throw Exception("Error : Photo Lecture Impossible !")

        val fileName = "${UUID.randomUUID()}.jpg"

        SupabaseClientProvider.client.storage
            .from("mood-photos")
            .upload(fileName, bytes)

        val mood = MoodDto(
            photo_url = fileName,
            track_id = track.id,
            track_title = track.title,
            artist_name = track.artist.name,
            album_cover_url = track.album.cover,
            preview_url = track.preview,
            latitude = location.latitude,
            longitude = location.longitude
        )

        SupabaseClientProvider.client
            .from("moods")
            .insert(mood)
    }

}