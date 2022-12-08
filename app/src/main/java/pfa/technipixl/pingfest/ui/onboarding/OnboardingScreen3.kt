package pfa.technipixl.pingfest.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import pfa.technipixl.pingfest.model.MusicGenre

@Composable
fun OnboardingScreen3(modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.32f),
			contentAlignment = Alignment.Center
		) {
			val composition by rememberLottieComposition(
				spec = LottieCompositionSpec.Url("https://assets10.lottiefiles.com/packages/lf20_rsqhglyn.json")
			)
			LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, contentScale = ContentScale.FillWidth)
		}

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 15.dp)
				.padding(top = 36.dp),
			verticalArrangement = Arrangement.SpaceEvenly,
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			Text(
				text = "Configure tes préférences",
				style = MaterialTheme.typography.headlineMedium,
				minLines = 2, maxLines = 2,
				textAlign = TextAlign.Center,
				modifier = Modifier.padding(top = 15.dp, bottom = 3.dp)
			)

			PrefList()
			Spacer(modifier = Modifier.weight(1f))
			ElevatedButton(
				modifier = Modifier
					.fillMaxWidth(),
				onClick = {

				}
			) {
				Text("C'est partis !")
			}
		}
	}
}

@Composable
fun PrefList(
	modifier: Modifier = Modifier
) {
	val listItems = MusicGenre.values()

	LazyColumn(
		modifier = modifier
	) {
		items(4) { index ->
			PrefListCell(
				modifier = Modifier
					.padding(horizontal = 50.dp),
				genre = listItems[index]
			)
		}
	}
}

@Composable
fun PrefListCell(
	modifier: Modifier = Modifier,
	genre: MusicGenre
) {
	Row(
		modifier = modifier,
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(genre.name)
		Spacer(modifier = Modifier.weight(1f))
		Checkbox(checked = false, onCheckedChange = {})
	}
}

@Preview
@Composable
fun OnboardingScreen3Preview() {
	OnboardingScreen3()
}
