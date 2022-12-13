package pfa.technipixl.pingfest.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pfa.technipixl.pingfest.model.ParticipatorResult

class FirebaseAuthService {
	private val authService = Firebase.auth
	private val storeService = Firebase.firestore

	fun connectUser(email: String, password: String, onSuccessHandler: (AuthResult) -> Unit) {
		authService.signInWithEmailAndPassword(email, password)
			.addOnSuccessListener { result ->
				onSuccessHandler(result)
			}
	}

	fun createNewUser(email: String, password: String, onSuccessHandler: (String) -> Unit) {
		authService.createUserWithEmailAndPassword(email, password)
			.addOnSuccessListener { result ->
				result.user?.let { user ->
					onSuccessHandler(user.uid)
				}
			}
	}

	fun getUserData(id: String, onSuccessHandler: (ParticipatorResult.Participator?) -> Unit) {
		storeService.collection("UserData")
			.whereEqualTo("idPeople", id)
			.get()
			.addOnSuccessListener { result ->
				var user: ParticipatorResult.Participator? = null
				for (document in result.documents) {
					user = document.toObject(ParticipatorResult.Participator::class.java)
				}
				onSuccessHandler(user)
			}
	}

	fun putUserData(user: ParticipatorResult.Participator, onSuccessHandler: (DocumentReference) -> Unit) {
		val userData = hashMapOf(
			"idPeople" to user.idPeople,
			"login" to user.login,
			"email" to user.email,
			"photo" to user.photo,
			"partyList" to user.partyList,
			"album" to user.album,
			"friendList" to user.friendList
		)

		storeService.collection("UserData")
			.add(userData)
			.addOnSuccessListener { result ->
				onSuccessHandler(result)
			}
	}
}