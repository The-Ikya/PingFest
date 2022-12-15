package pfa.technipixl.pingfest.network

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FirebaseStorageService {
	private val storageRef = Firebase.storage.reference

	suspend fun getStoredImage(imgId: String, onSuccessHandler: (Uri) -> Unit) {
		storageRef.child(imgId).downloadUrl.addOnSuccessListener {
			onSuccessHandler(it)
		}
	}
}
