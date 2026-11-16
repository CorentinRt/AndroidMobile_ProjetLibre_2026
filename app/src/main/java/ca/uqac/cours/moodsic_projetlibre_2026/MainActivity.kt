package ca.uqac.cours.moodsic_projetlibre_2026

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.Moodsic_ProjetLibre_2026Theme
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.navigation.MoodSicApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Moodsic_ProjetLibre_2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    MoodSicApp(
                        modifier = Modifier.padding(innerPadding),
                        startingPage = "home"
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
