package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ca.uqac.cours.moodsic_projetlibre_2026.R

@Preview(showBackground = true)
@Composable
fun BottomBarApp(
    modifier: Modifier = Modifier,
    currentDestination: String = "home",
    onGoToHomeClicked: () -> Unit = {},
    onGoToNewShareClicked: () -> Unit = {},
    onGoToUserInfosClicked: () -> Unit = {},
    onGoToSettingsClicked: () -> Unit = {}
){
    NavigationBar(
        modifier = modifier
    ){
        // Home Item
        NavigationBarItem(
            selected = currentDestination == "home",
            onClick = {
                onGoToHomeClicked()
            },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home Icon"
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.title_home),
                    fontSize = 14.sp
                )
            }
        )

        // New Share Item
        NavigationBarItem(
            selected = currentDestination.contains("new_share", true),
            onClick = {
                onGoToNewShareClicked()
            },
            icon = {
                Icon(
                    Icons.Default.AddCircle,
                    contentDescription = "New Share Icon"
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.title_new_share),
                    fontSize = 14.sp
                )
            }
        )

        // User Infos Item
        NavigationBarItem(
            selected = currentDestination == "user_infos",
            onClick = {
                onGoToUserInfosClicked()
            },
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "User Infos Icon"
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.title_user_infos),
                    fontSize = 14.sp
                )
            }
        )

        // Settings Item
        NavigationBarItem(
            selected = currentDestination == "settings",
            onClick = {
                onGoToSettingsClicked()
            },
            icon = {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings Icon"
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.title_settings),
                    fontSize = 14.sp
                )
            }
        )
    }
}