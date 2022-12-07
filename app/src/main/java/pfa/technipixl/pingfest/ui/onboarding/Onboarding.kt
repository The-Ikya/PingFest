package pfa.technipixl.pingfest.ui.onboarding

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import pfa.technipixl.pingfest.ui.OnboardingScreen1

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingNavigation(
	modifier: Modifier = Modifier
) {
	val pagerState = rememberPagerState()

	Column(
		modifier = modifier
			.fillMaxSize(),
	) {
		OnboardingPageBar(
			page = pagerState.currentPage
		)
		HorizontalPager(count = 3, state = pagerState) { page ->
			val innerModifier = Modifier
				//.padding(top = 50.dp)
				.fillMaxWidth()
				.fillMaxHeight(0.9f)

			when(page) {
				0 -> OnboardingScreen1(
					modifier = innerModifier
				)
				1 -> OnboardingScreen2(
					modifier = innerModifier
				)
				else -> OnboardingScreen1()
			}
		}
	}
}

@Composable
fun OnboardingPageBar(
	modifier: Modifier = Modifier,
	page: Int
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 25.dp, vertical = 10.dp),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		Box {
			repeat(3) {
				Indicators(size = 3, index = page)
			}
		}
		TextButton(
			onClick = {
				// TODO
			}
		) {
			Text(text = "Skip")
		}
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
			modifier = Modifier.padding(vertical = 15.dp)
		)
		Text(
			text = description,
			style = MaterialTheme.typography.bodyLarge,
			minLines = 6, maxLines = 6,
			textAlign = TextAlign.Center
		)
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
fun OnboardingNavigationPreview() {
	OnboardingNavigation()
}
