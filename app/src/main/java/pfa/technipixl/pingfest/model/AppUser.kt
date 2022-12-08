package pfa.technipixl.pingfest.model

object AppUser {
	const val FIRST_TIME_OPENING = "pfa.technipixl.pingfest.firsttimeopening"
	const val SHARED_PREFS = "pfa.technipixl.pingfest.shared_preferences"

	var connectedUser: ParticipatorResult.Participator? = null

	fun isConnected() = connectedUser != null
}