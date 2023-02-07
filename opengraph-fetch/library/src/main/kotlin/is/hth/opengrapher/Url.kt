package `is`.hth.opengrapher

@JvmInline
public value class HttpUrl(public val url: String) {
    init {
        require(url.startsWithAny(ignoreCase = true, "http://", "https://")) {
            "HttpUrl requires the url to start with 'http://' or 'https://'"
        }
    }
}

private fun String.startsWithAny(ignoreCase: Boolean, vararg values: String): Boolean {
    return values.firstOrNull { startsWith(it, ignoreCase = ignoreCase) } != null
}
