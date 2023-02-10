import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import `is`.hth.opengrapher.HttpUrl
import `is`.hth.opengrapher.OpenGrapher
import kotlinx.coroutines.runBlocking
import lumber.log.Lumber

public fun main() {
    Lumber.plant(Lumber.DebugTree())

    runBlocking {
        OpenGrapher.fetchResult(HttpUrl("https://github.com/hrafnthor/opengrapher"))
    }.onSuccess {
        Lumber.i("Extraction succeeded: $it")
    }.onFailure {
        Lumber.i("Extraction failed $it")
    }
}
