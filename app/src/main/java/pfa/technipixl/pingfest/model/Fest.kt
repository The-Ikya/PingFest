package pfa.technipixl.pingfest.model

import android.os.SystemClock.sleep
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pfa.technipixl.pingfest.network.FirebaseStorageService
import pfa.technipixl.pingfest.network.FirestoreService
import kotlin.reflect.KProperty

class Fest {
	private val service = FirestoreService()
	private val storage = FirebaseStorageService()

	private var _list = mutableStateListOf<FestResults>()
	val list get() = _list.toList()

	init {
		fetchFests()

		repeat(50) {
			CoroutineScope(Dispatchers.IO).launch {
				sleep((it*100).toLong())
				createFakeFest()
			}
		}
	}

	var tempI = 0
	private suspend fun createFakeFest() {
		val orga = mutableListOf(FestProvider.getRandomOrgaName())
		repeat((0..3).random()) {
			orga.add(FestProvider.getRandomOrgaName())
		}
		val newFest = FestResults(
			adress = FestProvider.getRandomAdress(),
			challenge = FestProvider.getRandomChallenge(),
			description = FestProvider.getRandomDescription(),
			name = FestProvider.getRandomName(),
			organisator = orga
		)
		storage.getStoredImage("FestImage/Fest${(0..3).random()}.jpg") {
			newFest.image = it.toString()
			createNewFest(newFest)
			tempI++
		}
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

object FestProvider {
	private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ' '

	private fun getRandomString(size: Int = 5) : String {
		var str = ""

		repeat (size) {
			str += charPool.random()
		}

		return str.trim()
	}

	fun getRandomAdress() = getRandomString(15)
	fun getRandomChallenge() = getRandomString(20)
	fun getRandomDescription() = getRandomString(50)
	fun getRandomName() = getRandomString((5..15).random())
	fun getRandomOrgaName() = getRandomName()
}
