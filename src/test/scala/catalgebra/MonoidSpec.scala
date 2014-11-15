package catalgebra

import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import catalgebra.monoid.monoids._

/**
 * > test-only MonoidSpec
 */
class MonoidSpec extends FlatSpec 
                        with Matchers 
                        with BeforeAndAfter {

  val initialMonoid = new IntAdditionMonoid()

  "Concatenating a Monoid with the identity " should
  " yield the same monoid value " in {
    val initialValue = 3
    val res = initialMonoid.op(initialValue, initialMonoid.zero)
    res should equal (initialValue)
  }

  "The identity with the combination "  should
    " be associative " in {
    val initialValue = 3
    val res1 = initialMonoid.op(initialMonoid.zero, initialValue)
    res1 should equal (initialValue)
    val res2 = initialMonoid.op(initialValue, initialMonoid.zero)
    res2 should equal (initialValue)
  }

  "The associative law "  should
    " hold for a monoid " in {
    val initialValue1 = 3
    val initialValue2 = 5
    val res1 = initialMonoid.op(initialValue1, initialValue2)
    val res2 = initialMonoid.op(initialValue2, initialValue1)
    res2 should equal (res1)
  }

}