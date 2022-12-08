package pfa.technipixl.pingfest.ui.theme


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pfa.technipixl.pingfest.NavBar
import pfa.technipixl.pingfest.NavGraphs
import pfa.technipixl.pingfest.R

@Composable
fun FilterScreenContent(
    modifier: Modifier = Modifier
) {
    var isDialogShown by remember {
        mutableStateOf(false)
    }

    var sliderPosition by remember { mutableStateOf(0f) }
    val radioOptions = listOf("0 à 150", "150 à 300", "300 à 700", "Plus de 750")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    val context = LocalContext.current
    Column(modifier = Modifier) {

        if(isDialogShown){
            GenreDialog(onDismiss = { isDialogShown = false})
        }

        Image(painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = " Genre de musique ",
            modifier = Modifier.size(20.dp))
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
            onClick = { isDialogShown = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        )  {
           Row(horizontalArrangement = Arrangement.SpaceBetween,
               modifier = Modifier.fillMaxWidth()) {
               Text(

                   text = "Genre de musique",
                   color = Color.Black
               )
               Icon(
                   imageVector = Icons.Filled.ArrowRight,
                   contentDescription = " Genre de musique ",
                   tint = Color.Black

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
               Icon(
                   imageVector = Icons.Filled.ArrowRight,
                   contentDescription = " Genre de musique ",
                   tint = Color.Black
               )
           }

        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FilterScreen(navigator: DestinationsNavigator, modifier: Modifier = Modifier){
    Scaffold(
        bottomBar = {
            NavBar(navigator)
        }
    ) {
        FilterScreenContent(modifier = modifier.padding(it))
    }
}


@Composable
fun GenreDialog(onDismiss: () -> Unit){
    var rockBox: Boolean by remember {
        mutableStateOf(true)
    }
            Dialog(
                content = {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Rock",
                                color = Color.Black
                            )
                            Checkbox(checked = rockBox, onCheckedChange = {
                                rockBox = it
                            })
                        }
                    }
                }, onDismissRequest = { onDismiss() },
                properties = DialogProperties(
                    dismissOnClickOutside = true,
                    dismissOnBackPress = true
                )
            )
}

@Preview
@Composable
fun ConnectionScreenPreview() {
    //FilterScreen()
    GenreDialog({})
}

