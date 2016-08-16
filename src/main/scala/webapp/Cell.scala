package webapp


class Cell(val coordinate: Coordinate, val state: CellState) {
  var goto: Option[Cell] = None

  override def toString: String = coordinate.toString + state.toString


}
