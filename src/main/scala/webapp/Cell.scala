package webapp


class Cell(val position: PVector, var state: CellState) {
  var goto: Option[Cell] = None

  override def toString: String = position.toString + state.toString


}
