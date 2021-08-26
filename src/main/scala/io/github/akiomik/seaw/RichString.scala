package io.github.akiomik.seaw

case class RichString(underlying: String) {
  override def toString: String = underlying

  // TODO: Add support for ligatures
  def width: Int = underlying.codePoints.reduce(0, (acc, cp) => acc + CodePoint(cp).width)
}
