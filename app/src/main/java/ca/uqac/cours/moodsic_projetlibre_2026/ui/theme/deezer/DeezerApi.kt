package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApi {

    @GET("search")
    suspend fun searchTracks(@Query("q") query: String): DeezerResponse

    object DeezerClient {

        val api: DeezerApi by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DeezerApi::class.java)
        }
    }
}