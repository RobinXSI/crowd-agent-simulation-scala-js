package webapp

class Cell(val coordinate: Coordinate, val state: CellState) {
  override def toString: String = coordinate.toString + state.toString
}
