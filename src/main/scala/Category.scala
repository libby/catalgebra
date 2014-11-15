
// trait Category {
//     abstract def objs: Set[Type]
//     abstract def arrows: Set[this.objs => this.objs]
// }
// 
// MyCategory extends Category {
//     def objs = Set(Int, String)
//     def arrows = {{n: Int => n + 1}, {n: Int => n.toString}}
// }
// 
// MyCategory1 extends Category {
//     def objs = Set(List[Int], List[String])
//     def arrows = {{n: List[Int] => n map _ + 1}, {n: List[Int] => n map _.toString}}
// }
// 
// Functor[MyCategory, MyCategory1] {
//     def obj_transform(val oElem: MyCategory.objs): MyCategory1.objs = List[oElem]
//     def arrow_transform(val oArrow: MyCategory.arrows): MyCategory1.arrows = 
//         { l: obj_transform(oArrow.sourceType) => (l map oArrow): obj_transform(oArrow.targetType)}
// }
