package `is`.hth.opengrapher.model

public data class Image(
    val url: String = "",
    val secureUrl: String = "",
    val type: String = "",
    val width: Int = -1,
    val height: Int = -1,
    val alt: String = ""
)
