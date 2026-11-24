package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    HomeScreenContent(
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    ScaffoldApp(){
            innerPadding ->
        HomeScreenContent(modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier
){
    Text("HomeScreen")
}