package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun TopBarApp(
    modifier: Modifier = Modifier,
    currentLocation: String = "home",
    onGoBackClicked: () -> Unit = {}
){
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(
            start = 10.dp,
            end = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            modifier = Modifier.weight(1f).padding(end = 36.dp),
            onClick = {
                onGoBackClicked()
            }
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Go Back Button"
            )
        }

        Icon(
            modifier = Modifier.weight(1f),
            imageVector = Icons.Default.Notifications,
            contentDescription = "Logo Application Moodsic"
        )

        Text(
            modifier = Modifier.weight(1f).padding(start = 24.dp),
            text = getTitlePageByDestination(
                destination = currentLocation
            ),
            textAlign = TextAlign.Center,
        )
    }
}

fun getTitlePageByDestination(destination: String): String
{
    /*
    when (destination){

    }
    */

    return ""
}
