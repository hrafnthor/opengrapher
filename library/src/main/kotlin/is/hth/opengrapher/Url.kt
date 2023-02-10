package `is`.hth.opengrapher

@JvmInline
public value class HttpUrl(public val url: String) {
    init {
        require(url.startsWith(HTTP, ignoreCase = true) || url.startsWith(HTTPS, ignoreCase = true)) {
            "HttpUrl requires the url to start with '$HTTP' or '$HTTPS'"
        }
    }
}

private const val HTTP = "http://"
private const val HTTPS = "https://"
