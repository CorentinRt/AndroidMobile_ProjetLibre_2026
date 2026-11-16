package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ScaffoldApp(
    modifier: Modifier = Modifier,
    onGoToHomeClicked: () -> Unit = {},
    onGoToNewShareClicked: () -> Unit = {},
    onGoToUserInfosClicked: () -> Unit = {},
    onGoToSettingsClicked: () -> Unit = {},
    onGoBackClicked: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {}
){
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBarApp(
                onGoBackClicked = {
                    onGoBackClicked()
                }
            )
        },
        bottomBar = {
            BottomBarApp(
                onGoToHomeClicked = {
                    onGoToHomeClicked()
                },
                onGoToNewShareClicked = {
                    onGoToNewShareClicked()
                },
                onGoToUserInfosClicked = {
                    onGoToUserInfosClicked()
                },
                onGoToSettingsClicked = {
                    onGoToSettingsClicked()
                }
            )
        }
    ){
        innerPadding ->
        content(innerPadding)
    }
}