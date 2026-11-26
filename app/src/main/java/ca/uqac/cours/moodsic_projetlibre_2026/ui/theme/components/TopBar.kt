package ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.uqac.cours.moodsic_projetlibre_2026.R
import ca.uqac.cours.moodsic_projetlibre_2026.ui.theme.utilities.CustomAppButton

@Preview(showBackground = true)
@Composable
fun TopBarApp(
    modifier: Modifier = Modifier,
    currentDestination: String = "user_infos",
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
        CustomAppButton(
            modifier = Modifier.weight(3f)
                .padding(end = 36.dp),
            onClick = {
                onGoBackClicked()
            },
            shape = RoundedCornerShape(4.dp)
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Go Back Button"
            )
        }

        Icon(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp),
            painter = painterResource(R.drawable.moodsic_logo),
            contentDescription = "Logo Application Moodsic"
        )

        Text(
            modifier = Modifier.weight(3f)
                .padding(start = 20.dp),
            text = stringResource(
                    id = getTitlePageIDByDestination(
                        destination = currentDestination
                    )
                ),
            textAlign = TextAlign.Right,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

fun getTitlePageIDByDestination(destination: String): Int
{
    when (destination){
        "home" -> return R.string.title_home
        "new_share" -> return R.string.title_new_share
        "user_infos" -> return R.string.title_user_infos
        "settings" -> return R.string.title_settings
    }

    return R.string.app_name
}
