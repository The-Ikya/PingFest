package pfa.technipixl.pingfest.model

data class Participator(
    val album: MutableList<String> = mutableListOf(),
    var email: String? = null, // {{faker 'internet.email'}}
    var idPeople: String? = null, // {{faker 'datatype.uuid'}}
    var login: String? = null, // {{faker 'music.genre'}}{{faker 'word.adjective'}}
    val partyList: MutableList<String> = mutableListOf(),
    val friendList: MutableList<String> = mutableListOf(), // {{faker 'random.alphaNumeric' 10}}
    var photo: String? = null, // {{faker 'internet.avatar'}}
)
