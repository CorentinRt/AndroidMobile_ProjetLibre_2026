package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import androidx.compose.foundation.Image
import ca.uqac.cours.moodsic_projetlibre_2026.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.viewmodels.RegisterViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.platform.LocalContext

@Composable
fun RegisterScreen(registerViewModel : RegisterViewModel , onGoToHome: () -> Unit){
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize() , horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(R.drawable.placeholder),
            contentDescription = "Logo MoodSic",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(40.dp))


        Text("email")
        OutlinedTextField(
            value = registerViewModel.email,
            onValueChange = { registerViewModel.email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Password")
        OutlinedTextField(
            value = registerViewModel.mdp,
            onValueChange = { registerViewModel.mdp = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onGoToHome) { Text("Sign Up") }
            Button(onClick = onGoToHome) { Text("Sign In") }
        }
            Button(onClick = {
                registerViewModel.signInWithGoogle(context, onGoToHome);
            }
            ) { Text("Log With Google") }
    }
}