import scala.collection.mutable.ArrayBuffer
import scala.util.Random
import scala.util.Try

class Board(width: Int, height: Int, rules: Rules) {
  private type Creature = Boolean
  private type BoardType = ArrayBuffer[ArrayBuffer[Creature]]

  private def newBoard(width: Int, height: Int)(func: => Creature): BoardType =
    ArrayBuffer.fill[Creature](width, height){func}

  var board = newBoard(width, height){Random.nextBoolean()}

  private def willLive(x: Int, y: Int): Boolean = {
    val neighbours = livingNeighbours(x, y)
    if ((board(x)(y)==true && rules.survive(neighbours)) ||
        (board(x)(y)==false && rules.birth(neighbours))) {
      return true
    } else {
      return false
    }
  }

  private def livingNeighbours(x: Int, y: Int): Int = {
    var livingNeighbours = 0
    for {
      dx <- Range(-1, 1).inclusive
      dy <- Range(-1, 1).inclusive
      if dx != 0 || dy != 0
    } {
      try {
        val wrapped_x = (x + dx + width) % width
        val wrapped_y = (y + dy + height) % height
        if (board(wrapped_x)(wrapped_y)==true) {
          livingNeighbours += 1
        }
      } catch {
        case e: IndexOutOfBoundsException => ()
      }
    }
    livingNeighbours
  }

  private def computeGeneration: BoardType = {
    var nextBoard = newBoard(width, height){false}
    for {
      x <- Range(0, width)
      y <- Range(0, height)
    } {
      nextBoard(x)(y) = willLive(x, y)
    }
    nextBoard
  }

  def nextGeneration() =
    board = computeGeneration

  override def toString: String = {
    val rows = for(y <- Range(0, height)) yield {
      val row = for(x <- Range(0, width)) yield {
        if (board(x)(y))
          "\033[32mX\033[0m"
        else
          "\033[31mX\033[0m"
      }
      row.mkString
    }
    rows.mkString("\n")
  }
}

class Rules(livingConditions: String, deadConditions: String) {
  val survive = livingConditions.map(_.asDigit).toSet
  val birth = deadConditions.map(_.asDigit).toSet
}

object Main {
  def main(args: Array[String]) = {
    val gol = new Board(
      Try(args(0)).getOrElse("40").toInt,
      Try(args(1)).getOrElse("40").toInt,
      new Rules("23","3"))
    while (true) {
      print("\n")
      println(gol)
      Thread.sleep(100)
      gol.nextGeneration()
    }
  }
}
