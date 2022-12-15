package pfa.technipixl.pingfest.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pfa.technipixl.pingfest.network.FirebaseAuthService

private const val FIRST_TIME_OPENING = "pfa.technipixl.pingfest.first_time_opening"
private const val SHARED_PREFS = "pfa.technipixl.pingfest.shared_preferences"

object AppUser {
	private val service = FirebaseAuthService()
	private var sharedPreferences: SharedPreferences? = null
	private var firstTimeOpening: Boolean = true

	private var _currentUser = mutableStateOf(Participator())
	val currentUser: State<Participator> get() = _currentUser
	var currentSettings = mutableStateOf(FiltersValues())

	fun isConnected() = !currentUser.value.idPeople.isNullOrEmpty()
	fun isFirstTimeOpening() = firstTimeOpening

	fun initUser(appContext: Context) {
		sharedPreferences = appContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
		sharedPreferences?.let { data ->
			firstTimeOpening = data.getBoolean(FIRST_TIME_OPENING, true)
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

	suspend fun connectUser(email: String, password: String) {
		service.connectUser(email, password) { result ->
			result.user?.let { user ->
				fetchUserDataFromId(user.uid)
			}
		}
	}

	suspend fun createNewUserFromCurrentSession(email: String, password: String) {
		service.createNewUser(email, password) { userID ->
			val newUser = currentUser.value.copy()
			newUser.idPeople = userID
			commitNewUserData(newUser)
		}
	}

	private fun commitNewUserData(newUser: Participator) {
		CoroutineScope(Dispatchers.IO).launch {
			service.putUserData(newUser) {
				_currentUser.value.idPeople = newUser.idPeople
			}
		}
	}

	private fun fetchUserDataFromId(id: String) {
		CoroutineScope(Dispatchers.IO).launch {
			service.getUserData(id) { userData ->
				_currentUser.value = userData
			}
		}
	}
}
