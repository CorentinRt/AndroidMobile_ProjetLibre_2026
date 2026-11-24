package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var email by mutableStateOf("")
    var mdp by mutableStateOf("")

}