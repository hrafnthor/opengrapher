package `is`.hth.opengrapher.tree

internal class Node(
    val key: String,
    val value: String,
) {
    val children: MutableList<Node> = mutableListOf()
}
