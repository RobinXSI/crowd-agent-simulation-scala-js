package webapp

import org.scalajs.dom
import org.scalajs.dom.html._

import scala.collection.immutable.IndexedSeq

class AgentVisualizer(canvas: Canvas) {
  val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]

  def draw(simulationMap: SimulationMap, centerCalculator: (PVector) => PVector, agents: IndexedSeq[Agent]) = {
    for {
      agent <- agents
    } drawAgent(agent)
  }


  private def drawAgent(agent: Agent): Unit = {
    ctx.beginPath()
    ctx.fillStyle = "green"
    ctx.lineWidth = 1
    ctx.strokeStyle = "green"
    ctx.arc(agent.position.x, agent.position.y, 5, 0, 2 * math.Pi)
//    ctx.fill()
    ctx.stroke()
    ctx.closePath()
  }
}
