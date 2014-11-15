
/**
 * Category with one type 
 * - binary operation
 * - identy function
 * note: all object in a Category have the identity function.
 * 
 * What is a Monoid? 
 *  A Monoid is a TYPE together with the monoid operations and a set of laws.
 *  A monoid is an algebra.
 * 
 * laws: associatity and identity. (i.e. ||, &&, +, *)
 *
 * =) help out parellel programming, as they allow problems to be broken in chunks.
 * =) they an be composed to assemble complex calculaitions from simplier parts. -FP book
 */
trait Monoid[A] {
   
   /**
    * a binary operation that works on two object from the type (domain)
    * and combines them to returs an object of the same type.
    * op(op(x,y),z) == op(x, op(y,z))
    */
   def op(a1: A, a2: A): A
   
   /**
    * identity for the op
    * op(a, zero) == a for any a
    */
   def zero: A
   
}
object Monoid {

  implicit object IntAdditionMonoid extends Monoid[Int] {
    override def op(x1: Int, x2: Int): Int = x1 + x2
    override def zero: Int = 0
  }

  implicit object StringConcatMonoid extends Monoid[String] {
    override def op(s1: String, s2: String): String = s1 + s2
    override def zero: String = ""
  }

}