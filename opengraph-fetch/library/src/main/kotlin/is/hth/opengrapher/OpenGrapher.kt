package `is`.hth.opengrapher

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import `is`.hth.opengrapher.data.toGraph
import `is`.hth.opengrapher.model.OpenGraph

public object OpenGrapher {

    /**
     * Fetches HTML from the supplied url and parses any Open Graph information into
     * the supplied [OpenGraph] object.
     *
     * To have custom default values for fields that are not found, preconfigure the
     * supplied [OpenGraph] object with those fields.
     */
    public suspend fun fetch(
        httpUrl: HttpUrl,
        default: OpenGraph = OpenGraph()
    ): OpenGraph = scrape(httpUrl).toGraph(default = default)

    /**
     * Fetches HTML from the supplied url and parses any Open Graph information into
     * the supplied [OpenGraph] object.
     *
     * To have custom default values for fields that are not found, preconfigure the
     * supplied [OpenGraph] object with those fields.
     */
    public suspend fun fetchResult(
        httpUrl: HttpUrl,
        default: OpenGraph = OpenGraph()
    ): Result<OpenGraph, Throwable> =
        runCatching {
            fetch(httpUrl, default)
        }.fold(
            onSuccess = { Ok(it) },
            onFailure = { Err(it) }
        )
}
