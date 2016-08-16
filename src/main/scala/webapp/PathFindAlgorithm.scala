package webapp

class PathFindAlgorithm {
  def findPath(simulationMap: SimulationMap) = {
    val allCells = simulationMap.grid.flatten

    val goal: Cell = allCells.find(c => c.state == End).orNull




    linkNeighbors(simulationMap, goal)
//    println(simulationMap.accessibleNeighbors(goal).map(c => c.goto))
  }


  def linkNeighbors(simulationMap: SimulationMap, actualCell: Cell): Unit = {
    val notSearchedNeighbors: Vector[Cell] = simulationMap.accessibleNeighbors(actualCell).filter(neighbor => neighbor.goto.isEmpty)
    if (notSearchedNeighbors.size != 0) println(notSearchedNeighbors.size)
    notSearchedNeighbors.foreach(neighbor => neighbor.goto = Some(actualCell))
    notSearchedNeighbors.foreach(neighbor => linkNeighbors(simulationMap, neighbor))
  }

}
