package `is`.hth.opengrapher

import `is`.hth.opengrapher.tree.Node
import `is`.hth.opengrapher.tree.add
import org.jsoup.Jsoup


internal fun scrape(httpUrl: HttpUrl): Node {
    val root = Node(key = "og", value = "")
    val doc = Jsoup.connect(httpUrl.url).get()
    doc.select("head > meta[property^=og]").map { element ->
        add(key = element.attr("property"), value = element.attr("content"), root)
    }
    return root
}
