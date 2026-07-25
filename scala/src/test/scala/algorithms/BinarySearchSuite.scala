package algorithms

class BinarySearchSuite extends munit.FunSuite:
  private val values = Vector(1, 3, 5, 7, 9)

  test("finds values at the beginning, middle, and end"):
    assertEquals(BinarySearch.find(values, 1), Some(0))
    assertEquals(BinarySearch.find(values, 5), Some(2))
    assertEquals(BinarySearch.find(values, 9), Some(4))

  test("returns None when the value is absent"):
    assertEquals(BinarySearch.find(values, 4), None)
