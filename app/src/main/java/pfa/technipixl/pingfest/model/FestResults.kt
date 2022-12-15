package pfa.technipixl.pingfest.model

import java.util.*

data class FestResults(
    val adress: String = "", // {{faker 'address.streetAddress'}},{{faker 'address.city'}}
    val albumPhoto: List<String> = listOf(),
    val challenge: String = "", // {{faker 'animal.cat'}}
    val dateDebut: Long = Date().time, // {{faker 'date.past'}}
    val dateFin: Long = Date().time + 10_000, // {{faker 'date.past'}}
    val description: String = "", // {{faker 'lorem.paragraph'}}
    var image: String? = null, // {{faker 'internet.avatar'}}
    val participators: List<String> = listOf(),
    val name: String = "", // {{faker 'word.adjective'}},{{faker 'music.genre'}}
    val organisator: List<String> = listOf(), // {{faker 'company.companyName'}}
    val type: List<MusicGenre> = listOf() // {{faker 'music.genre'}}
)
