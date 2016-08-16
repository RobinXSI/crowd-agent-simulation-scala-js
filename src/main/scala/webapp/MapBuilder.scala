package webapp

object MapBuilder {

  def typeFromChar(char: Char): CellState = {
    char match {
      case 'x' => Wall
      case 'e' => End
      case '.' => Empty
    }
  }

  def createLineOfCellGrid(line: String, indexY: Int): Vector[Cell] = (for {
    (char, indexX) <- line.toList.zipWithIndex
  } yield new Cell(Coordinate(indexX, indexY), typeFromChar(char))).toVector

  def createCellGrid(file: String): Vector[Vector[Cell]] = (for {
      (line, indexY) <- file.split("\n").zipWithIndex
    } yield createLineOfCellGrid(line, indexY)).toVector

  def createMap(filename: String): SimulationMap = {
    new SimulationMap(createCellGrid(filename))
  }
}
