package pfa.technipixl.pingfest.model


import com.google.gson.annotations.SerializedName

class FestResults : ArrayList<FestResults.FestResultsItem>(){
    data class FestResultsItem(
        val adress: String, // {{faker 'address.streetAddress'}},{{faker 'address.city'}}
        @SerializedName("album_photo")
        val albumPhoto: List<String>,
        val challenge: String, // {{faker 'animal.cat'}}
        @SerializedName("date_fin")
        val dateFin: String, // {{faker 'date.past'}}
        val description: String, // {{faker 'lorem.paragraph'}}
        val idFest: String, // {{faker 'datatype.uuid'}}
        val image: String, // {{faker 'internet.avatar'}}
        @SerializedName("liste_participators")
        val listeParticipators: List<String>,
        val name: String, // {{faker 'word.adjective'}},{{faker 'music.genre'}}
        val organisator: String, // {{faker 'company.companyName'}}
        val type: String // {{faker 'music.genre'}}
    )
}