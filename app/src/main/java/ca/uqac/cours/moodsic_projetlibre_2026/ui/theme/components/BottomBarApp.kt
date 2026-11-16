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
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun BottomBarApp(
    modifier: Modifier = Modifier,
    currentLocation: String = "home",
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
            selected = currentLocation == "home",
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
                Text(text = "Home")
            }
        )

        // New Share Item
        NavigationBarItem(
            selected = currentLocation == "new_share",
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
                Text(text = "Share")
            }
        )

        // User Infos Item
        NavigationBarItem(
            selected = currentLocation == "user_infos",
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
                Text(text = "User")
            }
        )

        // Settings Item
        NavigationBarItem(
            selected = currentLocation == "settings",
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
                Text(text = "Settings")
            }
        )
    }
}