
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

/**
 * > test-only BasicMonadSpec
 */
class BasicMonadSpec extends FlatSpec 
                        with Matchers 
                        with BeforeAndAfter {

  class ListMonad[A] extends Monad[List] {
      
      override def unit[A](a: A): List[A] = List(a)
      
      override def flatMap[A,B](ma: List[A])(f: A => List[B]): List[B] = ma flatMap f
      
  }
  
  "Creating a Monad of type List Int the identy property " +
    "bind(return) " should " equal " in {
    val lsMonad: ListMonad[Int] = new ListMonad[Int]()
    // create the monad
    val ms1 = lsMonad.unit(List(1,2,3))
    val ms1Plus1 = lsMonad.unit(List(2,3,4))
    val identity = lsMonad.flatMap(ms1)(ls => lsMonad.unit(ls))
    ms1 should be (identity)
  }

}