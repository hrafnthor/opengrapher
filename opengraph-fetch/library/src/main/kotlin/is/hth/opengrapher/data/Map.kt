package `is`.hth.opengrapher.data

import `is`.hth.opengrapher.model.*
import `is`.hth.opengrapher.tree.Node

internal fun Node.toGraph(default: OpenGraph = OpenGraph()): OpenGraph = default

internal fun Node.toLocale(default: Locale = Locale()): Locale = default

internal fun Node.firstChildValue(list: List<String>): List<String> = list

internal fun Node.toImage(default: Image = Image()): Image = default

internal fun Node.toAudio(default: Audio = Audio()): Audio = default

internal fun Node.toVideo(default: Video = Video()): Video = default
