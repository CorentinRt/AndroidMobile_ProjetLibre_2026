package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer

data class DeezerResponse(
    val data: List<DeezerTrack>
)

data class DeezerTrack(
    val id: Long,
    val title: String,
    val link: String,
    val preview: String,
    val artist: DeezerArtist,
    val album: DeezerAlbum
)

data class DeezerArtist(
    val name: String
)

data class DeezerAlbum(
    val cover: String
)