package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp

@Composable
fun NewShareChooseMusicScreen(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null,
    onConfirmChooseMusicClicked: () -> Unit = {}
) {
    NewShareChooseMusicContent(
        modifier = modifier,
        photoUri = photoUri,
        onConfirmChooseMusicClicked = {
            onConfirmChooseMusicClicked()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NewShareChooseMusicScreenPreview() {
    ScaffoldApp() { innerPadding ->
        NewShareChooseMusicContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewShareChooseMusicContent(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null,
    onConfirmChooseMusicClicked: () -> Unit = {}
){
    Box(
        modifier = modifier
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            ChooseMusicContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .weight(1f)
            )

            PhotoTakenPrevisualisation(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .weight(1f),
                photoUri = photoUri
            )

            IconButton(
                onClick = {
                    onConfirmChooseMusicClicked()
                },
                modifier = Modifier.size(80.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Restart Photo"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseMusicContainer(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                MaterialTheme.colorScheme.surfaceVariant
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline,
                RoundedCornerShape(12.dp)
            )
    ){

    }
}