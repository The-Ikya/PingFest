package pfa.technipixl.pingfest.ui.theme


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
){
    var sliderPosition by remember { mutableStateOf(0f)}
    val radioOptions = listOf("0 à 150", "150 à 300", "300 à 700", "Plus de 750")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] )}
    Column(modifier = Modifier) {
        Text(
            text = "Distance",
            fontWeight = FontWeight.Bold)
        Row(modifier = Modifier
            .fillMaxWidth()) {

            Text(text = "0km",)

            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it}
            , modifier = Modifier
                    .width(320.dp)
                    .height(50.dp))
            Text(text ="20km")
        }
        Text(text = "Taille de l'événement")
        radioOptions.forEach { text ->
            Row (modifier = Modifier
                .fillMaxWidth()
                .selectable(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                    }
                )
                .padding(horizontal = 16.dp)
            ){
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text)}
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodySmall.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }


    }

}



@Preview
@Composable
fun ConnectionScreenPreview(){
    FilterScreen()
}

