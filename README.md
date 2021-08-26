# seaw

[![Scala CI](https://github.com/akiomik/seaw/actions/workflows/scala-ci.yml/badge.svg)](https://github.com/akiomik/seaw/actions/workflows/scala-ci.yml)

An alternative to wcwidth for scala, to get fixed width of Unicode characters.

## Usage

```scala
import io.github.akiomik.seaw.implicits._

"To Sherlock Holmes she is always the woman.".size  // => 43
"To Sherlock Holmes she is always the woman.".width // => 43

"吾輩は猫である。名前はまだない。".size  // => 16
"吾輩は猫である。名前はまだない。".width // => 32
```
