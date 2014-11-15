package catalgebra

import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import catalgebra.monoid.monoids._

/**
 * > test-only catalgebra.OptionMonoidSpec
 */
class OptionMonoidSpec extends FlatSpec
                        with Matchers
                        with BeforeAndAfter {

  val optionMonoid = new OptionMonoid[Int]()

  "Concatenating a Monoid with the identity " should
    " yield the same monoid value " in {
    val initialValue = Some(3)
    val res = optionMonoid.op(initialValue, optionMonoid.zero)
    res should equal (initialValue)
  }

  "The identity with the combination "  should
    " be associative " in {
    val initialValue = Some(3)
    val res1 = optionMonoid.op(optionMonoid.zero, initialValue)
    res1 should equal (initialValue)
    val res2 = optionMonoid.op(initialValue, optionMonoid.zero)
    res2 should equal (initialValue)
  }

  "If both arguments to the combine function are the zero element, then the value"  should
    " also be the zero element" in {
    val res1 = optionMonoid.op(optionMonoid.zero, optionMonoid.zero)
    res1 should equal (optionMonoid.zero)
  }

  "The associative law "  should
    " hold for a monoid " in {
    //(op(Some(x),Some(y)), Some(z)) == (Some(x),op(Some(y)), Some(z))
    val initialValueX = Some(3)
    val initialValueY = Some(5)
    val initialValueZ = Some(10)
    val leftSide = optionMonoid.op(optionMonoid.op(initialValueX, initialValueY), initialValueZ)
    val rightSide  = optionMonoid.op(initialValueX, optionMonoid.op(initialValueY, initialValueZ))
    leftSide should equal (rightSide)
  }

}