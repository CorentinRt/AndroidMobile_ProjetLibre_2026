package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp

@Composable
fun SeeMoodScreen(
    modifier: Modifier = Modifier
) {
    SeeMoodContent(
        modifier = modifier
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
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
    ){

    }
}