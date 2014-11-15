import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

/**
 * > test-only OptionMonoidNoDroppingSpec
 */
class OptionMonoidNoDroppingSpec extends FlatSpec
                                  with Matchers
                                  with BeforeAndAfter {

  val optionMonoidInt = new OptionMonoid2[Int]()
  val optionMonoidString = new OptionMonoid2[String]()

  "Concatenating the identity with a monoid value " should
    " yield the same monoid value " in {
    val initialValue = Some(3)
    val res = optionMonoidInt.op(optionMonoidInt.zero, initialValue)
    res should equal (initialValue)
  }

  "Concatenating a monoid value with the identity " should
    " yield the same monoid value " in {
    val initialValue = Some(3)
    val res = optionMonoidInt.op(initialValue, optionMonoidInt.zero)
    res should equal (initialValue)
  }

  "The identity with the combination "  should
    " be associative " in {
    val initialValue = Some(3)
    val res1 = optionMonoidInt.op(optionMonoidInt.zero, initialValue)
    res1 should equal (initialValue)
    val res2 = optionMonoidInt.op(initialValue, optionMonoidInt.zero)
    res2 should equal (initialValue)
  }

  "If both arguments to the combine function are the zero element, then the value"  should
    " also be the zero element" in {
    val res1 = optionMonoidInt.op(optionMonoidInt.zero, optionMonoidInt.zero)
    res1 should equal (optionMonoidInt.zero)
  }

  "The associative law "  should
    " hold for a monoid " in {
    //(op(Some(x),Some(y)), Some(z)) == (Some(x),op(Some(y)), Some(z))
    val initialValueX = Some(3)
    val initialValueY = Some(5)
    val initialValueZ = Some(10)
    val leftSide = optionMonoidInt.op(optionMonoidInt.op(initialValueX, initialValueY), initialValueZ)
    val rightSide  = optionMonoidInt.op(initialValueX, optionMonoidInt.op(initialValueY, initialValueZ))
    println(" ----> " + leftSide)
    println(" ----> " + rightSide)
    leftSide should equal (rightSide)
  }

  "The associative law "  should
    " hold for a String monoid " in {
    //(op(Some(x),Some(y)), Some(z)) == (Some(x),op(Some(y)), Some(z))
    val initialValueX = Some(" hi ")
    val initialValueY = Some(" there ")
    val initialValueZ = Some(" universe ")
    val leftSide = optionMonoidString.op(optionMonoidString.op(initialValueX, initialValueY), initialValueZ)
    val rightSide  = optionMonoidString.op(initialValueX, optionMonoidString.op(initialValueY, initialValueZ))
    println(" ----> " + leftSide)
    println(" ----> " + rightSide)
    leftSide should equal (rightSide)
  }

  }