package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.HomeScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.NewShareChooseMusicScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.NewShareConfirmScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.NewShareScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.RegisterScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.SeeMoodContent
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.SeeMoodScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.SettingsScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens.UserInfosScreen
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.HomeScreenViewModel
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.NewShareGlobalViewModel
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.NewShareMusicPlayerViewModel
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.RegisterViewModel

@Preview(showBackground = true)
@Composable
fun MoodSicApp(
    modifier: Modifier = Modifier,
    startingPage: String = "register"
){
    val context: Context = LocalContext.current

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val homeRoute = "home"
    val registerRoute = "register"
    val newShareRouteSubGraph = "new_share_sub_graph"
    val newShareRoute = "new_share"
    val newShareChooseMusicRoute = "new_share_choose_music"
    val newShareConfirmRoute = "new_share_confirm"
    val userInfosRoute = "user_infos"
    val settingsRoute = "settings"
    val seeMoodRoute = "seeMood"

    val newShareGlobalViewModel: NewShareGlobalViewModel = viewModel()

    ScaffoldApp(
        modifier = modifier,
        currentDestination =
            if (currentRoute != null) currentRoute else homeRoute,
        onGoToHomeClicked = {
            navController.navigate(homeRoute){
                popUpTo(newShareRouteSubGraph){
                    inclusive = true
                }
            }
        },
        onGoToNewShareClicked = {
            navController.navigate(newShareRouteSubGraph)
        },
        onGoToUserInfosClicked = {
            navController.navigate(userInfosRoute){
                popUpTo(newShareRouteSubGraph){
                    inclusive = true
                }
            }
        },
        onGoToSettingsClicked = {
            navController.navigate(settingsRoute){
                popUpTo(newShareRouteSubGraph){
                    inclusive = true
                }
            }
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
                val homeScreenViewModel: HomeScreenViewModel = viewModel()

                HomeScreen(modifier, homeScreenViewModel)
            }

            navigation(
                route = newShareRouteSubGraph,
                startDestination = newShareRoute
            ){
                composable(newShareRoute){ backStackEntry ->

                    val shareGraphEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(newShareRouteSubGraph)
                    }

                    val newShareMusicPlayerViewModel: NewShareMusicPlayerViewModel = viewModel(
                        viewModelStoreOwner = shareGraphEntry,
                        factory = viewModelFactory {
                            initializer {
                                NewShareMusicPlayerViewModel(
                                    context = context.applicationContext
                                )
                            }
                        }
                    )

                    newShareMusicPlayerViewModel.pause()

                    NewShareScreen(
                        onNewShareTakePhotoConfirmed = {
                            newShareGlobalViewModel.defineCurrentPhotoTakenUri(it)
                            navController.navigate(newShareChooseMusicRoute)
                        }
                    )
                }

                composable(newShareChooseMusicRoute){ backStackEntry ->

                    val shareGraphEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(newShareRouteSubGraph)
                    }

                    val newShareMusicPlayerViewModel: NewShareMusicPlayerViewModel = viewModel(
                        viewModelStoreOwner = shareGraphEntry,
                        factory = viewModelFactory {
                            initializer {
                                NewShareMusicPlayerViewModel(
                                    context = context.applicationContext
                                )
                            }
                        }
                    )

                    NewShareChooseMusicScreen(
                        photoUri = newShareGlobalViewModel.currentPhotoTakenUri,
                        onConfirmChooseMusicClicked = {
                            newShareGlobalViewModel.defineCurrentSelectedTrack(it)
                            navController.navigate(newShareConfirmRoute)
                        },
                        newShareMusicPlayerViewModel = newShareMusicPlayerViewModel
                    )
                }

                composable(newShareConfirmRoute){ backStackEntry ->

                    val shareGraphEntry = remember(backStackEntry) {
                        navController.getBackStackEntry(newShareRouteSubGraph)
                    }

                    val newShareMusicPlayerViewModel: NewShareMusicPlayerViewModel = viewModel(
                        viewModelStoreOwner = shareGraphEntry,
                        factory = viewModelFactory {
                            initializer {
                                NewShareMusicPlayerViewModel(
                                    context = context.applicationContext
                                )
                            }
                        }
                    )

                    NewShareConfirmScreen(
                        photoUri = newShareGlobalViewModel.currentPhotoTakenUri,
                        selectedTrack = newShareGlobalViewModel.currentSelectedTrack,
                        onConfirmShare = { photoUri, selectedTrack ->

                            navController.navigate(homeRoute){
                                popUpTo(newShareRouteSubGraph){
                                    inclusive = true
                                }
                            }
                        },
                        newShareMusicPlayerViewModel = newShareMusicPlayerViewModel
                    )
                }
            }

            composable(userInfosRoute){
                UserInfosScreen()
            }

            composable(settingsRoute){
                SettingsScreen()
            }

            composable(seeMoodRoute){
                SeeMoodScreen()
            }
            composable(registerRoute){
                SeeMoodScreen()
            }
            composable(registerRoute){
                val registerViewModel: RegisterViewModel = viewModel()

                RegisterScreen(registerViewModel = registerViewModel, onGoToHome = { navController.navigate(homeRoute) })
            }
        }
    }
}