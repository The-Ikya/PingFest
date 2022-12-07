package pfa.technipixl.pingfest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.NavGraphSpec
import pfa.technipixl.pingfest.ui.theme.PingFestTheme
import pfa.technipixl.pingfest.viewmodels.DashboardViewModel

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

@Destination(start = true)
@Composable
fun Greeting(navigator: DestinationsNavigator) {
	Text(text = "Hello !")
}