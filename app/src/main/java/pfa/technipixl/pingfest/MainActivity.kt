package pfa.technipixl.pingfest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pfa.technipixl.pingfest.destinations.FilterScreenDestination
import pfa.technipixl.pingfest.destinations.GreetingDestination
import pfa.technipixl.pingfest.ui.theme.PingFestTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			PingFestTheme {
				// Navigation
				DestinationsNavHost(navGraph = NavGraphs.root)
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Destination(start = true)
@Composable
fun Greeting(navigator: DestinationsNavigator) {
	Scaffold(
		bottomBar = { NavBar(navigator) }
	) {
		Text(text = "Hello!",
		modifier = Modifier.padding(it))
	}
}

@Composable
fun NavBar(navigator: DestinationsNavigator) {
	NavigationBar {
		var selectedItem by remember { mutableStateOf(0) }
		NavigationBarItem(
			icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
			label = { Text("Home") },
			selected = selectedItem == 0,
			onClick = { selectedItem = 0
			navigator.navigate(GreetingDestination())}
		)
		NavigationBarItem(
			icon = { Icon(Icons.Filled.Stars, contentDescription = "Evènements") },
			label = { Text("Evènements") },
			selected = selectedItem == 1,
			onClick = { selectedItem = 1}
		)
		NavigationBarItem(
			icon = { Icon(Icons.Filled.Image, contentDescription = "Gallery") },
			label = { Text("Gallery") },
			selected = selectedItem == 2,
			onClick = { selectedItem = 2}
		)
		NavigationBarItem(
			icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
			label = { Text("Settings") },
			selected = selectedItem == 3,
			onClick = { selectedItem = 3
					  navigator.navigate(FilterScreenDestination())
					  },

		)
	}
}

@Composable
@Preview
fun TabBarPrev() {
	//NavBar()
}