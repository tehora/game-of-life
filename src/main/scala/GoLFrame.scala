import java.awt.Color
import java.awt.GridLayout
import javax.swing.BorderFactory
import javax.swing.JFrame
import javax.swing.JLabel
import scala.collection.mutable.ArrayBuffer

class GoLFrame extends JFrame {
  private val (boardSize_x, boardSize_y) = (80, 80)
  private val gol = new Board(boardSize_x, boardSize_y, new Rules("23", "3"))

  class Tile extends JLabel {
    setBorder(BorderFactory.createLineBorder(Color.CYAN))
    setOpaque(true)
    GoLFrame.this.add(this)
  }

  private val tiles = ArrayBuffer.fill[Tile](boardSize_x, boardSize_y){new Tile}
  setTitle("tehora's Game of Life")
  setSize(1000, 1000)
  setLayout(new GridLayout(boardSize_x, boardSize_y))
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  def refresh() = {
    for {
      x <- Range(0, boardSize_x)
      y <- Range(0, boardSize_y)
    } {
      if (gol.board(x)(y))
        tiles(x)(y).setBackground(Color.BLACK)
      else
        tiles(x)(y).setBackground(Color.WHITE)
    }
  }

  show(true)

  while (true) {
    refresh()
    Thread.sleep(100)
    gol.nextGeneration()
  }
}
