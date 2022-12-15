package pfa.technipixl.pingfest.network

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class FirebaseStorageService {
	private val storageRef = Firebase.storage.reference

	suspend fun getStoredImage(path: String, onSuccessHandler: (Uri) -> Unit) {
		storageRef.child(path).downloadUrl.addOnSuccessListener {
			onSuccessHandler(it)
		}
	}

	suspend fun storeImage(path: String, imageFile: Uri) {
		storageRef.child(path).putFile(imageFile)

	}
}
