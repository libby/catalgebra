package catalgebra

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
   * "lift a function taking one argument 'into the context of' some data type."
   */
  def map[A,B](fa: F[A])(f: A => B): F[B]
  
}

trait AdditionFunctorOps[F[_]] extends Functor[F] {

  // product
  def distribute[A,B](fab: F[(A, B)]):  (F[A], F[B]) = (map(fab)(_._1), map[(A, B),B](fab)(_._2))

  // sum
  def codistribute[A,B](e: Either[F[A],F[B]]): F[Either[A,B]] = {
    e match {
      case Left(fa) => map(fa)(Left(_))
      case Right(fb) => map(fb)(Right(_))
    }
  }

}