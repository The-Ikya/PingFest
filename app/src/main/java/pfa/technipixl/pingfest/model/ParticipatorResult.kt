package pfa.technipixl.pingfest.model

data class ParticipatorResult(
    val results: List<Participator>
) {
    data class Participator(
        val album: MutableList<String>,
        var email: String?, // {{faker 'internet.email'}}
        var idPeople: String?, // {{faker 'datatype.uuid'}}
        var login: String?, // {{faker 'music.genre'}}{{faker 'word.adjective'}}
        val partyList: MutableList<String>,
        val friendList: MutableList<String>, // {{faker 'random.alphaNumeric' 10}}
        var photo: String?, // {{faker 'internet.avatar'}}
    )
}
