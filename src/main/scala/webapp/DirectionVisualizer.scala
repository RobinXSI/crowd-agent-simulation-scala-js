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

  private def calculateCellDirections(simulationMap: SimulationMap, cell: Cell, calculator: (Coordinate) => Coordinate, calculator2: (Coordinate) => Coordinate) = {
    val cellSize = math.min(canvas.width / (simulationMap.width + 0.5) , canvas.height / (simulationMap.height + 0.5))
    val cell1 = cell
    val cell2 = cell.goto

    val directionX = cell2.coordinate.x - cell1.coordinate.x
    val directionY = cell2.coordinate.y - cell1.coordinate.y

    val test: Coordinate = calculator2(Coordinate(directionX, directionY))


    val coordinateTo: Coordinate = Coordinate(cell1.coordinate.x + directionX, cell1.coordinate.y + directionY)
    val calculatorTo: Coordinate = calculator(coordinateTo)
    val calculatorFrom: Coordinate = calculator(cell1.coordinate)


    ctx.beginPath()
    ctx.lineWidth = 5
    ctx.strokeStyle = "blue"
    ctx.moveTo(calculatorFrom.x, calculatorFrom.y)
    ctx.lineTo(calculatorFrom.x + test.x, calculatorFrom.y + test.y)
    ctx.stroke()
    ctx.closePath()


//    ctx.strokeStyle = "red"
//    ctx.beginPath()
//    ctx.arc(calculatorTo.x, calculatorTo.y, 5, 0, math.Pi * 2)
//    ctx.stroke()
//    ctx.closePath()



  }

}
