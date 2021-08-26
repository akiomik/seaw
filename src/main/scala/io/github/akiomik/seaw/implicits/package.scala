package io.github.akiomik.seaw

package object implicits {
  implicit class CharSequenceOps[T <: CharSequence](underlying: T) {
    override def toString: String = underlying.toString

    // TODO: Add support for ligatures
    def width: Int = underlying.codePoints.reduce(0, (acc, cp) => acc + CodePoint(cp).width)
  }
}
