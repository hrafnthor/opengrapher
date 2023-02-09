package `is`.hth.opengrapher.model

public data class Video(
    val url: String = "",
    val secureUrl: String = "",
    val type: String = "",
    val width: Int = -1,
    val height: Int = -1,
    val duration: Int = -1,
    val releaseDate: String = "",
    val series: String = "",
)
