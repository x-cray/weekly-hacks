package com.weeklyhacks.holdem

object Rank extends Enumeration {
  protected case class Val(name: String, ordinalValue: Int) extends super.Val {
    override def toString(): String = name
  }

  val _2 = Val("2", 1)
  val _3 = Val("3", 2)
  val _4 = Val("4", 3)
  val _5 = Val("5", 4)
  val _6 = Val("6", 5)
  val _7 = Val("7", 6)
  val _8 = Val("8", 7)
  val _9 = Val("9", 8)
  val _10 = Val("10", 9)
  val J = Val("J", 10)
  val Q = Val("Q", 11)
  val K = Val("K", 12)
  val A = Val("A", 13)
}
