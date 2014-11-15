package catalgebra

/**
 * A - inner type
 * Monad laws:
 * - given a type 'return' function should return a Monad with that type
 * 
 * - given f: A => MyMonad[A]
 *      // taking the element out of the monad and putting it back int 
 *      MyMonad(x).flatMap(f) === f(x) [Monad.bind(return) Haskell]
 * - myMonad.flatMap(MyMonad) == myMonad [ m >>= return ≡ m ]
 *
 * Binding two functions in succession is the same as binding one function that can be determined from them: 
 * - myMonad.flatMap ( x => f(x) ).flatMap(y => g(y)) === myMonad.flatMap(x => f(x).flatMap(y => g(y)))
 * 
 */
trait Monad[M[_]] {
    
    /**
     * map can be defined in terms of flatMap and unit (Haskell bind and unit).
     */
    def map[A,B](ma: M[A])(f: A => B): M[B] = flatMap(ma)(a => unit(f(a)))
    
    def apply[A](a: A): M[A] = unit(a)
    
    def unit[A](a: A): M[A]
    
    def flatMap[A,B](ma: M[A])(f: A => M[B]): M[B]
    
}