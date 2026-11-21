package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.uqac.cours.moodsic_projetlibre_2026.R
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerApi
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.NewShareChooseMusicViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@Composable
fun NewShareChooseMusicScreen(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null,
    onConfirmChooseMusicClicked: (DeezerTrack) -> Unit = {}
) {
    val chooseMusicScreenViewModel: NewShareChooseMusicViewModel = viewModel()

    NewShareChooseMusicContent(
        modifier = modifier,
        photoUri = photoUri,
        isLoading = chooseMusicScreenViewModel.isLoading,
        errorMessage = chooseMusicScreenViewModel.errorMessage,
        tracks = chooseMusicScreenViewModel.tracks,
        selectedTrack = chooseMusicScreenViewModel.selectedTrack,
        onReceiveUpdateSearchText = { newText ->
            chooseMusicScreenViewModel.defineSearchText(newText)
        },
        searchText = chooseMusicScreenViewModel.searchText,
        onConfirmChooseMusicClicked = {
            if (chooseMusicScreenViewModel.selectedTrack != null){
                onConfirmChooseMusicClicked(chooseMusicScreenViewModel.selectedTrack!!)
            }
        },
        onSelectTrack = {
            chooseMusicScreenViewModel.defineSelectedTrack(it)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NewShareChooseMusicScreenPreview() {
    ScaffoldApp() { innerPadding ->
        NewShareChooseMusicContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewShareChooseMusicContent(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null,
    isLoading: Boolean = false,
    errorMessage: Int? = null,
    tracks: List<DeezerTrack> = emptyList(),
    selectedTrack: DeezerTrack? = null,
    onReceiveUpdateSearchText: (String) -> Unit = {},
    searchText: String = "",
    onConfirmChooseMusicClicked: () -> Unit = {},
    onSelectTrack: (DeezerTrack) -> Unit = {}
){
    Box(
        modifier = modifier
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            ChooseMusicContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .weight(1.5f),
                isLoading = isLoading,
                errorMessage = errorMessage,
                tracks = tracks,
                selectedTrack = selectedTrack,
                onUpdateSearchText = onReceiveUpdateSearchText,
                searchText = searchText,
                onSelectTrack = {
                    onSelectTrack(it)
                }
            )

            PhotoTakenPrevisualisation(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(vertical = 8.dp)
                    .weight(1f),
                photoUri = photoUri
            )

            IconButton(
                onClick = {
                    onConfirmChooseMusicClicked()
                },
                modifier = Modifier.size(80.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Restart Photo"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseMusicContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    errorMessage: Int? = null,
    tracks: List<DeezerTrack> = emptyList(),
    selectedTrack: DeezerTrack? = null,
    onUpdateSearchText: (String) -> Unit = {},
    searchText: String = "",
    onSelectTrack: (DeezerTrack) -> Unit = {}
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                MaterialTheme.colorScheme.surfaceVariant
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline,
                RoundedCornerShape(12.dp)
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                value = searchText,
                onValueChange = onUpdateSearchText,
                label = {
                    Text(
                        text = stringResource(R.string.choose_music_titleorartist)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            errorMessage?.let {
                Text(
                    text = stringResource(it),
                    color = MaterialTheme.colorScheme.error
                )
            }

            selectedTrack?.let {
                Text(
                    text = stringResource(R.string.choose_music_selectionprefix) + " : " + it.title + "-" + it.artist.name,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            MusicPropositionsList(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                tracks = tracks,
                onSelectTrack = onSelectTrack,
                selectedTrack = selectedTrack
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MusicPropositionsList(
    modifier: Modifier = Modifier,
    tracks: List<DeezerTrack> = emptyList(),
    onSelectTrack: (DeezerTrack) -> Unit = {},
    selectedTrack: DeezerTrack? = null
){
    LazyColumn(
        modifier = modifier
    ) {

        items(
            items = tracks,
            key = { it.id }
        ) { track ->

            TrackItem(
                modifier = Modifier.fillMaxWidth(),
                track = track,
                onSelectTrack = onSelectTrack,
                selectedTrack = selectedTrack
            )

            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackItem(
    modifier: Modifier = Modifier,
    track: DeezerTrack? = null,
    onSelectTrack: (DeezerTrack) -> Unit = {},
    selectedTrack: DeezerTrack? = null
){
    if (track == null)
        return;

    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(track.title)
        },
        supportingContent = {
            Text(track.artist.name)
        },
        leadingContent = {
            AsyncImage(
                model = track.album.cover,
                contentDescription = "Album Cover",
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop
            )
        },
        trailingContent = {
            IconButton(
                onClick = {
                    onSelectTrack(track)
                }
            ) {
                Icon(
                    imageVector =
                        if (selectedTrack?.id == track.id)
                            Icons.Default.Done
                        else
                            Icons.Default.CheckCircle,
                    contentDescription = "Track Chose State"
                )
            }
        }
    )
}