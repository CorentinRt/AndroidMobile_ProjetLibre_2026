package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.HomeScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.NewShareScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.SettingsScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.UserInfosScreen

@Preview(showBackground = true)
@Composable
fun MoodSicApp(
    modifier: Modifier = Modifier,
    startingPage: String = "home"
){
    val navController = rememberNavController()

    ScaffoldApp(
        modifier = modifier,
        onGoToHomeClicked = {
            navController.navigate("home")
        },
        onGoToNewShareClicked = {
            navController.navigate("new_share")
        },
        onGoToUserInfosClicked = {
            navController.navigate("user_infos")
        },
        onGoToSettingsClicked = {
            navController.navigate("settings")
        }
    ){
        innerPadding ->

        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startingPage
        ){
            composable("home"){
                HomeScreen()
            }

            composable("new_share"){
                NewShareScreen()
            }

            composable("user_infos"){
                UserInfosScreen()
            }

            composable("settings"){
                SettingsScreen()
            }
        }
    }
}