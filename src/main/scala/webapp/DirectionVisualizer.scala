package webapp

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

class DirectionVisualizer(canvas: Canvas) {


  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]



  def draw(simulationMap: SimulationMap, calculator: (Coordinate) => Coordinate, calculator2: (Coordinate) => Coordinate) = {
    for {
      cell <- simulationMap.grid.flatten
    } calculateCellDirections(simulationMap, cell, calculator, calculator2)
  }

  private def calculateCellDirections(simulationMap: SimulationMap, cell: Cell, hexagonalCalculatorPosition: (Coordinate) => Coordinate, hexagonalCalculatorDirection: (Coordinate) => Coordinate) = {
    val lineLenght = 50
    val coordinateDirectionInSquareSystem: Coordinate = cell.goto.coordinate - cell.coordinate
    val coordinateDirectionInHexagonalSystem: Coordinate = hexagonalCalculatorDirection(coordinateDirectionInSquareSystem)
    val positionFromInHexagonalSystem: Coordinate = hexagonalCalculatorPosition(cell.coordinate)
    val resizedDirectionInHexagonalSystem = coordinateDirectionInHexagonalSystem.normalize() * lineLenght
    val positionToInHexagonalSystem: Coordinate = positionFromInHexagonalSystem + resizedDirectionInHexagonalSystem


    ctx.beginPath()
    ctx.lineWidth = 5
    ctx.strokeStyle = "blue"
    ctx.moveTo(positionFromInHexagonalSystem.x, positionFromInHexagonalSystem.y)
    ctx.lineTo(positionToInHexagonalSystem.x, positionToInHexagonalSystem.y)
    ctx.stroke()
    ctx.closePath()


//    ctx.arc(calculatorTo.x, calculatorTo.y, 5, 0, math.Pi * 2)
//    ctx.stroke()
//    ctx.closePath()



  }

}
