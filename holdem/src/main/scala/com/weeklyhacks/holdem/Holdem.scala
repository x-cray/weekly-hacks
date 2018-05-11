package com.weeklyhacks.holdem

import scala.util.{Failure, Success, Try}

object Holdem {

  def main(args: Array[String]): Unit = {
    // Parse number of players, fallback to 4
    val numPlayers = args.length match {
      case x if x > 0 =>
        Try(args(0).toInt) match {
          case Success(res) if res > 1 && res < 25 => res
          case Success(_) | Failure(_) => fail(1, "Number of players should be a positive integer between 2 and 22")
            0
        }
      case _ => 4
    }

    // Populate the deck with default set of 52 cards
    var deck = CardStack.default
    // Table and players start with empty sets
    var table = CardStack.empty
    var players = (0 until numPlayers).map(_ => CardStack.empty).toList
    printState("Initial", deck, table, players)
    printState("Initial [open]", deck, table, players, openCards = true)

    // Deal 2 hole cards to all players
    for (n <- players.indices; _ <- 0 until 2) {
      val picked = {
        val res = deck.pick
        deck = res._2
        res._1
      }
      players = players.updated(n, players(n).put(picked))
    }
    printState("Hole cards dealt", deck, table, players)

    val dealTableCards = (numCards: Int) => {
      // First, discard the top card
      deck = deck.discard

      // Then, deal numCards to the table
      for (_ <- 0 until numCards) {
        val picked = {
          val res = deck.pick
          deck = res._2
          res._1
        }
        table = table.put(picked.copy(open = true))
      }
    }

    // Deal the flop
    dealTableCards(3)
    printState("Flop dealt", deck, table, players)

    // Deal the turn
    dealTableCards(1)
    printState("Turn dealt", deck, table, players)

    // Deal the flop
    dealTableCards(1)
    printState("River dealt", deck, table, players)
    printState("River dealt [open]", deck, table, players, openCards = true)
  }

  def printState(title: String, deck: CardStack, table: CardStack, players: List[CardStack], openCards: Boolean = false): Unit = {
    println(s"=== $title ===")
    println(s" Deck     : ${if (openCards) deck.open else deck}")
    println(s" Table    : ${if (openCards) table.open else table}")
    for (n <- players.indices) {
      println(s" Player ${n + 1} : ${if (openCards) players(n).open else players(n)}")
    }
    println()
  }

  def fail(code: Int, msg: String): Unit = {
    System.err.println(msg)
    System.exit(code)
  }

}
