package pfa.technipixl.pingfest.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pfa.technipixl.pingfest.model.FestResults
import pfa.technipixl.pingfest.network.FirestoreService

class EventsScreenViewModel : ViewModel() {

	private val service = FirestoreService()

	enum class EventsViewMode {
		Map,
		List
	}

	var viewMode = mutableStateOf(EventsViewMode.List)

	private var _eventList: List<FestResults> = listOf()
	val eventList get() = _eventList

	init {
		fetchEvents()
	}

	fun getFABIcon() = when(viewMode.value) {
		EventsViewMode.List -> Icons.Filled.Map
		EventsViewMode.Map -> Icons.Filled.List
	}

	fun fetchEvents() {
		viewModelScope.launch {
			service.getFestsData {
				_eventList = it
			}
		}
	}
}