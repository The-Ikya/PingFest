package pfa.technipixl.pingfest.model

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object AppUser {
	private const val FIRST_TIME_OPENING = "pfa.technipixl.pingfest.firsttimeopening"
	private const val SHARED_PREFS = "pfa.technipixl.pingfest.shared_preferences"

	private var sharedPreferences: SharedPreferences? = null
	private var firstTimeOpening: Boolean = true
	var connectedUser: ParticipatorResult.Participator? = null
		private set(value) { field = value }

	fun isConnected() = connectedUser != null
	fun isFirstTimeOpening() = firstTimeOpening

	fun initUser(appContext: Context) {
		sharedPreferences = appContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
		firstTimeOpening = sharedPreferences!!.getBoolean(FIRST_TIME_OPENING, true)
	}

	fun hasFinishedOnboarding() {
//		sharedPreferences?.let {
//			val editor = it.edit()
//			editor.putBoolean(FIRST_TIME_OPENING, false)
//			editor.apply()
//		}  // TODO à décommenter plus tard
		firstTimeOpening = false
	}
}