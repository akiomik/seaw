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

package object implicits {
  implicit class CharSequenceOps[T <: CharSequence](underlying: T) {
    override def toString: String = underlying.toString

    // TODO: Add support for ligatures
    def width: Int = underlying.codePoints.reduce(0, (acc, cp) => acc + CodePoint(cp).width)
  }
}
