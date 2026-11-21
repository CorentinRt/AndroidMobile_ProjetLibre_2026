package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.deezer.DeezerTrack

@Composable
fun NewShareConfirmScreen(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null,
    selectedTrack: DeezerTrack? = null,
    onConfirmShare: () -> Unit
) {
    NewShareConfirmContent(
        modifier = modifier,
        photoUri = photoUri,
        selectedTrack = selectedTrack,
        onConfirmShare = {
            onConfirmShare()
        }
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
    onConfirmShare: () -> Unit = {}
){
    Column(
        modifier = modifier
    ){

    }
}