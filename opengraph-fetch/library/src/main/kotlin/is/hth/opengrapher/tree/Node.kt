package `is`.hth.opengrapher.tree

internal data class Node(
    val key: String,
    var value: String,
    val children: MutableList<Node> = mutableListOf()
)
