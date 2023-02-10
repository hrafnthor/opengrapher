package `is`.hth.opengrapher

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import `is`.hth.opengrapher.tree.Node
import `is`.hth.opengrapher.tree.add

public class TreeTests : StringSpec({

    val root = Node(key = "og", value = "")
    beforeEach { root.children.clear() }

    "Adding empty key" {
        val copy = root.copy()
        add(key = "", value = "", root)
        root shouldBe copy
    }

    "Adding non prefixed key does nothing" {
        val copy = root.copy()
        add(key = "books", value = "", root)
        root shouldBe copy
    }

    "Adding only 'og:' key does nothing" {
        val copy = root.copy()
        add(key = "og:", value = "", root)
        root shouldBe copy
    }

    "Adding empty key does nothing" {
        val copy = root.copy()
        add(key = "", value = "", root)
        root shouldBe copy
    }

    "Adding non standard 'og:' prefixed key does nothing" {
        val copy = root.copy()
        add(key = "og:image:url:not:in:spec", value = "", root)
        root shouldBe copy
    }

    "Adding leaf key results in a tree of exactly height 1" {
        add(key = "og:url", value = "", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 0
    }

    "Adding leaf key assigns leaf a value" {
        add(key = "og:url", value = "value", root)
        root.children.first().value shouldBe "value"
    }

    "Adding 'og:image' key results in a tree of exactly height two" {
        add(key = "og:image", value = "", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
    }

    "Adding 'og:image:url' key results in a tree of exactly height two" {
        add(key = "og:image:url", value = "", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
    }

    "Adding two 'og:image' keys results in a tree of exactly height two" {
        add(key = "og:image", value = "", root)
        add(key = "og:image", value = "", root)
        root.children.size shouldBe 2
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
        root.children.second().children.size shouldBe 1
        root.children.second().children.first().children.size shouldBe 0
    }

    "Adding two 'og:image' keys results in two children with separate values" {
        add(key = "og:image", value = "localhost1", root)
        add(key = "og:image", value = "localhost2", root)
        root.children.size shouldBe 2
        root.children.first() shouldNotBe root.children.second()
    }

    "Adding 'og:video' key results in a tree of exactly height two" {
        add(key = "og:video", value = "", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
    }

    "Adding 'og:video:url' key results in a tree of exactly height two" {
        add(key = "og:video:url", value = "", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
    }

    "Adding two 'og:video' keys results in a tree of exactly height two" {
        add(key = "og:video", value = "", root)
        add(key = "og:video", value = "", root)
        root.children.size shouldBe 2
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
        root.children.second().children.size shouldBe 1
        root.children.second().children.first().children.size shouldBe 0
    }

    "Adding two 'og:video' keys results in two children with separate values" {
        add(key = "og:video", value = "localhost1", root)
        add(key = "og:video", value = "localhost2", root)
        root.children.size shouldBe 2
        root.children.first() shouldNotBe root.children.second()
    }

    "Adding 'og:audio' key results in a tree of exactly height two" {
        add(key = "og:audio", value = "", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
    }

    "Adding 'og:audio:url' key results in a tree of exactly height two" {
        add(key = "og:audio:url", value = "", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
    }

    "Adding two 'og:audio' keys results in a tree of exactly height two" {
        add(key = "og:audio", value = "", root)
        add(key = "og:audio", value = "", root)
        root.children.size shouldBe 2
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
        root.children.second().children.size shouldBe 1
        root.children.second().children.first().children.size shouldBe 0
    }

    "Adding two 'og:audio' keys results in two children with separate values" {
        add(key = "og:audio", value = "localhost1", root)
        add(key = "og:audio", value = "localhost2", root)
        root.children.size shouldBe 2
        root.children.first() shouldNotBe root.children.second()
    }

    "Adding 'og:locale' key results in a tree of exactly height two" {
        add(key = "og:locale", value = "", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 0
    }

    "Adding two 'og:locale' keys results in two children with separate values" {
        add(key = "og:locale", value = "is", root)
        add(key = "og:locale", value = "en", root)
        root.children.size shouldBe 2
        root.children.first() shouldNotBe root.children.second()
    }

    "Adding 'og:locale:alternate' key results in tree of exactly height three" {
        add(key = "og:locale:alternate", value = "is", root)
        root.children.size shouldBe 1
        root.children.first().children.size shouldBe 1
        root.children.first().children.first().children.size shouldBe 1
        root.children.first().children.first().children.first().children.size shouldBe 0
    }
})
