package com.weeklyhacks.holdem

case class Card(rank: Rank.Value, suit: Suit.Value, open: Boolean = false) {
  override def toString: String = if (open) s"$rank$suit" else "\u2591\u2591"
}
