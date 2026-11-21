package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp

@Composable
fun NewShareConfirmScreen(
    modifier: Modifier = Modifier,
    onConfirmShare: () -> Unit
) {
    NewShareConfirmContent(
        modifier = modifier,
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
    onConfirmShare: () -> Unit = {}
){
    Column(
        modifier = modifier
    ){

    }
}