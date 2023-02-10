# opengrapher

![License](https://camo.githubusercontent.com/2a2157c971b7ae1deb8eb095799440551c33dcf61ea3d965d86b496a5a65df55/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4c6963656e73652d417061636865253230322e302d626c75652e737667)
![Maven Central](https://img.shields.io/maven-central/v/is.hth/opengrapher?color=blue)


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
  implementation 'is.hth:opengrapher:0.0.1'
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
  implementation 'is.hth:opengrapher:0.0.1-SNAPSHOT'
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
