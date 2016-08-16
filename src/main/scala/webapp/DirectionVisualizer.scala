package webapp

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

class DirectionVisualizer(canvas: Canvas) {



  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]



  def draw(simulationMap: SimulationMap, calculator: (Coordinate) => Coordinate) = {
    for {
      cell <- simulationMap.grid.flatten
    } calculateCellDirections(simulationMap, cell, calculator)
  }

  private def calculateCellDirections(simulationMap: SimulationMap, cell: Cell, calculator: (Coordinate) => Coordinate) = {
    val cellSize = math.min(canvas.width / (simulationMap.width + 0.5) , canvas.height / (simulationMap.height + 0.5))
    val cell1 = cell
    val cell2 = cell.goto

    val directionX = cell1.coordinate.x + (cell1.coordinate.x - cell2.coordinate.x)
    val directionY = cell1.coordinate.y + (cell1.coordinate.y - cell2.coordinate.y)

    val coordinateTo: Coordinate = Coordinate(directionX, directionY)
    val calculatorTo: Coordinate = calculator(coordinateTo)
    val calculatorFrom: Coordinate = calculator(cell1.coordinate)

    ctx.beginPath()
    ctx.lineWidth = 5
    ctx.strokeStyle = "green"
    ctx.moveTo(calculatorFrom.x, calculatorFrom.x)
    ctx.lineTo(calculatorTo.x, calculatorTo.y)
    ctx.stroke()


  }

}
