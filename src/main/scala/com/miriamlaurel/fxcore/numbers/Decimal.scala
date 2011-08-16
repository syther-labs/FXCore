package com.miriamlaurel.fxcore.numbers

import java.math.MathContext

/**
 * @author Alexander Temerev
 */
class Decimal(bd: java.math.BigDecimal) extends BigDecimal(bd, MathContext.DECIMAL64) {

  def this(bd: BigDecimal) = this(bd.bigDecimal.stripTrailingZeros)
  def this(s: String) = this(BigDecimal(s, MathContext.DECIMAL64))
  def this(i: Int) = this(BigDecimal(i))
  def this(l: Long) = this(BigDecimal(l))

  def toPlainString = if (this == 0) "0" else bigDecimal.toPlainString

  def +(that: Decimal): Decimal = Decimal(super.+(that))
  def -(that: Decimal): Decimal = Decimal(super.-(that))
  def *(that: Decimal): Decimal = Decimal(super.*(that))
  def /(that: Decimal): Decimal = Decimal(super./(that))

  def %(that: Decimal): Decimal = Decimal(super.%(that))

  def min(that: Decimal): Decimal = Decimal(super.min(that))
  def max(that: Decimal): Decimal = Decimal(super.max(that))
  override def abs: Decimal = Decimal(super.abs)

  override def setScale(scale: Int): Decimal = Decimal(super.setScale(scale, BigDecimal.RoundingMode.HALF_EVEN))

  def reciprocal: Decimal = Decimal(1) / this

  override def toString = bd.toPlainString
}

object Decimal {

  def apply(bd: BigDecimal) = new Decimal(bd)
  def apply(s: String) = new Decimal(s)
  def apply(i: Int) = new Decimal(i)
  def apply(l: Long) = new Decimal(l)

  implicit def enrichBigDecimal(bd: BigDecimal): Decimal = Decimal(bd)
  implicit def wrapInt(i: Int): Decimal = Decimal(i)
  implicit def wrapLong(l: Long): Decimal = Decimal(l)
  implicit def wrapOptInt(oi: Option[Int]): Option[Decimal] = if (oi.isDefined) Some(Decimal(oi.get)) else None
  implicit def wrapOptLong(ol: Option[Long]): Option[Decimal] = if (ol.isDefined) Some(Decimal(ol.get)) else None
  implicit def wrapString(s: String) = Decimal(s)

  // Implicit unwrapping like Decimal -> Int is dangerous due to precision loss.
}