package `is`.hth.opengrapher.tree


internal fun add(key: String, value: String, root: Node) {
    val keys: MutableList<String> = key.lowercase().split(":").toMutableList()
    val firstKey = keys.removeFirstOrNull() ?: ""
    if (firstKey != "og") {
        throw IllegalArgumentException("Property key did not start with required prefix 'og:' !")
    }
    add(keys, value, root)
}

private fun add(keys: MutableList<String>, value: String, node: Node) {
    val firstKey = getKey(keys)
    val childIndex = if (firstKey.isNotBlank()) node.children.indexOfFirst { it.key == firstKey } else -1
    when {
        childIndex == -1 && keys.isEmpty() -> node.children.add(Node(key = firstKey, value = value))
        childIndex >= 0 -> add(keys, value, node.children[childIndex])
        else -> {
            val child = Node(key = firstKey, value = "")
            add(keys, value, child)
            node.children.add(child)
        }
    }
}

private val mediaRootRegex: Regex = "^(image|video|audio)".toRegex()

private fun getKey(keys: MutableList<String>): String {
    val firstKey = keys.removeFirstOrNull() ?: ""
    if (keys.isEmpty() && firstKey.matches(mediaRootRegex)) {
        // Special case where media root tags (image, video, audio) contain the 'url' content directly
        // rather than using a leaf tag (i.e. og:image:url).
        keys.add("url")
    }
    return firstKey
}