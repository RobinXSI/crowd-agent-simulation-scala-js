package webapp

class SimulationMap(val grid: Vector[Vector[Cell]]) {

  def height: Int = grid.length
  def width: Int = grid(0).length

  override def toString: String = grid.map (line => line mkString " ") mkString "\n"

  def neighbors(cell: Cell): Vector[Option[Cell]] = {
    Vector(topLeft(cell),
      topRight(cell),
      right(cell),
      bottomRight(cell),
      bottomLeft(cell),
      left(cell))
  }

  def accessibleNeighbors(cell: Cell): Vector[Cell] = {
    neighbors(cell).flatten.filter(c => c.state != Wall) // todo replace with case match
  }

  private def topLeft(cell: Cell): Option[Cell] = {
    if (cell.coordinate.y - 1 < 0 || cell.coordinate.x - 1 < 0) None
    else if (cell.coordinate.y % 2 == 0) get(Coordinate(cell.coordinate.x - 1, cell.coordinate.y - 1))
    else get(Coordinate(cell.coordinate.x, cell.coordinate.y - 1))
  }

  private def topRight(cell: Cell): Option[Cell] = {
    if (cell.coordinate.y - 1 < 0 || cell.coordinate.x + 1 >= width) None
    else if (cell.coordinate.y % 2 == 0) get(Coordinate(cell.coordinate.x, cell.coordinate.y - 1))
    else get(Coordinate(cell.coordinate.x + 1, cell.coordinate.y - 1))
  }

  private def right(cell: Cell): Option[Cell] = {
    if (cell.coordinate.x + 1 >= width) None
    else get(Coordinate(cell.coordinate.x + 1, cell.coordinate.y))
  }

  private def bottomRight(cell: Cell): Option[Cell] = {
    if (cell.coordinate.y + 1 >= height || cell.coordinate.x + 1 >= width) None
    else if (cell.coordinate.y % 2 == 0) get(Coordinate(cell.coordinate.x, cell.coordinate.y + 1))
    else get(Coordinate(cell.coordinate.x + 1, cell.coordinate.y + 1))
  }

  private def bottomLeft(cell: Cell): Option[Cell] = {
    if (cell.coordinate.y + 1 >= height || cell.coordinate.x - 1 < 0) None
    else if (cell.coordinate.y % 2 == 0) get(Coordinate(cell.coordinate.x - 1, cell.coordinate.y + 1))
    else get(Coordinate(cell.coordinate.x, cell.coordinate.y + 1))
  }

  private def left(cell: Cell): Option[Cell] = {
    if (cell.coordinate.x - 1 < 0) None
    else get(Coordinate(cell.coordinate.x - 1, cell.coordinate.y))
  }

  def get(coordinate: Coordinate): Option[Cell] = Some(grid(coordinate.y)(coordinate.x))


}
