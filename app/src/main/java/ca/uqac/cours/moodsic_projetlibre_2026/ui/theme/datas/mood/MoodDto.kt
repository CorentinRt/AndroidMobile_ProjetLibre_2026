package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.datas.mood

import kotlinx.serialization.Serializable

@Serializable
data class MoodDto(
    val photo_url: String,
    val track_id: Long?,
    val track_title: String,
    val artist_name: String,
    val album_cover_url: String?,
    val preview_url: String?,
    val latitude: Double,
    val longitude: Double
)