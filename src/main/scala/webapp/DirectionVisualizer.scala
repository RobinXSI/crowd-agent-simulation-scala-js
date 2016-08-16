package webapp

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

class DirectionVisualizer(canvas: Canvas) {
  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  def draw(simulationMap: SimulationMap, hexagonalCalculatorCenter: (Coordinate) => Coordinate, hexagonalCalculatorDirection: (Coordinate) => Coordinate) = {
    for {
      cell <- simulationMap.grid.flatten
    } calculateCellDirections(simulationMap, cell, hexagonalCalculatorCenter, hexagonalCalculatorDirection)
  }

  private def calculateCellDirections(simulationMap: SimulationMap, cell: Cell, hexagonalCalculatorCenter: (Coordinate) => Coordinate, hexagonalCalculatorDirection: (Coordinate) => Coordinate) = cell.goto match {
    case Some(goto) => {
      val lineLenght = 50
      val coordinateDirectionInSquareSystem: Coordinate = goto.coordinate - cell.coordinate
      val coordinateDirectionInHexagonalSystem: Coordinate = hexagonalCalculatorDirection(coordinateDirectionInSquareSystem)
      val positionFromInHexagonalSystem: Coordinate = hexagonalCalculatorCenter(cell.coordinate)
      val resizedDirectionInHexagonalSystem = coordinateDirectionInHexagonalSystem.normalize() * lineLenght
      val positionToInHexagonalSystem: Coordinate = positionFromInHexagonalSystem + resizedDirectionInHexagonalSystem

      ctx.beginPath()
      ctx.lineWidth = 5
      ctx.strokeStyle = "blue"
      ctx.arc(positionFromInHexagonalSystem.x, positionFromInHexagonalSystem.y, 5, 0, math.Pi * 2)
      ctx.moveTo(positionFromInHexagonalSystem.x, positionFromInHexagonalSystem.y)
      ctx.lineTo(positionToInHexagonalSystem.x, positionToInHexagonalSystem.y)
      ctx.stroke()
      ctx.closePath()
    }
    case None => // do nothing
  }
}
