package pfa.technipixl.pingfest.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pfa.technipixl.pingfest.network.FestApiServiceImpl

class DashboardViewModel : ViewModel() {
    private val service by lazy { FestApiServiceImpl() }

    fun fetchParties() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getFest()

            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    response.body()?.results?.forEach {
                        Log.d("Fest", "getFest")
                    }
                } else {

                    Log.d("Fest", "Error")
                }
            }
        }

    }
}