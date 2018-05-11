package com.weeklyhacks.holdem

import scala.collection.immutable.Queue
import scala.util.Random

case object CardStack {
  def default: CardStack = {
    val cards = for {r <- Rank.values; s <- Suit.values} yield Card(r, s)
    CardStack(Queue(Random.shuffle(cards.toSeq): _*))
  }
  def empty: CardStack = CardStack(Queue.empty)
}

case class CardStack(private val cards: Queue[Card]) {
  def put(card: Card): CardStack = {
    CardStack(cards.enqueue(card))
  }

  def pick: (Card, CardStack) = {
    val lastCard = cards.dequeue
    lastCard._1 -> CardStack(lastCard._2)
  }

  def discard: CardStack = {
    CardStack(cards.dequeue._2)
  }

  def open: CardStack = CardStack(cards = cards.map(c => c.copy(open = true)))

  override def toString: String = cards.mkString(" ")
}
