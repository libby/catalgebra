
/**
 * Example of Monoids:
 * Monoids are a Type Monad + an implementation of Monad
 * "type A forms a monoid under the perations defined by the Monoid[A] instance." FP
 * "a monoid is a type together with a binary operation (op) over that type, satisfying associatevity and having an identy element (zero)""
 */
 class StringMonoid extends Monoid[String] {
   
   override def op(s1: String, s2: String): String = s1 + s2
   
   override def zero = ""  
     
 }

// doesn't seem to work.
object MonoidImplicits {
//  implicit def string2Monoid(s: String): StringMonoid = new StringMonoid()
//  implicit def int2Monoid(s: Int): IntAdditionMonoid = new IntAdditionMonoid()

  implicit object string2Monoid extends StringMonoid

  implicit object IntAdditionMonoid extends Monoid[Int] {
      override def op(x1: Int, x2: Int): Int = x1 + x2
      override def zero: Int = 0
  }

}

 class IntAdditionMonoid extends Monoid[Int] {

     override def op(x1: Int, x2: Int): Int = x1 + x2
     
     override def zero: Int = 0
     
 }
 
 class MultiplicationIntMonoid extends Monoid[Int] {
     
     override def op(x1: Int, x2: Int): Int = x1 * x2
     
     override def zero: Int = 1
 
 }
 
 class ListConcatMonoid[T] extends Monoid[List[T]] {
       
       override def op(xs: List[T], ys: List[T]): List[T] = xs ::: ys
  
       override def zero:  List[T] = Nil
       
 }
 
 class BooleanOr extends Monoid[Boolean] {
     
     override def op(b1: Boolean, b2: Boolean): Boolean = b1 || b2
     
     /**
      * f || f == f
      * t || f == t
      */
     override def zero: Boolean = false     
     
 }
 
 class BooleanAnd extends Monoid[Boolean] {

      override def op(b1: Boolean, b2: Boolean): Boolean = b1 && b2

      /**
       * f && t == f
       * t && t == t
       */
      override def zero: Boolean = true     

  }
 
  class OptionMonoid[T] extends Monoid[Option[T]] {
    // map privative
    //implicit

    /**
     * doesn't have to be commutative just associative.
     *
     * (op(Some(x),Some(y)), Some(z)) == (Some(x),op(Some(y)), Some(z))
     * None    + Some(x) == Some(x)
     * Some(x) + None    == Some(x)
     */
     override def op(opt1: Option[T], opt2: Option[T]): Option[T] = {
      // TODO: some implicit combiner?
      // for now just count the sums, silly function, but follows the rules.
      opt1.map(Option(_)).getOrElse(opt2)
     }

    def op2(opt1: Option[T], opt2: Option[T])(implicit m: Monoid[T]): Option[T] = {
      opt1.map { x => if (opt2.isDefined) { opt2.map( y => m.op(x,y) ) }
      else Option(x)
      }
        .getOrElse(opt2)
    }

     override def zero: Option[T] = None      
    
   }

/**
 * Requires that and implicit conversion from T to Monoid is in
 * scope.
 */
  class OptionMonoid2[T : Monoid] extends Monoid[Option[T]] {

      /**
       * doesn't have to be commutative just associative.
       *
       * (op(Some(x),Some(y)), Some(z)) == (Some(x),op(Some(y)), Some(z))
       * None    + Some(x) == Some(x)
       * Some(x) + None    == Some(x)
       */
      override def op(opt1: Option[T], opt2: Option[T]): Option[T] = {
        val m = implicitly[Monoid[T]]
        (opt1, opt2) match {
          case (Some(x), Some(y)) => Option(m.op(x,y))
          case (None, Some(y)) => Some(y)
          case (Some(x), None) => Some(x)
          case (None, None) => None
        }
//        opt1.map { x => if (opt2.isDefined) { opt2.map( y => m.op(x,y) ) }
//                        else Option(x)
//            }.getOrElse(opt2)
      }

      override def zero: Option[T] = None

    }

   class endoMonoid[T] extends Monoid[T=>T] {
       
       override def op(f: T => T, g: T => T): T => T = f compose g
       
       override def zero: T => T = { x => x }
   }