package pfa.technipixl.pingfest.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun OnboardingScreen2(modifier: Modifier = Modifier) {
	Column(
		modifier = modifier,
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.3f),
			contentAlignment = Alignment.Center
		) {
			val composition by rememberLottieComposition(
				spec = LottieCompositionSpec.Url("https://assets5.lottiefiles.com/packages/lf20_6sxyjyjj.json")
			)
			LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
		}

		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(horizontal = 15.dp),
			verticalArrangement = Arrangement.SpaceEvenly
		) {
			OnboardingDescriptionText(
				title = "Des événements proches de toi",
				description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
			)
		}
	}
}

@Preview
@Composable
fun OnboardingScreen1Preview() {
	OnboardingScreen2()
}
