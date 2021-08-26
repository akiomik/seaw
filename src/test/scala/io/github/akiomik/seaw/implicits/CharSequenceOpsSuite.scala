package io.github.akiomik.seaw

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import io.github.akiomik.seaw.implicits._

class CharSequenceOpsSuite extends AnyFunSuite with Matchers {
  test("toString returns underlying String instance") {
    val s = "To Sherlock Holmes she is always the woman."
    assert(CharSequenceOps(s).toString eq s)
  }

  test("Halfwidth characters have width which equals String#size") {
    val s = "To Sherlock Holmes she is always the woman."
    assert(s.width === s.size)
  }

  test("Fullwidth characters have width which equals doubled String#size") {
    val s = "吾輩は猫である。名前はまだない。"
    assert(s.width === s.size * 2)
  }

  // TODO: Add support for ligatures
  test("Combining emoji have width which is sum of printable character widths") {
    assert("👩🏿\u200d🔧".width === 6) // Woman Mechanic (Dark Skin Tone)
  }
}
