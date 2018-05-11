package com.weeklyhacks.holdem

import scala.collection.immutable.Queue
import scala.util.Random

case object CardSet {
  def default: CardSet = {
    val cards = for {r <- Rank.values; s <- Suit.values} yield Card(r, s)
    CardSet(Queue(Random.shuffle(cards.toSeq): _*))
  }
  def empty: CardSet = CardSet(Queue.empty)
}

case class CardSet(private val cards: Queue[Card]) {
  def put(card: Card): CardSet = {
    CardSet(cards.enqueue(card))
  }

  def pick: (Card, CardSet) = {
    val lastCard = cards.dequeue
    lastCard._1 -> CardSet(lastCard._2)
  }

  def discard: CardSet = {
    CardSet(cards.dequeue._2)
  }

  def open: CardSet = CardSet(cards = cards.map(c => c.copy(open = true)))

  override def toString: String = cards.mkString(" ")
}
