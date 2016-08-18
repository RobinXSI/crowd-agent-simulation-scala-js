package webapp

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

class GridVisualizer(canvas: Canvas) {
  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
  var cellSize: Double = _

  def draw(simulationMap: SimulationMap) = {
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    for {
      cell <- simulationMap.grid.flatten
    } calculateCellParameters(simulationMap, cell)
  }

  private def calculateCellParameters(simulationMap: SimulationMap, cell: Cell) = {
    cellSize = math.min(canvas.width / (simulationMap.width + 0.5), canvas.height / (simulationMap.height + 0.5))
    createHexagon(cell.coordinate, cell.state)
    val centerOfHexagon: Coordinate = hexagonalCalculatorCenter()(cell.coordinate)

//    if (cell.state == Wall) ctx.fillStyle = "white"
//    else ctx.fillStyle = "blue"
//    ctx.lineWidth = 2
//    ctx.font = "4 px Arial"
//    ctx.textAlign = "center"
//    ctx.fillText(cell.toString,centerOfHexagon.x,centerOfHexagon.y - 10)
  }

  private def widthToRadius(width: Double): Double = width / math.sqrt(3)

  private def hexCorner(center: Coordinate, i: Integer) = {
    val angleDegree = 60 * i + 30
    val angleRadiant = Math.PI / 180 * angleDegree
    val radius = widthToRadius(cellSize)

    val x = (center.x * cellSize) + radius * math.cos(angleRadiant) + cellSize / 2
    val y = (center.y * radius * 3 / 4 * 2) + radius * math.sin(angleRadiant) + radius

    if (center.y % 2 == 0) Coordinate(x, y)
    else Coordinate(x + cellSize / 2, y)
  }

  private def createHexagon(center: Coordinate, state: CellState) = {
    val numberOfEdges = 6

    ctx.strokeStyle = "grey"

    state match {
      case Wall => ctx.fillStyle = "black"
      case Active => ctx.fillStyle = "blue"
      case Empty => ctx.fillStyle = "white"
      case End => ctx.fillStyle = "red"
    }

    ctx.beginPath()
    for (i <- 1 to numberOfEdges) {
      val corner = hexCorner(center, i)
      if (i == 1) ctx.moveTo(corner.x, corner.y)
      else ctx.lineTo(corner.x, corner.y)
    }
    ctx.closePath()
    ctx.fill()
    ctx.stroke()
  }

  def hexagonalCalculatorCenter(): (Coordinate) => Coordinate  = {
    (coordinate: Coordinate) => {
      val x = coordinate.x * cellSize + 0.5 * cellSize
      val y = coordinate.y * widthToRadius(cellSize) * 3 / 4 * 2 + widthToRadius(cellSize)

      if (coordinate.y % 2 == 0) Coordinate(x, y)
      else Coordinate(x + cellSize / 2, y)
    }
  }
}
