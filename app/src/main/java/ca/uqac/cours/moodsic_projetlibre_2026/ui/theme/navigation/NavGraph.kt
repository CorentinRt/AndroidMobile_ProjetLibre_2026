package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val homeRoute = "home"
    val newShareRoute = "new_share"
    val userInfosRoute = "user_infos"
    val settingsRoute = "settings"

    ScaffoldApp(
        modifier = modifier,
        currentDestination =
            if (currentRoute != null) currentRoute else homeRoute,
        onGoToHomeClicked = {
            navController.navigate(homeRoute)
        },
        onGoToNewShareClicked = {
            navController.navigate(newShareRoute)
        },
        onGoToUserInfosClicked = {
            navController.navigate(userInfosRoute)
        },
        onGoToSettingsClicked = {
            navController.navigate(settingsRoute)
        },
        onGoBackClicked = {
            navController.popBackStack()
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