package catalgebra.monad

import catalgebra.Functor

object Functors {

  // List type constructor is a functor "Functor[F]" instance consitues proof that F is a in fact a functor.
  val listFunctor = new Functor[List] {

    override
    def map[A,B](as: List[A])(f: A => B): List[B] = as map f

  }





}