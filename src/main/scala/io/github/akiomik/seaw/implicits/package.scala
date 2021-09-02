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

import java.util.PrimitiveIterator

import scala.annotation.tailrec

package object implicits {
  implicit class CharSequenceOps[T <: CharSequence](underlying: T) {
    override def toString: String = underlying.toString

    /** The display width of the CharSequence instance (e.g. String).
      *
      * @todo Add support for ligatures.
      * @return the display width of the CharSequence instance.
      */
    def width: Int = underlying.codePoints.reduce(0, (acc, cp) => acc + CodePoint(cp).width)

    /** Returns a string padded with the element to the given width.
      *
      *  @return a string with the element appended until the given target width is reached.
      */
    def padToWidth(width: Int, elem: Char)(implicit A: Appendable[T]): T = {
      @tailrec def go(a: T): T = if (CharSequenceOps(a).width >= width) a else go(A.pad(a, elem))
      go(underlying)
    }

    /** Returns a string that has the target width from the first.
      *
      * @return a string that has the target width from the first if `n` is less than the string width, the whole string otherwise.
      */
    def takeWidth(n: Int): T = {
      if (CharSequenceOps(underlying).width <= n) {
        underlying
      } else {
        @tailrec
        def go(iter: PrimitiveIterator.OfInt, width: Int, size: Int): Int = {
          val cp = CodePoint(iter.next)
          if (width + cp.width > n) size else go(iter, width + cp.width, size + cp.size)
        }

        val size = go(underlying.codePoints.iterator, 0, 0)
        underlying.subSequence(0, size).asInstanceOf[T]
      }
    }
  }
}
