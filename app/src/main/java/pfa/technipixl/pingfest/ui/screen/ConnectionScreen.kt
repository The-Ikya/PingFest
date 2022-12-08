package pfa.technipixl.pingfest.ui.screen


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

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

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ContentView() {
    val pagerState = rememberPagerState()
    Column() {
        SegmentedText()
        Box(
            modifier = Modifier
                //.fillMaxSize()
                .background(Color.Yellow)
        ) {
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

@Composable
private fun ViewOne() {
    Column(
        modifier = Modifier
            //.fillMaxSize()
            .background(Color.Magenta),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Toto",
            fontWeight = FontWeight.Bold,
            color = Color.Cyan,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

    }
}

@Composable
private fun ViewTwo() {
    Column(
        modifier = Modifier
            //.fillMaxSize()
            .background(Color.Cyan),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tutu",
            fontWeight = FontWeight.Bold,
            color = Color.Magenta,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview
@Composable
public fun ConnectionScreenPreview() {
    ConnectionScreen()
}