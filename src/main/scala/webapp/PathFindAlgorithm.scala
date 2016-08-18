package webapp

import scala.math.Ordering

case class CostPerCell(cost: Int, cell: Cell)

class PathFindAlgorithm {

  implicit def orderingByCost[A <: CostPerCell]: Ordering[A] = Ordering.by(e => -e.cost)

  val frontier = collection.mutable.PriorityQueue.empty[CostPerCell](
    orderingByCost
  )

  var visited = collection.immutable.Queue.empty[CostPerCell]

  def findPath(simulationMap: SimulationMap) = {
    val startCell: Cell = simulationMap.findGoal()
    frontier += CostPerCell(0, startCell)
    visited = CostPerCell(0, startCell) +: visited



    while (frontier.nonEmpty) {
      val current: CostPerCell = frontier.dequeue()

      val currentNeighbors = simulationMap.accessibleNeighbors(current.cell)
      currentNeighbors.foreach(next => {
        val newCost = current.cost + simulationMap.getCost(current, next)

        val costSoFar: Option[CostPerCell] = visited find { v => v.cell == next }
        costSoFar match {
          case None => {
            test(current, next, newCost)
          }
          case Some(costSoFar) => {
            if (newCost < costSoFar.cost) {
              test(current, next, newCost)

            }
          }
        }
      })
    }
    println("end")
  }

  def test(current: CostPerCell, next: Cell, newCost: Int): frontier.type = {
    visited = CostPerCell(newCost, next) +: visited
    next.goto = Some(current.cell)
    frontier += CostPerCell(newCost, next)
  }
}
