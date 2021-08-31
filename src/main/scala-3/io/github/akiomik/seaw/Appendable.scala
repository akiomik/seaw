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

import scala.reflect.Selectable.reflectiveSelectable

trait Appendable[A] {
  def pad(a: A, elem: Char): A
}

object Appendable {
  implicit def appendableIsAppendable[A <: { def append(c: Char): A }]: Appendable[A] =
    new Appendable[A] {
      def pad(a: A, elem: Char): A = a.append(elem)
    }

  implicit object StringIsAppendable extends Appendable[String] {
    def pad(a: String, elem: Char): String = a :+ elem
  }
}
