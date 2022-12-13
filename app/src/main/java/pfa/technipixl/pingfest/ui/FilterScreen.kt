package pfa.technipixl.pingfest.ui.theme

import android.graphics.Paint.Align
import android.icu.text.CaseMap
import android.icu.text.Transliterator.Position
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pfa.technipixl.pingfest.NavBar
import pfa.technipixl.pingfest.NavGraphs
import pfa.technipixl.pingfest.R
import pfa.technipixl.pingfest.model.MusicGenre

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

        if (isDialogShown) {
            GenreDialog(onDismiss = { isDialogShown = false })
        }

        Image(
            painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = " Genre de musique ",
            modifier = Modifier.size(20.dp)
        )
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
                    .height(50.dp)
            )
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
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
        Button(
            modifier = Modifier.align(Alignment.Start),
            onClick = { isDialogShown = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
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
            modifier = Modifier.align(Alignment.Start),
            onClick = {
                Toast.makeText(context, "This is a Toast. Yay!", Toast.LENGTH_LONG).show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
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
fun FilterScreen(navigator: DestinationsNavigator, modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = {
            NavBar(navigator)
        }
    ) {
        FilterScreenContent(modifier = modifier.padding(it))
    }
}


@Composable
fun GenreDialog(onDismiss: () -> Unit) {
    val context = LocalContext.current
    var checkedBox by remember {
        mutableStateOf(MusicGenre.values())
    }
    AlertDialog(
        onDismissRequest ={ onDismiss()},
        confirmButton = { TextButton(onClick = { onDismiss() }) {//changer le onDismiss par l'enregistrement
            Text(text = "Ok")
        }},
        modifier = Modifier.fillMaxHeight(fraction = 0.70f),
        title = {
            Text(
                text = "Genres préférés",
                Modifier.padding(15.dp),
                style = MaterialTheme.typography.headlineLarge
            )
        },
        text = {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            ) {
                items(items = MusicGenre.values(), itemContent = { item ->
                    var checkBox: Boolean by remember {
                        mutableStateOf(true)
                    }
                    Divider(
                        Modifier
                            .padding(
                                top = 10.dp, bottom = 10.dp,
                                start = 30.dp, end = 30.dp
                            )
                            .alpha(0.8f)
                    )
                    Row {
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = item.name,
                            Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        )
                        Checkbox(
                            checked = checkBox, onCheckedChange = {
                                checkBox = it
                            },
                        )
                    }
                })
            }
        },
    )
    /*Dialog(
        content = {
            Surface(shape = RoundedCornerShape(30.dp)) {
                Text(
                    text = "Genres préférés",
                    Modifier.padding(15.dp),
                    style = MaterialTheme.typography.headlineLarge
                )
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.70f)
                        .padding(top = 50.dp, bottom = 50.dp)
                ) {
                    items(items = MusicGenre.values(), itemContent = { item ->
                        var checkBox: Boolean by remember {
                            mutableStateOf(true)
                        }
                        Divider(
                            Modifier
                                .padding(
                                    top = 10.dp, bottom = 10.dp,
                                    start = 30.dp, end = 30.dp
                                )
                                .alpha(0.8f)
                        )
                        Row {
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = item.name,
                                Modifier
                                    .weight(8f)
                                    .align(Alignment.CenterVertically)
                            )
                            Checkbox(
                                checked = checkBox, onCheckedChange = {
                                    checkBox = it
                                },
                                Modifier.weight(2f)
                            )
                        }
                    })
                }
                Row(horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()){
                    TextButton(onClick = {Toast.makeText(context, "This is a Toast. Yay!", Toast.LENGTH_LONG).show()}) {
                        Text("Ok")
                    }
                }
            }
        }, onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            dismissOnBackPress = true
        )
    )*/
}

@Preview
@Composable
fun ConnectionScreenPreview() {
    //FilterScreen()
    GenreDialog({})
}

