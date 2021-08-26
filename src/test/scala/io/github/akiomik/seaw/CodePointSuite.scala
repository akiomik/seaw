// Copyright 2021 Akiomi Kamakura
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package io.github.akiomik.seaw

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks._

class CodePointSuite extends AnyFunSuite with Matchers {
  test("Table-based testing") {
    val codePoints =
      Table(
        ("s", "width", "isPrintable", "isHalfwidth", "isFullwidth", "isAmbiguous"),
        ("\n", 0, false, false, false, false),
        ("\u200b", 0, false, false, false, false), // zwsp
        ("a", 1, true, true, false, false),
        ("ｱ", 1, true, true, false, false),
        ("1", 1, true, true, false, false),
        (" ", 1, true, true, false, false),
        ("☆", 1, true, true, false, true), // ambiguous emoji
        ("ä", 1, true, true, false, false),
        ("𝒂", 1, true, true, false, false),
        ("ａ", 2, true, false, true, false),
        ("ア", 2, true, false, true, false),
        ("ア", 2, true, false, true, false),
        ("９", 2, true, false, true, false),
        ("　", 2, true, false, true, false),
        ("😂", 2, true, false, true, false)
      )

    forAll(codePoints) { (s: String, width: Int, isPrintable: Boolean, isHalfwidth: Boolean, isFullwidth: Boolean, isAmbiguous: Boolean) =>
      val cp = CodePoint(s.codePointAt(0))
      assert(cp.width === width)
      assert(cp.isPrintable === isPrintable)
      assert(cp.isHalfwidth === isHalfwidth)
      assert(cp.isFullwidth === isFullwidth)
      assert(cp.isAmbiguous === isAmbiguous)
    }
  }
}
