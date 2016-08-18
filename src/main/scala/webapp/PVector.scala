package webapp


case class PVector(x: Double, y: Double) {
  


  override def toString: String = x + ":" + y

  def -(that: PVector): PVector = PVector(this.x - that.x, this.y - that.y)
  def +(that: PVector): PVector = PVector(this.x + that.x, this.y + that.y)
  def /(n: Double): PVector = PVector(this.x / n, this.y / n)
  def *(n: Double): PVector = PVector(this.x * n, this.y * n)

  def mag(): Double = math.sqrt(magSq())
  def magSq(): Double = x * x + y * y
  def normalize(): PVector = {
    val mag = this.mag()
    if (mag != 0 && mag != 1) this / mag
    else this
  }
  def limit(max: Double): PVector = {
    if (this.magSq() > max * max) this.normalize() * max
    else this
  }
  def heading(): Double = math.atan2(y, x)
}
