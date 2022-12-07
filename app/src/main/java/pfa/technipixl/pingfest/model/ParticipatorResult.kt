package pfa.technipixl.pingfest.model


import com.google.gson.annotations.SerializedName

data class ParticipatorResult(
    val results: List<Result>
) {
    data class Result(
        val album: List<String>,
        val email: String, // {{faker 'internet.email'}}
        val idPeople: String, // {{faker 'datatype.uuid'}}
        val login: String, // {{faker 'music.genre'}}{{faker 'word.adjective'}}
        val partyList: List<String>,
        val password: String, // {{faker 'random.alphaNumeric' 10}}
        val photo: String // {{faker 'internet.avatar'}}
    )
}