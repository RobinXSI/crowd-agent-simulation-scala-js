package webapp

class SimulationMap(val grid: Vector[Vector[Cell]]) {
  def getCost(from: CostPerCell, to: Cell) = 1

  def findGoal(): Cell = {
    val allCells = grid.flatten
    allCells.find(c => c.state == End).orNull
  }


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
    neighbors(cell).flatten.filter(c => c.state != Wall) // TODO replace with case match
  }

  private def topLeft(cell: Cell): Option[Cell] = {
    if (cell.position.y - 1 < 0 || cell.position.x - 1 < 0) None
    else if (cell.position.y % 2 == 0) get(PVector(cell.position.x - 1, cell.position.y - 1))
    else get(PVector(cell.position.x, cell.position.y - 1))
  }

  private def topRight(cell: Cell): Option[Cell] = {
    if (cell.position.y - 1 < 0 || cell.position.x + 1 >= width) None
    else if (cell.position.y % 2 == 0) get(PVector(cell.position.x, cell.position.y - 1))
    else get(PVector(cell.position.x + 1, cell.position.y - 1))
  }

  private def right(cell: Cell): Option[Cell] = {
    if (cell.position.x + 1 >= width) None
    else get(PVector(cell.position.x + 1, cell.position.y))
  }

  private def bottomRight(cell: Cell): Option[Cell] = {
    if (cell.position.y + 1 >= height || cell.position.x + 1 >= width) None
    else if (cell.position.y % 2 == 0) get(PVector(cell.position.x, cell.position.y + 1))
    else get(PVector(cell.position.x + 1, cell.position.y + 1))
  }

  private def bottomLeft(cell: Cell): Option[Cell] = {
    if (cell.position.y + 1 >= height || cell.position.x - 1 < 0) None
    else if (cell.position.y % 2 == 0) get(PVector(cell.position.x - 1, cell.position.y + 1))
    else get(PVector(cell.position.x, cell.position.y + 1))
  }

  private def left(cell: Cell): Option[Cell] = {
    if (cell.position.x - 1 < 0) None
    else get(PVector(cell.position.x - 1, cell.position.y))
  }

  def get(coordinate: PVector): Option[Cell] = Some(grid(coordinate.y.toInt)(coordinate.x.toInt))

  def mapHexagonalCoordinate(hexagonalCoordinate: PVector): PVector = {
    /*int column = int(constrain(lookup.x/resolution,0,cols-1));
    int row = int(constrain(lookup.y/resolution,0,rows-1));
    return field[column][row].get();*/
    println(hexagonalCoordinate)
    PVector(0, 0)
  }


}
