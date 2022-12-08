package pfa.technipixl.pingfest.model

data class ParticipatorResult(
    val results: List<Participator>
) {
    data class Participator(
        val album: List<String>?,
        val email: String, // {{faker 'internet.email'}}
        val idPeople: String, // {{faker 'datatype.uuid'}}
        val login: String, // {{faker 'music.genre'}}{{faker 'word.adjective'}}
        val partyList: List<String>?,
        val friendList: List<String>?, // {{faker 'random.alphaNumeric' 10}}
        val photo: String? // {{faker 'internet.avatar'}}
    )
}
