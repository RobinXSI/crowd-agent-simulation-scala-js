package webapp

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html

@JSExport
object MainApp {

  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val simulationMap: SimulationMap = MapBuilder.createMap("..x...\n..x..e\n......\n..x...")
    val gridVisualizer: GridVisualizer = new GridVisualizer(canvas)
    val directionVisualizer: DirectionVisualizer = new DirectionVisualizer(canvas)
    val pathFindAlgorithm: PathFindAlgorithm = new PathFindAlgorithm()
    
    def run() = {
      pathFindAlgorithm.findPath(simulationMap)
      gridVisualizer.draw(simulationMap)
      val hexagonalCalculatorCenter: (Coordinate) => Coordinate = gridVisualizer.hexagonalCalculatorCenter()
      directionVisualizer.draw(simulationMap, hexagonalCalculatorCenter)
    }

    dom.window.setInterval(() => run(), 50)
  }
}
