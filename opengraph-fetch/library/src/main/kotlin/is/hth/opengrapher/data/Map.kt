package `is`.hth.opengrapher.data

import `is`.hth.opengrapher.model.*
import `is`.hth.opengrapher.tree.Node

internal fun Node.toGraph(default: OpenGraph = OpenGraph()): OpenGraph {
    return children.fold(default) { graph, node ->
        when (node.key) {
            "title" -> graph.copy(title = node.value)
            "type" -> graph.copy(type = node.value)
            "url" -> graph.copy(url = node.value)
            "description" -> graph.copy(description = node.value)
            "determiner" -> graph.copy(determiner = node.value)
            "site_name" -> graph.copy(siteName = node.value)
            "locale" -> graph.copy(locale = node.toLocale())
            "image" -> graph.copy(images = graph.images + node.toImage())
            "video" -> graph.copy(videos = graph.videos + node.toVideo())
            "audio" -> graph.copy(audios = graph.audios + node.toAudio())
            else -> graph
        }
    }
}

internal fun Node.toLocale(default: Locale = Locale()): Locale {
    return children.fold(default) { locale, node ->
        when (node.key) {
            "default" -> locale.copy(default = node.value)
            "alternate" -> locale.copy(alternate = node.firstChildValue(locale.alternate))
            else -> locale
        }
    }
}

internal fun Node.firstChildValue(list: List<String>): List<String> {
    return list + (children.firstOrNull()?.value ?: return list)
}

internal fun Node.toImage(default: Image = Image()): Image {
    return children.fold(default) { image, node ->
        when (node.key) {
            "url" -> image.copy(url = node.value)
            "secure_url" -> image.copy(secureUrl = node.value)
            "type" -> image.copy(type = node.value)
            "width" -> image.copy(width = node.value.toIntOrNull() ?: -1)
            "height" -> image.copy(height = node.value.toIntOrNull() ?: -1)
            "alt" -> image.copy(alt = node.value)
            else -> image
        }
    }
}

internal fun Node.toAudio(default: Audio = Audio()): Audio {
    return children.fold(default) { audio, node ->
        when (node.key) {
            "url" -> audio.copy(url = node.value)
            "secure_url" -> audio.copy(secureUrl = node.value)
            "type" -> audio.copy(type = node.value)
            else -> audio
        }
    }
}

internal fun Node.toVideo(default: Video = Video()): Video {
    return children.fold(default) { video, node ->
        when (node.key) {
            "url" -> video.copy(url = node.value)
            "secure_url" -> video.copy(secureUrl = node.value)
            "type" -> video.copy(type = node.value)
            "width" -> video.copy(width = node.value.toIntOrNull() ?: -1)
            "height" -> video.copy(height = node.value.toIntOrNull() ?: -1)
            else -> video
        }
    }
}
