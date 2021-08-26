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

import com.ibm.icu.lang.{UCharacter, UProperty}

case class CodePoint(codePoint: Int) extends AnyVal {
  def width: Int = if (isPrintable) eaToWidth(ea) else 0

  def isPrintable: Boolean = UCharacter.isPrintable(codePoint)

  def isHalfwidth: Boolean = width == 1

  def isFullwidth: Boolean = width == 2

  def isAmbiguous: Boolean = ea == UCharacter.EastAsianWidth.AMBIGUOUS

  private def ea: Int = UCharacter.getIntPropertyValue(codePoint, UProperty.EAST_ASIAN_WIDTH)

  private def eaToWidth(ea: Int): Int = ea match {
    case UCharacter.EastAsianWidth.AMBIGUOUS => 1
    case UCharacter.EastAsianWidth.FULLWIDTH => 2
    case UCharacter.EastAsianWidth.HALFWIDTH => 1
    case UCharacter.EastAsianWidth.NARROW    => 1
    case UCharacter.EastAsianWidth.NEUTRAL   => 1
    case UCharacter.EastAsianWidth.WIDE      => 2
    case _                                   => 1
  }
}
