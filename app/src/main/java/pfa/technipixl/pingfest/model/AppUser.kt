package pfa.technipixl.pingfest.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import pfa.technipixl.pingfest.network.FirebaseAuthService

object AppUser {
	private const val FIRST_TIME_OPENING = "pfa.technipixl.pingfest.firsttimeopening"
	private const val SHARED_PREFS = "pfa.technipixl.pingfest.shared_preferences"

	private val service = FirebaseAuthService()
	private var sharedPreferences: SharedPreferences? = null
	private var firstTimeOpening: Boolean = true

	private lateinit var _currentUser: MutableState<ParticipatorResult.Participator>
	val currentUser: State<ParticipatorResult.Participator> get() = _currentUser

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

	fun connect(email: String, password: String) {
		service.connectUser(email, password) { result ->
			result.user?.let { user ->
				fetchUserDataFromId(user.uid)
			}
		}
	}

	fun createNewUserFromCurrentSession(email: String, password: String) {

	}

	private fun fetchUserDataFromId(id: String) {
		service.getUserData(id) { userData ->
			userData?.let { _currentUser.value = it }
		}
	}
}