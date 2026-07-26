package algorithms

object BinarySearch {
  def find[A: Ordering](values: IndexedSeq[A], target: A): Option[Int] = {
    val ordering = implicitly[Ordering[A]]
    var low = 0
    var high = values.length - 1

    while (low <= high) {
      val middle = low + (high - low) / 2
      ordering.compare(values(middle), target) match {
        case 0 => return Some(middle)
        case comparison if comparison < 0 => low = middle + 1
        case _ => high = middle - 1
      }
    }

    None
  }
}
