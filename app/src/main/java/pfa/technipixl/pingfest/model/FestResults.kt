package pfa.technipixl.pingfest.model


data class Fests(
    val results: List<FestResults>
)
data class FestResults(
    val adress: String = "", // {{faker 'address.streetAddress'}},{{faker 'address.city'}}
//    @SerializedName("album_photo")
    val albumPhoto: List<String> = listOf(),
    val challenge: String = "", // {{faker 'animal.cat'}}
//    @SerializedName("date_debut")
    val dateDebut: String = "", // {{faker 'date.past'}}
//    @SerializedName("date_fin")
    val dateFin: String = "", // {{faker 'date.past'}}
    val description: String = "", // {{faker 'lorem.paragraph'}}
//    val idFest: String = "", // {{faker 'datatype.uuid'}}
    val image: String? = null, // {{faker 'internet.avatar'}}
//    @SerializedName("liste_participators")
    val listeParticipators: List<String> = listOf(),
    val name: String = "", // {{faker 'word.adjective'}},{{faker 'music.genre'}}
    val organisator: String? = null, // {{faker 'company.companyName'}}
    val type: List<MusicGenre> = listOf() // {{faker 'music.genre'}}
)