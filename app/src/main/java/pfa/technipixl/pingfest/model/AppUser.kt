package pfa.technipixl.pingfest.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
		service.connectUser(email, password) { result ->
			result.user?.let { user ->
				fetchUserDataFromId(user.uid)
			}
		}
	}

	fun createNewUserFromCurrentSession(email: String, password: String) {
		service.createNewUser(email, password) { userID ->
			_currentUser.value.idPeople = userID
			service.putUserData(currentUser.value)
		}
	}

	private fun fetchUserDataFromId(id: String) {
		service.getUserData(id) { userData ->
			userData?.let { _currentUser.value = it }
		}
	}
}