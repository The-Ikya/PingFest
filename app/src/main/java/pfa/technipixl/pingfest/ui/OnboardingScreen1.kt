package pfa.technipixl.pingfest.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun OnboardingScreen1(modifier: Modifier = Modifier) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.fillMaxHeight()
			.padding(horizontal = 10.dp),
		verticalArrangement = Arrangement.SpaceEvenly
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.3f),
			contentAlignment = Alignment.Center
		) {
			val composition by rememberLottieComposition(
				spec = LottieCompositionSpec.Url("https://assets6.lottiefiles.com/packages/lf20_6aYlBl.json")
			)
			LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
		}

		OnboardingDescriptionText(
			title = "Bienvenue dans PingFest !",
			description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."
		)

		OnboardingPageBar()
	}
}

@Composable
fun OnboardingDescriptionText(
	modifier: Modifier = Modifier,
	title: String,
	description: String
) {
	Column(
		modifier = modifier,
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		Text(
			text = title,
			style = MaterialTheme.typography.headlineMedium,
			minLines = 2, maxLines = 2,
			textAlign = TextAlign.Center,
		)
		Text(
			text = description,
			style = MaterialTheme.typography.bodyLarge,
			minLines = 6, maxLines = 6,
			textAlign = TextAlign.Center,
			modifier = Modifier.padding(top = 10.dp)
		)
	}
}

@Composable
fun OnboardingPageBar(
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		Box {
			repeat(3) {
				Indicators(size = 3, index = 0)
			}
		}
		Button(
			onClick = {

			}
		) {
			Text(text = "Skip")
		}
	}
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(12.dp),
		modifier = Modifier.align(Alignment.CenterStart)
	) {
		repeat(size) {
			Indicator(isSelected = it == index)
		}
	}
}

@Composable
fun Indicator(isSelected: Boolean) {
	val width = animateDpAsState(
		targetValue = if (isSelected) 25.dp else 10.dp,
		animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
	)

	Box(
		modifier = Modifier
			.height(10.dp)
			.width(width.value)
			.clip(CircleShape)
			.background(
				color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0XFFF8E2E7)
			)
	) {

	}
}

@Preview
@Composable
fun OnboardingScreen1Preview() {
	OnboardingScreen1()
}
