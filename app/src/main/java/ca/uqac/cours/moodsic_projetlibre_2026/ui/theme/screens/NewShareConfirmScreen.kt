package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.utilities.CustomAppButton
import coil.compose.AsyncImage

@Composable
fun NewShareConfirmScreen(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null,
    selectedTrack: DeezerTrack? = null,
    onConfirmShare: (Uri, DeezerTrack) -> Unit = { _, _ -> }
) {
    NewShareConfirmContent(
        modifier = modifier,
        photoUri = photoUri,
        selectedTrack = selectedTrack,
        onConfirmShare = onConfirmShare
    )
}

@Preview(showBackground = true)
@Composable
fun NewShareConfirmScreenPreview() {
    ScaffoldApp() { innerPadding ->
        NewShareConfirmContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewShareConfirmContent(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null,
    selectedTrack: DeezerTrack? = null,
    onConfirmShare: (Uri, DeezerTrack) -> Unit = { _, _ -> }
){
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        PhotoTakenPrevisualisation(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .weight(1f),
            photoUri = photoUri
        )

        TrackPrevisualisation(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            track = selectedTrack
        )

        CustomAppButton(
            onClick = {
                if (photoUri != null && selectedTrack != null){
                    onConfirmShare(
                        photoUri,
                        selectedTrack
                    )
                }
            },
            modifier = Modifier
                .size(80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Confirm Share Button"
            )
        }

        Spacer(Modifier.height(8.dp))
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 100)
@Composable
fun TrackPrevisualisation(
    modifier: Modifier = Modifier,
    track: DeezerTrack? = null
){
    var title = "None Title"
    var artistName = "None Name"
    var albumCover = ""

    if (track != null){
        title = track.title
        artistName = track.artist.name
        albumCover = track.album.cover
    }
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ){

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            AsyncImage(
                model = albumCover,
                contentDescription = "Album Cover",
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2.5f)
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = artistName,
                    fontSize = 14.sp
                )
            }

            LinearProgressIndicator(
                modifier = Modifier.weight(1f)
            )

            Spacer(Modifier.weight(0.5f))
        }
    }
}