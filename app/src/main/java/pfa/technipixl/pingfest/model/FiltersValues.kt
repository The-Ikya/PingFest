package pfa.technipixl.pingfest.model

data class FiltersValues(
	var distance: Float = 0.25f,
	var size: EventSize = EventSize.medium,
	var selectedGenres: MutableMap<MusicGenre, Boolean> = mutableMapOf(),
	var preferedSort: EventSorting = EventSorting.eventName
) {
	init {
		MusicGenre.values().forEach {
			selectedGenres[it] = false
		}
	}
}

enum class EventSize(val text: String) {
	small("0 à 150"),
	medium("150 à 300"),
	large("300 à 700"),
	giant("Plus de 750")
}

enum class EventSorting {
	eventName,
	distance,
	friendCount,
	genreCount,
	eventSize
}