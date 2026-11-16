package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ca.uqac.cours.moodsic_projetlibre_2026.R
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.SettingsViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
){
    val context: Context = LocalContext.current

    val settingsViewModel: SettingsViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                SettingsViewModel(context)
            }
        }
    )

    val nightMode by settingsViewModel.nightMode.collectAsState()

    SettingsContent(
        modifier = modifier,
        nightMode = nightMode,
        onToggleNightMode = {
            settingsViewModel.defineNightMode(
                nightMode = it
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview(){
    ScaffoldApp(){
        innerPadding ->
        SettingsContent(
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    nightMode: Boolean = false,
    onToggleNightMode: (Boolean) -> Unit = {}
){

    Box(
        modifier = modifier
        .fillMaxHeight()
        .padding(top = 16.dp)
    ){
        ListSettings(
            onToggleNightMode = {
                onToggleNightMode(it)
            },
            nightMode = nightMode
        )
    }

}

@Preview(showBackground = true)
@Composable
fun ListSettings(
    modifier: Modifier = Modifier,
    nightMode: Boolean = false,
    onToggleNightMode: (Boolean) -> Unit = {}
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        SwitchSetting(
            name = stringResource(R.string.settings_nightmode),
            icon = Icons.Default.Phone,
            contentDescription = "Night Mode Icon Setting",
            checked = nightMode,
            onCheckedChange = {
                onToggleNightMode(it)
            }
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun SwitchSetting(
    modifier : Modifier = Modifier,
    name: String = "Placeholder",
    icon: ImageVector = Icons.Default.Home,
    contentDescription: String = "Icon Setting",
    checked : Boolean = true,
    onCheckedChange : (Boolean) -> Unit = {}
){
    Row(modifier = modifier.fillMaxWidth()
        .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription
        )

        Text(
            modifier = Modifier,
            text = name,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Left
        )

        Switch(
            checked = checked,
            onCheckedChange = {
                onCheckedChange(it)
            }
        )
    }
}