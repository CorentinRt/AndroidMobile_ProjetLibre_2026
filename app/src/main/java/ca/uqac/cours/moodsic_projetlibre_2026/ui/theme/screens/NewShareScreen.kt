package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import ca.uqac.cours.moodsic_projetlibre_2026.R
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components.ScaffoldApp
import coil.compose.AsyncImage
import java.io.File

@Composable
fun NewShareScreen(
    modifier: Modifier = Modifier,
    onNewShareTakePhotoConfirmed: (Uri) -> Unit = {}
) {
    NewShareContent(
        modifier = modifier,
        onNewShareTakePhotoConfirmed = {
            onNewShareTakePhotoConfirmed(it)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NewShareScreenPreview() {
    ScaffoldApp() { innerPadding ->
        NewShareContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewShareContent(
    modifier: Modifier = Modifier,
    onNewShareTakePhotoConfirmed: (Uri) -> Unit = {}
) {
    val context = LocalContext.current

    var photoUri by remember { mutableStateOf<Uri?>(null) }
    var cameraUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->

        if (success) {
            photoUri = cameraUri
        }
    }

    fun openCamera() {
        try {
            val file = File.createTempFile(
                "moodsic_photo_",
                ".jpg",
                context.cacheDir
            )

            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                file
            )

            cameraUri = uri
            cameraLauncher.launch(uri)

        } catch (e: Exception) {
            Log.d("NEW_SHARE_SCREEN", e.message, e);
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoTakenPrevisualisation(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            photoUri = photoUri
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (photoUri != null) {

            PhotoTakenActionsButtons(
                modifier = Modifier.fillMaxWidth(),
                onRestartPhotoClicked = {
                    photoUri?.let { uri ->
                        photoUri = null
                        openCamera()
                    }
                },
                onConfirmPhotoClicked = {
                    photoUri?.let { uri ->
                        onNewShareTakePhotoConfirmed(uri)
                    }
                }
            )

        } else {

            PhotoNotTakenYetActionsButtons(
                modifier = Modifier.fillMaxWidth(),
                onOpenCameraClicked = {
                    openCamera()
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoTakenPrevisualisation(
    modifier: Modifier = Modifier,
    photoUri: Uri? = null
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
                ),
            contentAlignment = Alignment.Center
        ) {
            if (photoUri != null) {
                AsyncImage(
                    model = photoUri,
                    contentDescription = "Recap Photo",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Text(
                    text = stringResource(R.string.new_share_screen_nopicturetook),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
}

@Preview(showBackground = true)
@Composable
fun PhotoTakenActionsButtons(
    modifier: Modifier = Modifier,
    onRestartPhotoClicked: () -> Unit = {},
    onConfirmPhotoClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        IconButton(
            onClick = {
                onRestartPhotoClicked()
            },
            modifier = Modifier.size(80.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Restart Photo"
            )
        }

        IconButton(
            onClick = {
                onConfirmPhotoClicked()
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
                contentDescription = "Confirm Photo"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoNotTakenYetActionsButtons(
    modifier: Modifier = Modifier,
    onOpenCameraClicked: () -> Unit = {}
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    onOpenCameraClicked()
                },
                modifier = Modifier.size(80.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Capture"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.new_share_screen_starttakingpicture),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}