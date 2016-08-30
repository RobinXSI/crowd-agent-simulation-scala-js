package webapp

class Agent(var position: PVector, maxSpeed: Double, maxForce: Double) {
  var velocity: PVector = PVector(0, 0)
  var acceleration: PVector = PVector(0, 0)
  val r: Double = 3
  
  /*
  TODO: display() ausserhalb aufrufen
  TODO: border() verhalten bei austritt überlegen
   */

  def run(): Unit = {
    update()
  }

  def follow(simulationMap: SimulationMap, actualCellPosition: PVector): Unit = {
    val maybeCell: Cell = simulationMap.get(actualCellPosition).orNull
    // What is the vector at that spot in the flow field? Scale it up by maxspeed
    val desired: PVector = maybeCell.goto match {
      case Some(goto) => (goto.position - maybeCell.position) * maxSpeed
      case None => PVector(0, 0) * maxSpeed
    }
//    val desired: PVector = maybeCell.goto.orNull.position * maxSpeed // TODO: Test if goto is needed for desired
    // Steering is desired minus velocity
    val steer: PVector = (desired - velocity) limit maxForce
    applyForce(steer)
  }
  
  def applyForce(force: PVector): Unit = {
    // We could add mass here if we want A = F / M
    acceleration = acceleration + force
  }
  
  // Method to update position
  def update(): Unit = {
    // Update velocity
    velocity = (velocity + acceleration) limit maxSpeed
    position = position + velocity
    acceleration = acceleration * 0
  }
  
  def theta(): Double = velocity.heading() + math.toRadians(90)

}
