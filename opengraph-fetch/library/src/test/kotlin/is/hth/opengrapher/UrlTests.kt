package `is`.hth.opengrapher

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

internal class UrlTests : StringSpec({

    "instantiate HttpUrl with 'http://' url prefix" {
        HttpUrl("http://")
    }
    "instantiate HttpUrl with 'https://' url prefix" {
        HttpUrl("https://")
    }
    "instantiate HttpUrl with empty string" {
        shouldThrow<IllegalArgumentException> {
            HttpUrl("")
        }
    }
    "instantiate HttpUrl with 'abc' string" {
        shouldThrow<IllegalArgumentException> {
            HttpUrl("abc")
        }
    }
})
