package pfa.technipixl.pingfest.ui.screen


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.*


import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import pfa.technipixl.pingfest.R

@Composable
fun ConnectionScreen() {
    ContentView()
}

@Composable
private fun SegmentedText() {

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.10f)
            .padding(top = 40.dp, bottom = 5.dp)
            .padding(horizontal = 25.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            TabBarItem(title = "Se connecter", modifier = Modifier.weight(1f), isSelected = true)


            TabBarItem(title = "Nouvel utilisateur",modifier = Modifier.weight(1f))

        }



}
@Composable
fun TabBarItem(title:String, modifier: Modifier=Modifier, isSelected :Boolean = false){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(text = title,
        style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier
            .height(if (isSelected) 3.dp else 1.dp)
            .fillMaxWidth()
            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray)) {

        }
    }
}

@Composable 
private fun ButtonConnection(){
    Row(modifier = Modifier
        .fillMaxWidth(),
    horizontalArrangement = Arrangement.Center) {
        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(Color.White), border = BorderStroke(1.dp, Color.Blue)) {
            Text(text = "Annuler",color = Color.DarkGray)
        }
        Spacer(modifier = Modifier.padding(30.dp))
        Button(onClick = { /*TODO*/ }) {
            Text("Connexion")
        }
    }
}



@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ContentView() {
    val pagerState = rememberPagerState()
    Column() {
        SegmentedText()
        Box() {
            PagerContent(pagerState = pagerState)
        }
    }

}


@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
private fun PagerContent(pagerState: PagerState) {
    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            count = 2,
            state = pagerState
        ) { pager ->
            when (pager) {
                0 -> {
                    ViewOne()
                }
                1 -> {
                    ViewTwo()
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ViewOne() {
    Column(modifier = Modifier
        .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        var text by remember { mutableStateOf("")}
        var password by rememberSaveable { mutableStateOf("")}
        val composition by rememberLottieComposition(
            spec =  LottieCompositionSpec.Url("https://assets6.lottiefiles.com/packages/lf20_6aYlBl.json")
        )
       LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
       OutlinedTextField(
           value = text,
           onValueChange = {text = it},
           label = {Text("Nom utilisateur")}
       )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text("Mot de passe")},
            // ajouter l'icon
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ButtonConnection()
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(end = 35.dp),
            horizontalArrangement = Arrangement.End) {
            Text(text = "Mot de passe oublié?", color = Color.Blue)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ViewTwo() {
    Column(modifier = Modifier
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        var text by remember { mutableStateOf("")}
        var password by rememberSaveable { mutableStateOf("")}
        val composition by rememberLottieComposition(
            spec =  LottieCompositionSpec.Url("https://lottiefiles.com/5482-connect")
        )
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = {Text("Nom utilisateur")}
        )
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = {Text("Email")}
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text("Mot de passe")},
            // ajouter l'icon
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text("confirmer mot de passe")},
            // ajouter l'icon
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        ButtonConnection()

    }
}

@Preview
@Composable
public fun ConnectionScreenPreview() {
    ConnectionScreen()
}