package webapp


case class Coordinate(x: Double, y: Double) {
  override def toString: String = x + ":" + y

  def -(that: Coordinate): Coordinate = Coordinate(this.x - that.x, this.y - that.y)
  def +(that: Coordinate): Coordinate = Coordinate(this.x + that.x, this.y + that.y)
  def /(n: Double): Coordinate = Coordinate(this.x / n, this.y / n)
  def *(n: Double): Coordinate = Coordinate(this.x * n, this.y * n)

  def mag(): Double = math.sqrt(magSq())
  def magSq(): Double = x * x + y * y
  def normalize(): Coordinate = {
    val mag = this.mag()
    if (mag != 0 && mag != 1) this / mag
    else this
  }
}
