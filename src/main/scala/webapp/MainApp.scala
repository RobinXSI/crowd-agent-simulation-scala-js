package webapp

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html

@JSExport
object MainApp {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val simulationMap: SimulationMap = MapBuilder.createMap("..x...\n..x...\n..x...\n..x...")
    val gridVisualizer: GridVisualizer = new GridVisualizer(canvas)
    
    def draw() = {
      gridVisualizer.draw(simulationMap)
    }

    dom.window.setInterval(() => draw(), 50)
  }
}
