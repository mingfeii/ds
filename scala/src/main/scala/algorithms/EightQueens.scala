package algorithms

import scala.collection.mutable.ArrayBuffer

object EightQueens {
  // 每个解用 Vector 表示：下标是行，值是皇后所在的列，均从 0 开始。
  // 例如 solution(2) = 5 表示第 3 行的皇后在第 6 列。
  def solve(): Vector[Vector[Int]] = {
    val solutions = ArrayBuffer.empty[Vector[Int]]
    val columns = Array.fill(8)(-1) // -1 表示这一行还没有放皇后。

    // 只检查前面的行：每行只放一个皇后，因此不会同行。
    def canPlace(row: Int, col: Int): Boolean = {
      var previousRow = 0
      while (previousRow < row) {
        val previousCol = columns(previousRow)
        if (previousCol == col) {
          return false // 同列。
        }
        if (math.abs(row - previousRow) == math.abs(col - previousCol)) {
          return false // 行距离等于列距离，说明在同一条对角线上。
        }
        previousRow += 1
      }
      true
    }

    // dfs(row)：前 row 行已经放好，现在尝试放第 row 行。
    def dfs(row: Int): Unit = {
      if (row == 8) {
        solutions += columns.toVector // 保存不可变快照，后续回溯不会改变它。
        return
      }

      for (col <- 0 until 8) {
        if (canPlace(row, col)) { // 剪枝：只深入搜索不冲突的位置。
          columns(row) = col   // 1. 做选择：在当前位置放皇后。
          dfs(row + 1)         // 2. 深入：尝试下一行。
          columns(row) = -1    // 3. 撤销选择：回到本行，尝试下一列。
        }
      }
      // 所有列都试完了，返回上一层，让上一行的皇后换位置。
    }

    dfs(0)
    solutions.toVector
  }
}

object EightQueensDemo {
  def main(args: Array[String]): Unit = {
    val solutions = EightQueens.solve()
    println(s"Total solutions: ${solutions.size}")
    solutions.headOption.foreach { first =>
      println("First solution (Q = queen, . = empty):")
      for (row <- 0 until 8) {
        println((0 until 8).map(col => if (first(row) == col) "Q" else ".").mkString(" "))
      }
    }
  }
}
