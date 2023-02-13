package `is`.hth.opengrapher

import `is`.hth.opengrapher.tree.Node
import `is`.hth.opengrapher.tree.add
import it.skrape.core.document
import it.skrape.fetcher.*
import it.skrape.selects.html5.meta

internal suspend fun scrape(httpUrl: HttpUrl): Node {
    return skrape(AsyncFetcher) {
        request {
            url = httpUrl.url
            method = Method.GET
        }
        response { Node(key = "og", value = "").also(::toTree) }
    }
}

private val openGraphRegex = "^og[:\\w]*\$".toRegex()

private fun Result.toTree(root: Node) {
    document.meta {
        withAttributeKey = "property"
        findAll { filter { it.attribute("property").matches(openGraphRegex) } }
    }.forEach { element ->
        add(key = element.attribute("property"), value = element.attribute("content"), root)
    }
}
