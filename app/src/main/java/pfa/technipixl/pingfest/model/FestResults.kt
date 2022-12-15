package pfa.technipixl.pingfest.model

data class FestResults(
    val adress: String = "", // {{faker 'address.streetAddress'}},{{faker 'address.city'}}
    val albumPhoto: List<String> = listOf(),
    val challenge: String = "", // {{faker 'animal.cat'}}
    val dateDebut: String = "", // {{faker 'date.past'}}
    val dateFin: String = "", // {{faker 'date.past'}}
    val description: String = "", // {{faker 'lorem.paragraph'}}
    val image: String? = null, // {{faker 'internet.avatar'}}
    val listeParticipators: List<String> = listOf(),
    val name: String = "", // {{faker 'word.adjective'}},{{faker 'music.genre'}}
    val organisator: String? = null, // {{faker 'company.companyName'}}
    val type: List<MusicGenre> = listOf() // {{faker 'music.genre'}}
)
