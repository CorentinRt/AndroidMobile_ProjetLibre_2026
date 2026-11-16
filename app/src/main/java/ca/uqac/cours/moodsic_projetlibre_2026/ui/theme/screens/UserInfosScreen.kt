package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp

@Composable
fun UserInfosScreen(
    modifier: Modifier = Modifier
){
    UserInfosContent(
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UserInfosScreenPreview(){
    ScaffoldApp(){
            innerPadding ->
        UserInfosContent(modifier = Modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun UserInfosContent(
    modifier: Modifier = Modifier
){

}