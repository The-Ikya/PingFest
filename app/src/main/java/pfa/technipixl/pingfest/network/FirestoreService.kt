package pfa.technipixl.pingfest.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pfa.technipixl.pingfest.model.FestResults

class FirestoreService {
	private val storeService = Firebase.firestore

	suspend fun getFestsData(onSuccessHandler: (List<FestResults>) -> Unit) {
		storeService.collection("Fests")
			.get()
			.addOnSuccessListener { result ->
				val festList: MutableList<FestResults> = mutableListOf()
				result.documents.forEach { document ->
					document.toObject(FestResults::class.java)?.let { festList += it }
				}
				onSuccessHandler(festList)
			}
	}

	suspend fun putFestData(fest: FestResults, onSuccessHandler: () -> Unit) {
		storeService.collection("Fests")
			.add(fest)
			.addOnSuccessListener {
				onSuccessHandler()
			}
	}
}