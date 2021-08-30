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

class AppendableSuite extends AnyFunSuite with Matchers {
  test("An Appendable[StringBuilder] instance can be found") {
    val sb = new StringBuilder("吾輩は猫である。名前はまだない。")
    assert(implicitly[Appendable[StringBuilder]].pad(sb, ' ') === sb.append(' '))
  }

  test("An Appendable[StringBuffer] instance can be found") {
    val sb = new StringBuffer("吾輩は猫である。名前はまだない。")
    assert(implicitly[Appendable[StringBuffer]].pad(sb, ' ') === sb.append(' '))
  }

  test("An Appendable[String] instance can be found") {
    val s = "吾輩は猫である。名前はまだない。"
    assert(implicitly[Appendable[String]].pad(s, ' ') === s :+ ' ')
  }
}
