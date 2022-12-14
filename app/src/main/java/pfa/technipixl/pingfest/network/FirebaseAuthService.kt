package pfa.technipixl.pingfest.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pfa.technipixl.pingfest.model.ParticipatorResult

class FirebaseAuthService {
	private val authService = Firebase.auth
	private val storeService = Firebase.firestore

	suspend fun connectUser(email: String, password: String, onSuccessHandler: (AuthResult) -> Unit) {
		authService.signInWithEmailAndPassword(email, password)
			.addOnSuccessListener { result ->
				onSuccessHandler(result)
			}
	}

	suspend fun createNewUser(email: String, password: String, onSuccessHandler: (String) -> Unit) {
		authService.createUserWithEmailAndPassword(email, password)
			.addOnSuccessListener { result ->
				result.user?.let { user ->
					onSuccessHandler(user.uid)
				}
			}
	}

	suspend fun getUserData(id: String, onSuccessHandler: (ParticipatorResult.Participator?) -> Unit) {
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

	suspend fun putUserData(user: ParticipatorResult.Participator, onSuccessHandler: () -> Unit) {
		storeService.collection("UserData")
			.add(user)
			.addOnSuccessListener {
				onSuccessHandler()
			}
	}
}