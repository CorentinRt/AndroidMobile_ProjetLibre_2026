package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
){

    SettingsContent(
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview(){
    ScaffoldApp(){
        innerPadding ->
        SettingsContent(modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsContent(
    modifier: Modifier = Modifier
){

}