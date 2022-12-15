package pfa.technipixl.pingfest.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pfa.technipixl.pingfest.model.Participator

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

	suspend fun getUserData(id: String, onSuccessHandler: (Participator) -> Unit) {
		storeService.collection("UserData")
			.document(id)
			.get()
			.addOnSuccessListener { result ->
				val user = result.toObject(Participator::class.java)
				user?.let { onSuccessHandler(it) }
			}
	}

	suspend fun putUserData(user: Participator, onSuccessHandler: () -> Unit) {
		storeService.collection("UserData")
			.document(user.idPeople ?: "UNKNOWN")
			.set(user)
			.addOnSuccessListener {
				onSuccessHandler()
			}
	}
}