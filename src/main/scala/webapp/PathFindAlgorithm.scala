package webapp

class PathFindAlgorithm {
  def findPath(simulationMap: SimulationMap) = {
    val allCells = simulationMap.grid.flatten


    //    allCells.foreach(cell => {
    //      cell.goto = simulationMap.accessibleNeighbors(cell).head
    //    })

    val goal: Cell = allCells.find(c => c.state == Wall).orNull

    linkNeighbors(simulationMap, goal)
  }


  def linkNeighbors(simulationMap: SimulationMap, actualCell: Cell): Unit = {
    simulationMap.accessibleNeighbors(actualCell).foreach(neighbor => neighbor.goto match {
      case None => {
        neighbor.goto = Some(actualCell)
        linkNeighbors(simulationMap, neighbor)
      }
      case Some(_) => println("")
    })
  }

}
