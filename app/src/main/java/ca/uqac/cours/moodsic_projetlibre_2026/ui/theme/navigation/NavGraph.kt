package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.HomeScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.NewShareChooseMusicScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.NewShareConfirmScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.NewShareScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.SettingsScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.UserInfosScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.NewShareGlobalViewModel

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
    val newShareChooseMusicRoute = "new_share_choose_music"
    val newShareConfirmRoute = "new_share_confirm"
    val userInfosRoute = "user_infos"
    val settingsRoute = "settings"

    val newShareGlobalViewModel: NewShareGlobalViewModel = viewModel()

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
            composable(homeRoute){
                HomeScreen()
            }

            composable(newShareRoute){
                NewShareScreen(
                    onNewShareTakePhotoConfirmed = {
                        newShareGlobalViewModel.defineCurrentPhotoTakenUri(it)
                        navController.navigate(newShareChooseMusicRoute)
                    }
                )
            }

            composable(newShareChooseMusicRoute){
                NewShareChooseMusicScreen(
                    photoUri = newShareGlobalViewModel.currentPhotoTakenUri,
                    onConfirmChooseMusicClicked = {
                        newShareGlobalViewModel.defineCurrentSelectedTrack(it)
                        navController.navigate(newShareConfirmRoute)
                    }
                )
            }

            composable(newShareConfirmRoute){
                NewShareConfirmScreen(
                    photoUri = newShareGlobalViewModel.currentPhotoTakenUri,
                    selectedTrack = newShareGlobalViewModel.currentSelectedTrack,
                    onConfirmShare = {
                        navController.navigate(homeRoute){
                            popUpTo(newShareRoute){
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(userInfosRoute){
                UserInfosScreen()
            }

            composable(settingsRoute){
                SettingsScreen()
            }
        }
    }
}