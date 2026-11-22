package algorithms

class EightQueensSuite extends munit.FunSuite {
  test("finds all 92 distinct solutions with no attacking queens") {
    val solutions = EightQueens.solve()
    assertEquals(solutions.size, 92)
    assertEquals(solutions.distinct.size, 92)

    for (columns <- solutions) {
      assertEquals(columns.size, 8)
      assert(columns.forall(col => col >= 0 && col < 8))
      for (row <- 0 until 8; previousRow <- 0 until row) {
        assert(columns(row) != columns(previousRow))
        assert(math.abs(row - previousRow) != math.abs(columns(row) - columns(previousRow)))
      }
    }
  }
}
