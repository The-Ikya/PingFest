package pfa.technipixl.pingfest.model

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pfa.technipixl.pingfest.network.FirestoreService
import kotlin.reflect.KProperty

class Fest {
	private val service = FirestoreService()

	private var _list = mutableStateListOf<FestResults>()
	val list get() = _list.toList()

	init {
		fetchFests()
	}

	fun fetchFests() {
		CoroutineScope(Dispatchers.IO).launch {
			service.getFestsData { festList ->
				_list.addAll(festList)
			}
		}
	}

	fun createNewFest(fest: FestResults) {
		CoroutineScope(Dispatchers.IO).launch {
			service.putFestData(fest) {
				_list.add(fest)
			}
		}
	}

	operator fun getValue(thisRef: Any?, property: KProperty<*>) = list
	operator fun setValue(thisRef: Any?, property: KProperty<*>, list: List<FestResults>) {
		_list.clear()
		_list.addAll(list)
	}
}

