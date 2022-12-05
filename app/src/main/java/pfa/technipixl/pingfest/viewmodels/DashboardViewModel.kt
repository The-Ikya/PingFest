package pfa.technipixl.pingfest.viewmodels

import androidx.lifecycle.ViewModel
import pfa.technipixl.pingfest.network.FestApiServiceImpl

class DashboardViewModel : ViewModel(){
    private val service by lazy { FestApiServiceImpl()}

    suspend fun fetchParties(){
        service.getFest()
    }
}