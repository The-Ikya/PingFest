package pfa.technipixl.pingfest.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pfa.technipixl.pingfest.model.FestResult
import pfa.technipixl.pingfest.network.FestApiServiceImpl

class DashboardViewModel : ViewModel() {
    private val service by lazy { FestApiServiceImpl() }

    fun fetchParties() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getFest()
            withContext(Dispatchers.Main) {

                if (response.isSuccessful) {
                    response.body()?.result?.let {
                        Log.d("FEST", "fetchParties: $it")
                        displayResult(it)
                    }
                }
            }
        }

    }

    fun displayResult(results: List<FestResult>) {

    }
}