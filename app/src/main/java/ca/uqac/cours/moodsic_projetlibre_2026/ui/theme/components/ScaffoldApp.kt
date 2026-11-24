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
    currentDestination: String = "home",
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
            if (currentDestination != "register"){
                TopBarApp(
                    currentDestination = currentDestination,
                    onGoBackClicked = {
                        onGoBackClicked()
                    }
                )
            }
        },
        bottomBar = {
            if (currentDestination != "register"){
                BottomBarApp(
                    currentDestination = currentDestination,
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
        }
    ){
        innerPadding ->
        content(innerPadding)
    }
}