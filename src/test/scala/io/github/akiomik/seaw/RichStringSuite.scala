package io.github.akiomik.seaw

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class RichStringSuite extends AnyFunSuite with Matchers {
  test("toString returns underlying String instance") {
    val s = "To Sherlock Holmes she is always the woman."
    assert(RichString(s).toString eq s)
  }

  test("Halfwidth characters have width which equals String#size") {
    val rs = RichString("To Sherlock Holmes she is always the woman.")
    assert(rs.width === rs.toString.size)
  }

  test("Fullwidth characters have width which equals doubled String#size") {
    val rs = RichString("吾輩は猫である。名前はまだない。")
    assert(rs.width === rs.toString.size * 2)
  }

  // TODO: Add support for ligatures
  test("Combining emoji have width which is sum of printable character widths") {
    assert(RichString("👩🏿\u200d🔧").width === 6) // Woman Mechanic (Dark Skin Tone)
  }
}
