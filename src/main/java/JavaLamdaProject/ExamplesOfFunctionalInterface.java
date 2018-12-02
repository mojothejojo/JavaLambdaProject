package JavaLamdaProject;

public class ExamplesOfFunctionalInterface {
    
interface Foo { boolean equals(Object obj); }
// Not functional because equals is already an implicit member (Object class)



interface Comparator<T> {
 boolean equals(Object obj); //this is non-overriding abstract function
 int compare(T o1, T o2);//this is the abstract non-Object method
}
// Functional because Comparator has only one abstract non-Object method
// this is not FunctionalInterface as it has 2 abstract functions.



interface Foo_not {
  int m();  // this is the  abstract function
  Object clone(); //this is again non-overriding abstract function
}
// Not functional because method Object.clone is not public
// this is not FunctionalInterface as it has 2 abstract functions.



interface X { int m(Iterable<String> arg); }
interface Y { int m(Iterable<String> arg); }
//interface is not class so it can extend multiple interfaces
interface Z extends X, Y {
    //this multiple extend is allowed only because the diamomd dependency is not getting created
    // had the 2 m's been default -- this multiple extension would have been disallowed.
}
// Functional: two methods, but they have the same signature




interface X_2 { Iterable m(Iterable<String> arg); }
interface Y_2 { Iterable<String> m(Iterable arg); }
interface Z_2 extends X_2, Y_2 {}
// Functional: Y.m is a subsignature & return-type-substitutable


interface X_3 { Iterable m(Iterable<String> arg); }
interface Y_3 { Integer m(Integer arg); }
interface Z_3 extends X_3, Y_3 {
}
// Not functional: No method has a subsignature of all abstract methods
// thus there are 2 abstract functions 


interface X_4 { int m(Iterable<String> arg); }
interface Y_4 { int m(Iterable<Integer> arg); }
//interface Z_4 extends X_4, Y_4 {}
// Not functional: No method has a subsignature of all abstract methods
//infact this extends is not allowed as both the methods have the same erasure but none overrides the 
//other - this is the error message as given in the intellij



interface X_5 { int m(Iterable<String> arg, Class c); }
interface Y_5 { int m(Iterable arg, Class<?> c); }
//interface Z_5 extends X_5, Y_5 {}
// Not functional: No method has a subsignature of all abstract methods
// the reason is same as above

interface X_6 { long m(); } 
interface Y_6 { int m(); }
//interface Z_6 extends X_6, Y_6 {}
// Compiler error: no method is return type substitutable
// methods have unrelated return types

interface Foo_2<T> { void m(T arg); }
interface Bar<T> { void m(T arg); }
//interface FooBar<X, Y> extends Foo_2<X>, Bar<Y> {}
// Compiler error: different signatures, same erasure
// same error that 2 interfaces have same erasure yet none overrides the other

}
