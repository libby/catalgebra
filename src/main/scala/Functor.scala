
/**
 * A Functor converts one catagory to another equivalent category, where equivalant 
 * means, one can transform the Category A to Category B 
 * (transforms both objects and function on those objects) perform operations on those objects f(), g(), h() of category B
 * and transform those object back to the original category, and this is the same as performing f(), g() on those objects
 * transforming Category A to Categroy B preforming h() in  Category B and transforming the Category B back to Category A
 *
 * homomorphisms between categories
 * Two important consequences of the functor axioms are:
 *
 *  F transforms each commutative diagram in C into a commutative diagram in D;
 *  if f is an isomorphism in C, then F(f) is an isomorphism in D.
 *
 *  Functor (mappable) things that can be mapped over. 
 */
trait Functor[F[_]] {
  
  /**
   * transform the category
   */
  def map[A,B](fa: F[A])(f: A => B): F[B]   
  
} 