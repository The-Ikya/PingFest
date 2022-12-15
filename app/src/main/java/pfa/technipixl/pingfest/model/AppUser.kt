package pfa.technipixl.pingfest.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.SystemClock.sleep
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pfa.technipixl.pingfest.network.FirebaseAuthService

object AppUser {
	private const val FIRST_TIME_OPENING = "pfa.technipixl.pingfest.first_time_opening"
	private const val SHARED_PREFS = "pfa.technipixl.pingfest.shared_preferences"

	private val service = FirebaseAuthService()
	private var sharedPreferences: SharedPreferences? = null
	private var firstTimeOpening: Boolean = true

	private var _currentUser = mutableStateOf(ParticipatorResult.Participator())
	val currentUser: State<ParticipatorResult.Participator> get() = _currentUser
	var currentSettings = mutableStateOf(FiltersValues())

	fun isConnected() = !currentUser.value.idPeople.isNullOrEmpty()
	fun isFirstTimeOpening() = firstTimeOpening

	fun initUser(appContext: Context) {
		sharedPreferences = appContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
		sharedPreferences?.let { data ->
			firstTimeOpening = data.getBoolean(FIRST_TIME_OPENING, true)
		}

		repeat(50) {
			CoroutineScope(Dispatchers.IO).launch {
				sleep((it*200).toLong())
				createFakeUser()
			}
		}
	}

	var tempI = 0
	suspend fun createFakeUser() {
		val newUser = ParticipatorResult.Participator(
			email = UserProvider.getRandomEmail(),
			login = UserProvider.getRandomLogin()
		)
		service.createNewUser(newUser.email!!, UserProvider.getRandomPassword()) { id ->
			newUser.idPeople = id
			CoroutineScope(Dispatchers.IO).launch {
				service.putUserData(newUser) {
					Log.d("TEMP_I", tempI.toString())
					tempI++
				}
			}
		}
	}

	fun hasFinishedOnboarding() {
//		sharedPreferences?.let {
//			val editor = it.edit()
//			editor.putBoolean(FIRST_TIME_OPENING, false)
//			editor.apply()
//		}  // TODO à décommenter plus tard
		firstTimeOpening = false
	}

	fun connectUser(email: String, password: String) {
		CoroutineScope(Dispatchers.IO).launch {
			service.connectUser(email, password) { result ->
				result.user?.let { user ->
					fetchUserDataFromId(user.uid)
				}
			}
		}
	}

	fun createNewUserFromCurrentSession(email: String, password: String) {
		CoroutineScope(Dispatchers.IO).launch {
			service.createNewUser(email, password) { userID ->
				val newUser = currentUser.value.copy()
				newUser.idPeople = userID
				commitNewUserData(newUser)
			}
		}
	}

	private fun commitNewUserData(newUser: ParticipatorResult.Participator) {
		CoroutineScope(Dispatchers.IO).launch {
			service.putUserData(currentUser.value) {
				_currentUser.value.idPeople = newUser.idPeople
			}
		}
	}

	private fun fetchUserDataFromId(id: String) {
		CoroutineScope(Dispatchers.IO).launch {
			service.getUserData(id) { userData ->
				userData?.let { _currentUser.value = it }
			}
		}
	}
}

object UserProvider {
	private val charPool: List<Char> = ('a'..'z') + ('A'..'Z')

	private fun getRandomString(size: Int = 5) : String {
		var str = ""

		repeat (size) {
			str += charPool.random()
		}

		return str
	}

	fun getRandomEmail() = "${getRandomString()}@${getRandomString()}.com"
	fun getRandomLogin() = getRandomString(7)
	fun getRandomPassword() = getRandomString(10)
}