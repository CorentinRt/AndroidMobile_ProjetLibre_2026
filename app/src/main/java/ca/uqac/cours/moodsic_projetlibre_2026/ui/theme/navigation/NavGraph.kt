package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.BottomBarApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.TopBarApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.HomeScreen

@Preview(showBackground = true)
@Composable
fun MoodSicApp(
    modifier: Modifier = Modifier,
    startingPage: String = "home"
){
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopBarApp()
        },
        bottomBar = {
            BottomBarApp(
                onGoToHomeClicked = {
                    navController.navigate("home")
                },
                onGoToSettingsClicked = {
                    navController.navigate("settings")
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = startingPage
        ){
            composable("home"){
                HomeScreen()
            }
        }
    }
}