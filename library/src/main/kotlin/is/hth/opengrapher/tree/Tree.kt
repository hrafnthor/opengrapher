package `is`.hth.opengrapher.tree

internal fun add(key: String, value: String, root: Node) {
    if (key.isNotBlank() && openGraphSpecification.contains(key)) {
        add(key.removePrefix("og:").split(":").toMutableList(), value, root)
    }
}

private fun add(keys: MutableList<String>, value: String, parent: Node) {
    val keySegment = keys.removeFirstOrNull() ?: return

    when {
        keys.isEmpty() && parent.key == keySegment -> parent.value = value
        keys.isEmpty() -> {
            when {
                keySegment.matches(mediaRootRegex) -> "url"
                keySegment.matches(localeRootRegex) -> "default"
                keySegment.matches(localeAlternateRegex) -> "value"
                else -> null
            }?.apply(keys::add)

            val child = Node(key = keySegment, value = "").apply(parent.children::add)
            if (keys.isEmpty()) {
                child.value = value
            } else {
                add(keys, value, child)
            }
        }

        else -> {
            val child = parent.children.lastOrNull { it.key == keySegment } ?: Node(
                key = keySegment,
                value = ""
            ).apply(parent.children::add)
            add(keys, value, child)
        }
    }
}


private val mediaRootRegex: Regex = "^(image|video|audio)\$".toRegex()
private val localeRootRegex: Regex = "^locale\$".toRegex()
private val localeAlternateRegex: Regex = "^alternate\$".toRegex()

/**
 * The Open Graph Protocol specification as described on https://ogp.me.
 */
private val openGraphSpecification: Set<String> = setOf(
    // Required
    "og:title",
    "og:type",
    "og:url",
    "og:image",

    // Optional
    "og:audio",
    "og:description",
    "og:determiner",
    "og:locale",
    "og:locale:alternate",
    "og:site_name",
    "og:video",

    // Structured Property - Image
    "og:image:url",
    "og:image:secure_url",
    "og:image:type",
    "og:image:width",
    "og:image:height",
    "og:image:alt",

    // Structured Property - Video
    "og:video:url",
    "og:video:secure_url",
    "og:video:type",
    "og:video:width",
    "og:video:height",

    // Structured Property - Audio
    "og:audio:url",
    "og:audio:secure_url",
    "og:audio:type",
)
