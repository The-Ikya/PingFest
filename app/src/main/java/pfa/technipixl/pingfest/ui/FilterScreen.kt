package pfa.technipixl.pingfest.ui.theme


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ConnectionScreen(
    modifier: Modifier = Modifier,
){
    Column(modifier = Modifier) {
        Text(text = "Distance")
        Row(verticalAlignment = Alignment.CenterVertically) {
            var sliderPosition by remember { mutableStateOf(0f)}
            Text(text = "0km")
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it}
            , modifier = Modifier
                    .width(200.dp)
                    .height(50.dp))
            Text(text ="20km")
        }


    }

}

@Composable
fun EditNumberField(
    label: Int,
    value: String,
    onValueChange: (String) -> Unit
) {
    Spacer(modifier = Modifier.padding(20.dp))
    Text(text = "Nouvel utilisateur")
}

@Preview
@Composable
fun ConnectionScreenPreview(){
    ConnectionScreen()
}

