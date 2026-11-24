package ca.uqac.cours.moodsic_projetlibre_2026

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.Moodsic_ProjetLibre_2026Theme
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.navigation.MoodSicApp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()

        setContent {

            val context = LocalContext.current

            val settingsViewModel: SettingsViewModel = viewModel(
                factory = viewModelFactory {
                    initializer {
                        SettingsViewModel(
                            context = context
                        )
                    }
                }
            )

            val nightMode by settingsViewModel.nightMode.collectAsState()

            Moodsic_ProjetLibre_2026Theme(
                darkTheme = nightMode
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MoodSicApp(
                        modifier = Modifier.padding(top = innerPadding.calculateTopPadding()),
                        startingPage = "register"
                    )
                }
            }
        }
    }
    
    override fun onStart() {
        super.onStart()
        Log.d("MAIN ACTIVITY" , "OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MAIN ACTIVITY" , "OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MAIN ACTIVITY" , "OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MAIN ACTIVITY" , "OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MAIN ACTIVITY" , "OnDestroy")
    }
}
