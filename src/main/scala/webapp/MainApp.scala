package webapp

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html

@JSExport
object MainApp {

  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    println(dom.window.innerHeight)
    println(dom.window.outerHeight)
    canvas.width = dom.window.outerWidth
    canvas.height = dom.window.outerHeight - 200

    val simulationMap: SimulationMap = MapBuilder.createMap(
      "......x..x......x....x..........\n" +
      "......x..x..x...x....x..........\n" +
      "......x.....x...x....x..........\n" +
      "......x.....x........x..........\n" +
      "......x..x..x...x....x..........\n" +
      "......x..x..x...x....x..........\n" +
      "......x..x......x....x..........\n" +
      "......x..xxxxxxxx....x..........\n" +
      "......x..x...........x..........\n" +
      "......x..x...........x..........\n" +
      "......x..x...xxxxxxxxx..........\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x.................e\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      ".........x......................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................\n" +
      "......x..x...x..................")
    val gridVisualizer: GridVisualizer = new GridVisualizer(canvas)
    val directionVisualizer: DirectionVisualizer = new DirectionVisualizer(canvas)
    val pathFindAlgorithm: PathFindAlgorithm = new PathFindAlgorithm()
    
    def run() = {
      pathFindAlgorithm.findPath(simulationMap)
      gridVisualizer.draw(simulationMap)
      val hexagonalCalculatorCenter: (PVector) => PVector = gridVisualizer.hexagonalCalculatorCenter()
      directionVisualizer.draw(simulationMap, hexagonalCalculatorCenter)
    }

    dom.window.setInterval(() => run(), 50)
  }
}
