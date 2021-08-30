# seaw

[![seaw Scala version support](https://index.scala-lang.org/akiomik/seaw/seaw/latest.svg?color=blue&style=flat)](https://index.scala-lang.org/akiomik/seaw/seaw)
[![Scala CI](https://github.com/akiomik/seaw/actions/workflows/scala-ci.yml/badge.svg)](https://github.com/akiomik/seaw/actions/workflows/scala-ci.yml)

An alternative to wcwidth for scala, to get fixed width of Unicode characters.

## Getting started

seaw is currently available for Scala 2.12, 2.13 and 3.0.

Add the following line to your `build.sbt`.

```scala
libraryDependencies += "io.github.akiomik" %% "seaw" % "0.1.0"
```

## Usage

```scala
import io.github.akiomik.seaw.implicits._

"To Sherlock Holmes she is always the woman.".size  // => 43
"To Sherlock Holmes she is always the woman.".width // => 43

"吾輩は猫である。名前はまだない。".size  // => 16
"吾輩は猫である。名前はまだない。".width // => 32
```

## Publishing

```sh
sbt "+ publishSigned; sonatypeBundleRelease"
```
