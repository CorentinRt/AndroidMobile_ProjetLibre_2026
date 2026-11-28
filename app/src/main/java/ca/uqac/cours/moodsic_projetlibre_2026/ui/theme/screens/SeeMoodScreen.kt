package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack
import coil.compose.AsyncImage

@Composable
fun SeeMoodScreen(
    modifier: Modifier = Modifier,
    track: DeezerTrack? = null,
    photoUri: Uri? = null,
    userName: String = "UserName"
) {
    SeeMoodContent(
        modifier = modifier,
        track = track,
        photoUri = photoUri,
        userName = userName
    )
}

@Preview(showBackground = true)
@Composable
fun SeeMoodScreenPreview() {
    ScaffoldApp() { innerPadding ->
        SeeMoodContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SeeMoodContent(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    onTogglePlayTrack: () -> Unit = {},
    track: DeezerTrack? = null,
    photoUri: Uri? = null,
    userName: String = "UserName"
){
    Column(
        modifier = modifier
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        ){
            UserBanner(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                userPhotoUri = null,
                userName = userName
            )

            PhotoTakenPrevisualisation(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                photoUri = photoUri
            )

            TrackPrevisualisation(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(top = 8.dp),
                track = track,
                isPlaying = isPlaying,
                onTogglePlayTrack = onTogglePlayTrack
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 60)
@Composable
fun UserBanner(
    modifier: Modifier = Modifier,
    userPhotoUri: Uri? = null,
    userName: String = "Name"
){
    Row(
        modifier = modifier.padding(all = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){
        AsyncImage(
            model = userPhotoUri,
            contentDescription = "User Photo",
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.width(10.dp))

        Text(
            text = userName,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

    }
}