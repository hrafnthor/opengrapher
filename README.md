# opengrapher

[![License](https://img.shields.io/badge/License-Apache%202.0-blue)](https://choosealicense.com/licenses/apache-2.0/)
[![Maven Central](https://img.shields.io/maven-central/v/is.hth/opengrapher?color=blue)](https://central.sonatype.com/artifact/is.hth/opengrapher/0.0.1)


OpenGrapher is a small utility library for fetching and processing Open Graph information from webpages.

Simply point it towards an url, and it will fetch and process any Open Graph meta tags it encounters.

```kotlin
val graph = OpenGrapher.fetch(HttpUrl("https://github.com/hrafnthor/opengrapher"))
```

There is also the option of having the graph returned via a monad

```kotlin
OpenGrapher.fetchResult(HttpUrl("https://github.com/hrafnthor/opengrapher"))
    .onSuccess {
        // Use resulting graph
    }
    .onFailure {
        // Respond to failure
    }
```

It is that simple.

#### Download

```
repositories {
  mavenCentral()
}

dependencies {
  implementation 'is.hth:opengrapher:0.0.2'
}
```

<details>
<summary>Snapshot from the latest development version are available at Sonatype's snapshot repository</summary>
<p>

```
repositories {
  mavenCentral()
  maven {
    url 'https://s01.oss.sonatype.org/content/repositories/snapshots/'
  }
}

dependencies {
  implementation 'is.hth:opengrapher:0.0.2-SNAPSHOT'
}
```

</p>
</details>


License
-------

    Copyright 2023 Hrafn Thorvaldsson

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
