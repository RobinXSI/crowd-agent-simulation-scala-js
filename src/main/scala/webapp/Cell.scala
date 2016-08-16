package webapp


class Cell(val coordinate: Coordinate, val state: CellState) {
  var goto: Cell = _

  override def toString: String = coordinate.toString + state.toString


}
