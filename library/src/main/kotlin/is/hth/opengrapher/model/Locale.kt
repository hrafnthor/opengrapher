package `is`.hth.opengrapher.model

public data class Locale(
    val default: String = "",
    val alternate: List<String> = emptyList()
)
