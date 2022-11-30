package pfa.technipixl.pingfest.model

import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import okhttp3.Challenge
import java.util.*

data class FestResult(
    val idFest: Int,
    val imageFest: ImageView,
    val name: String,
    @SerializedName("date_debut")
    val startDate: Date,
    @SerializedName("date_fin")
    val endDate: Date,
    val organisator: String,
    val adress: String,
    val type: String,
    val description: String,
    val challenge: String,
    // @SerializedName("liste_participators")
    // val participatorList:
    // @SerializedName("album_photo")
    // val albumPhotoList:
)
