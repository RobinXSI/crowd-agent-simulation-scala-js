package webapp

import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html

import scala.collection.immutable.IndexedSeq
import scala.util.Random

@JSExport
object MainApp {

  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    println(dom.window.innerHeight)
    println(dom.window.outerHeight)
    canvas.width = dom.window.outerWidth - 100
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
    val hexagonalCalculatorCenter: (PVector) => PVector = gridVisualizer.hexagonalCalculatorCenter()

    pathFindAlgorithm.findPath(simulationMap)




    val agents: IndexedSeq[Agent] = 1 to 1 map {
      _ => new Agent(PVector(120, 130), 5, 5)
    }
    val agentVisualizer: AgentVisualizer = new AgentVisualizer(canvas)

    def run() = {
      gridVisualizer.draw(simulationMap)
      directionVisualizer.draw(simulationMap, hexagonalCalculatorCenter)
      agentVisualizer.draw(simulationMap, hexagonalCalculatorCenter, agents)
      for {
        agent <- agents
      } test(simulationMap, gridVisualizer, agent)

    }

    dom.window.setInterval(() => run(), 50)
  }

  def test(simulationMap: SimulationMap, gridVisualizer: GridVisualizer, agent: Agent) = {
    agent.follow(simulationMap, gridVisualizer.mapHexagonalCoordinate(agent.position))
    agent.run()
  }
}
