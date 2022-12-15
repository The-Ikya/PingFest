package pfa.technipixl.pingfest.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import pfa.technipixl.pingfest.NavBar
import pfa.technipixl.pingfest.model.FestResults
import pfa.technipixl.pingfest.viewmodels.EventsScreenViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun EventsScreen(
	navigator: DestinationsNavigator,
	viewModel: EventsScreenViewModel = viewModel()
) {
	Scaffold(
		topBar = { EventScreenTopBar() },
		bottomBar = { NavBar(navigator = navigator) },
		floatingActionButton = { EventsFAB(viewModel = viewModel) },
		floatingActionButtonPosition = FabPosition.End
	) {
		when (viewModel.viewMode.value) {
			EventsScreenViewModel.EventsViewMode.List ->
				EventScreenList(
					modifier = Modifier.padding(it),
					viewModel = viewModel
				)
			EventsScreenViewModel.EventsViewMode.Map ->
				EventScreenMap(
					modifier = Modifier.padding(it),
					viewModel = viewModel
				)
		}
	}
}

@Composable
@Preview
fun EventsFAB(
	modifier: Modifier = Modifier,
	viewModel: EventsScreenViewModel = viewModel()
) {
	FloatingActionButton(
		onClick = { Log.d("FABaction", "boutton fonctionnel") }
	) {
		Icon(
			imageVector = 	viewModel.getFABIcon(),
			contentDescription = "Passer en mode ${viewModel.viewMode.value.name}"
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun EventScreenTopBar(
	modifier: Modifier = Modifier
) {
	var textFieldState by remember { mutableStateOf("") }
	
	Row(
		modifier = modifier.padding(8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		OutlinedTextField(
			modifier = Modifier.weight(1f),
			placeholder = { Text("Rechercher") },
			value = textFieldState, 
			onValueChange = { textFieldState = it },
			leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = "recherche") }
		)
		Icon(
			modifier = Modifier.padding(start = 8.dp),
			imageVector = Icons.Filled.AccountCircle,
			contentDescription = null
		)
	}
}

@Composable
@Preview
fun EventScreenList(
	modifier: Modifier = Modifier,
	viewModel: EventsScreenViewModel = viewModel()
) {
	LazyColumn(
		modifier = modifier.padding(horizontal = 10.dp)
	) {
		items(viewModel.eventList) { event ->
			EventListCell(event = event)
		}
	}
}

@Composable
fun EventListCell(
	modifier: Modifier = Modifier,
	event: FestResults
) {
	Card(
		modifier = modifier
			.fillMaxWidth()
			.height(50.dp)
	) {
		Row {
			Column {
				Text(
					text = event.name,
					style = MaterialTheme.typography.titleMedium
				)
				Text(
					text = Date(event.dateDebut).toString(),
					style = MaterialTheme.typography.labelSmall
				)
				Spacer(modifier = Modifier.weight(1f))
				Text(
					text = event.description,
					style = MaterialTheme.typography.labelSmall
				)
			}
			Spacer(modifier = Modifier.weight(1f))
			Image(
				painter = rememberAsyncImagePainter(model = event.image),
				contentDescription = null,
				contentScale = ContentScale.Fit,
				modifier = Modifier
					.fillMaxHeight()
					.width(50.dp)
			)
		}
	}
}

@Composable
@Preview
fun EventScreenMap(
	modifier: Modifier = Modifier,
	viewModel: EventsScreenViewModel = viewModel()
) {
	val cameraPositionState = rememberCameraPositionState()

	GoogleMap(
		modifier = modifier
			.fillMaxSize(),
		cameraPositionState = cameraPositionState
	)
}

@Preview
@Composable
fun EventsScreenPreveiew() {

}
