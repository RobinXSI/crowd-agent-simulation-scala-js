package webapp

import org.scalajs.dom
import org.scalajs.dom.html.Canvas

class DirectionVisualizer(canvas: Canvas) {
  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  def draw(simulationMap: SimulationMap, centerCalculator: (Coordinate) => Coordinate) = {
    for {
      cell <- simulationMap.grid.flatten
    } calculateCellDirections(simulationMap, cell, centerCalculator)
  }

  private def calculateCellDirections(simulationMap: SimulationMap, cell: Cell, centerCalculator: (Coordinate) => Coordinate) =
    cell.goto match {
    case Some(goto) => {
      val lineLenght = 25

      val centerFrom = centerCalculator(cell.coordinate)
      val centerTo = centerCalculator(goto.coordinate)
      val difference = (centerFrom - centerTo).normalize() * lineLenght
      val pointer = centerFrom + difference * -1

      ctx.beginPath()
      ctx.lineWidth = 2
      ctx.strokeStyle = "blue"
      ctx.arc(centerFrom.x, centerFrom.y, 5, 0, math.Pi * 2)
      ctx.moveTo(centerFrom.x, centerFrom.y)
      ctx.lineTo(pointer.x, pointer.y)
      ctx.stroke()
      ctx.closePath()
    }
    case None => // do nothing
  }
}
