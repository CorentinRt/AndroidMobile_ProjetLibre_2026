package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun TopBarApp(
    modifier: Modifier = Modifier,
    currentLocation: String = "home"
){
    Text(
        text = "PLACEHOLDER TOP BAR"
    )
}
