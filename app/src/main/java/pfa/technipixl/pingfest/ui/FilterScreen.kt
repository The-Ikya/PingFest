package pfa.technipixl.pingfest.ui.theme


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pfa.technipixl.pingfest.R


@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
) {
    var sliderPosition by remember { mutableStateOf(0f) }
    val radioOptions = listOf("0 à 150", "150 à 300", "300 à 700", "Plus de 750")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    val context = LocalContext.current
    Column(modifier = Modifier) {
        Text(
            text = "Distance",
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(text = "0km")

            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it }, modifier = Modifier
                    .width(320.dp)
                    .height(50.dp))
            Text(text = "20km")
        }
        Text(text = "Taille de l'événement")
        Spacer(modifier = Modifier.padding(10.dp))
        radioOptions.forEach { text ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                    }
                )
                .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        Button(
            modifier =  Modifier.align(Alignment.Start),
            onClick = { Toast.makeText(context, "This is a Toast. Yay!", Toast.LENGTH_LONG).show() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        )  {
           Row(horizontalArrangement = Arrangement.SpaceBetween,
               modifier = Modifier.fillMaxWidth()) {
               Text(

                   text = "Genre de musique",
                   color = Color.Black
               )
               Image(
                   painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                   contentDescription = " Genre de musique ",
                   modifier = Modifier.size(20.dp)

               )
           }
        }
        Button(
            modifier =  Modifier.align(Alignment.Start),
            onClick = { Toast.makeText(context, "This is a Toast. Yay!", Toast.LENGTH_LONG).show() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        )  {
           Row(horizontalArrangement = Arrangement.SpaceBetween,
               modifier = Modifier.fillMaxWidth()) {
               Text(
                   text = "Trier les événements",
                   color = Color.Black
               )
               Image(
                   painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                   contentDescription = " Genre de musique ",
                   modifier = Modifier.size(20.dp)
               )
           }

        }


    }

}


@Preview
@Composable
fun ConnectionScreenPreview() {
    FilterScreen()
}

