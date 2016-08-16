package webapp

class PathFindAlgorithm {
  def findPath(simulationMap: SimulationMap) = {
    simulationMap.grid.flatten.foreach(cell => {
      cell.goto = simulationMap.accessibleNeighbors(cell).head
    })
  }


}
