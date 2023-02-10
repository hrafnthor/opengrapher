package `is`.hth.opengrapher

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import `is`.hth.opengrapher.data.toGraph
import `is`.hth.opengrapher.model.*
import `is`.hth.opengrapher.tree.Node
import `is`.hth.opengrapher.tree.add

internal class MapTests : StringSpec({

    val image1 = Image(
        url = "url1",
        secureUrl = "secureUrl1",
        type = "type1",
        width = 1,
        height = 1,
        alt = "alt1"
    )
    val image2 = Image(
        url = "url2",
        secureUrl = "secureUrl2",
        type = "type2",
        width = 2,
        height = 2,
        alt = "alt2"
    )

    val video1 = Video(
        url = "url1",
        secureUrl = "secureUrl1",
        type = "type1",
        width = 1,
        height = 1,
    )
    val video2 = Video(
        url = "url2",
        secureUrl = "secureUrl2",
        type = "type2",
        width = 2,
        height = 2,
    )

    val audio1 = Audio(
        url = "url1",
        secureUrl = "secureUrl1",
        type = "type1"
    )
    val audio2 = Audio(
        url = "url2",
        secureUrl = "secureUrl2",
        type = "type2"
    )

    val locale = Locale(
        default = "default",
        alternate = listOf("alternate1", "alternate2", "alternate3")
    )

    val graph = OpenGraph(
        title = "title",
        siteName = "siteName",
        description = "description",
        determiner = "determiner",
        type = "type",
        url = "url",
        locale = locale,
        images = listOf(image1, image2),
        videos = listOf(video1, video2),
        audios = listOf(audio1, audio2)
    )


    "Map a OpenGraph object" {
        val root = Node(key = "og", value = "")
        add(key = "og:title", value = graph.title, root)
        add(key = "og:site_name", value = graph.siteName, root)
        add(key = "og:description", value = graph.description, root)
        add(key = "og:determiner", value = graph.determiner, root)
        add(key = "og:type", value = graph.type, root)
        add(key = "og:url", value = graph.url, root)
        add(key = "og:locale", value = graph.locale.default, root)
        graph.locale.alternate.forEach { add(key = "og:locale:alternate", value = it, root) }
        graph.images.forEach { image ->
            add(key = "og:image", value = image.url, root)
            add(key = "og:image:secure_url", value = image.secureUrl, root)
            add(key = "og:image:type", value = image.type, root)
            add(key = "og:image:width", value = image.width.toString(), root)
            add(key = "og:image:height", value = image.height.toString(), root)
            add(key = "og:image:alt", value = image.alt, root)
        }
        graph.videos.forEach { video ->
            add(key = "og:video", value = video.url, root)
            add(key = "og:video:secure_url", value = video.secureUrl, root)
            add(key = "og:video:type", value = video.type, root)
            add(key = "og:video:width", value = video.width.toString(), root)
            add(key = "og:video:height", value = video.height.toString(), root)
        }
        graph.audios.forEach { audio ->
            add(key = "og:audio", value = audio.url, root)
            add(key = "og:audio:secure_url", value = audio.secureUrl, root)
            add(key = "og:audio:type", value = audio.type, root)
        }

        root.toGraph() shouldBe graph
    }

    "Map Locale object" {
        val root = Node(key = "og", value = "")

        add(key = "og:locale", value = locale.default, root)
        locale.alternate.forEach { add(key = "og:locale:alternate", value = it, root) }

        root.toGraph().locale shouldBe locale
    }

    "Map Image object" {
        val root = Node(key = "og", value = "")

        add(key = "og:image", value = image1.url, root)
        add(key = "og:image:secure_url", value = image1.secureUrl, root)
        add(key = "og:image:type", value = image1.type, root)
        add(key = "og:image:width", value = image1.width.toString(), root)
        add(key = "og:image:height", value = image1.height.toString(), root)
        add(key = "og:image:alt", value = image1.alt, root)

        val generatedGraph = root.toGraph()
        generatedGraph.images.size shouldBe 1
        generatedGraph.images.first() shouldBe image1
    }

    "Map two Image objects" {
        val root = Node(key = "og", value = "")

        add(key = "og:image", value = image1.url, root)
        add(key = "og:image:secure_url", value = image1.secureUrl, root)
        add(key = "og:image:type", value = image1.type, root)
        add(key = "og:image:width", value = image1.width.toString(), root)
        add(key = "og:image:height", value = image1.height.toString(), root)
        add(key = "og:image:alt", value = image1.alt, root)
        add(key = "og:image", value = image2.url, root)
        add(key = "og:image:secure_url", value = image2.secureUrl, root)
        add(key = "og:image:type", value = image2.type, root)
        add(key = "og:image:width", value = image2.width.toString(), root)
        add(key = "og:image:height", value = image2.height.toString(), root)
        add(key = "og:image:alt", value = image2.alt, root)

        val generatedGraph = root.toGraph()
        generatedGraph.images.size shouldBe 2
        generatedGraph.images.first() shouldBe image1
        generatedGraph.images.second() shouldBe image2
    }

    "Map Video object" {
        val root = Node(key = "og", value = "")

        add(key = "og:video", value = video1.url, root)
        add(key = "og:video:secure_url", value = video1.secureUrl, root)
        add(key = "og:video:type", value = video1.type, root)
        add(key = "og:video:width", value = video1.width.toString(), root)
        add(key = "og:video:height", value = video1.height.toString(), root)

        val generatedGraph = root.toGraph()
        generatedGraph.videos.size shouldBe 1
        generatedGraph.videos.first() shouldBe video1
    }

    "Map two Video objects" {
        val root = Node(key = "og", value = "")

        add(key = "og:video", value = video1.url, root)
        add(key = "og:video:secure_url", value = video1.secureUrl, root)
        add(key = "og:video:type", value = video1.type, root)
        add(key = "og:video:width", value = video1.width.toString(), root)
        add(key = "og:video:height", value = video1.height.toString(), root)
        add(key = "og:video", value = video2.url, root)
        add(key = "og:video:secure_url", value = video2.secureUrl, root)
        add(key = "og:video:type", value = video2.type, root)
        add(key = "og:video:width", value = video2.width.toString(), root)
        add(key = "og:video:height", value = video2.height.toString(), root)

        val generatedGraph = root.toGraph()
        generatedGraph.videos.size shouldBe 2
        generatedGraph.videos.first() shouldBe video1
        generatedGraph.videos.second() shouldBe video2
    }

    "Map Audio object" {
        val root = Node(key = "og", value = "")

        add(key = "og:audio", value = audio1.url, root)
        add(key = "og:audio:secure_url", value = audio1.secureUrl, root)
        add(key = "og:audio:type", value = audio1.type, root)

        val generatedGraph = root.toGraph()
        generatedGraph.audios.size shouldBe 1
        generatedGraph.audios.first() shouldBe audio1
    }

    "Map two Audio objects" {
        val root = Node(key = "og", value = "")

        add(key = "og:audio", value = audio1.url, root)
        add(key = "og:audio:secure_url", value = audio1.secureUrl, root)
        add(key = "og:audio:type", value = audio1.type, root)
        add(key = "og:audio", value = audio2.url, root)
        add(key = "og:audio:secure_url", value = audio2.secureUrl, root)
        add(key = "og:audio:type", value = audio2.type, root)

        val generatedGraph = root.toGraph()
        generatedGraph.audios.size shouldBe 2
        generatedGraph.audios.first() shouldBe audio1
        generatedGraph.audios.second() shouldBe audio2
    }
})
