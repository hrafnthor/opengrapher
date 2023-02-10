package `is`.hth.opengrapher.model

public data class OpenGraph(
    val title: String = "",
    val siteName: String = "",
    val description: String = "",
    val determiner: String = "",
    val type: String = "",
    val url: String = "",
    val locale: Locale = Locale(),
    val images: List<Image> = emptyList(),
    val videos: List<Video> = emptyList(),
    val audios: List<Audio> = emptyList()
)
