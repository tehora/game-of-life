import scala.collection.mutable.ArrayBuffer
import scala.util.Random

class Board(width: Int, height: Int) {
  type Creature = Boolean
  private type BoardType = ArrayBuffer[ArrayBuffer[Creature]]

  def newBoard(width: Int, height: Int): BoardType =
    ArrayBuffer.fill[Creature](width, height){Random.nextBoolean()}

  var board = newBoard(width, height)

  def willLive(x: Int, y: Int): Boolean = {
    var livingNeighbours = 0
    for {
      dx <- Range(-1,1)
      dy <- Range(-1,1)
      if dx != 0 || dy != 0
    } {
        if (board(x + dx)(y+dy)==true) {
          livingNeighbours+=1
        }
    }
    if ((board(x)(y)==true && (livingNeighbours==2 || livingNeighbours==3)) ||
        (board(x)(y)==false && livingNeighbours==3)) {
      return true
    } else {
      return false
    }
  }

  def nextGeneration(board: BoardType): BoardType = {
    var nextBoard = newBoard(width, height)
    for {
      x <- Range(0, width)
      y <- Range(0, height)
    } {
      nextBoard(x)(y) = willLive(x, y)
    }
    nextBoard
  }
}

object Main {
  def main(args: Array[String]) = {

  }
}
