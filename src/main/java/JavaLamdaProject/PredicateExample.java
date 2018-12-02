package JavaLamdaProject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import JavaLamdaProject.JavaLambdaProject.model.Apple;

public class PredicateExample {
    public static void main(String args[]){
        //this is the list of the apples that is being created.
        List<Apple> apples = Arrays.asList(new Apple("green", 120.0), new Apple("red", 110.0),
			new Apple("brown", 150.0), new Apple("green", 160.0), new Apple("red", 122.0));
	
	   //set of predicates approach 
	
	    //Approach:1
	    //either the predicate may be sent in the from of the function or  
	    filterApples(apples, isWeightAbove150()).forEach(System.out::println);

	    //Approach:2	    
	    //may be sent in the form of the Lambda expression
	    filterApples(apples, apple -> apple.getWeight() >= 150).forEach(System.out::println);

	    //Approach:3	    
	    //the predicate may be given in the form of the anonymous classes as well
	    filterApples(apples,apple->{
	        return apple.getWeight()>=150;
	    }).forEach(System.out::println);
	    
	    
	    //Approach:4
	    //alternaltely we can use the simple filter method instead of a function as:
	    apples.stream()
	    .filter(apple->{
	        return apple.getWeight()>=150;
	    })
	    .collect(Collectors.toList())
	    .forEach(System.out::println);
	    
	    
	    //Approach:5
	    //creating predicate declaration as the variable
	    //and streaming through the variable
	    //here the test function is not needed as the filter implicitly calls the test()
	    Predicate<Apple>isWeightAbove150_var=apple->apple.getWeight()>=150;
	    apples.stream()
	    .filter(isWeightAbove150_var)
	    .collect(Collectors.toList())
	    .forEach(System.out::println);
	    
	    
	    //other function of the predicate
	    //and predicate
	    //same way use the or predicate
	    Predicate<Apple> isColorGreen=apple-> apple.getColor().equals("green");
	    Predicate<Apple> isColorGreen_and_weightAbove150=isColorGreen.and(isWeightAbove150_var);
	    //now stream over the apples
	    apples.stream().filter(isColorGreen_and_weightAbove150).forEach(System.out::println);
	    
	    //for the negate use the following example syntax
	    Predicate<Apple> isNotGreen=isColorGreen.negate();
	    //strem over the apples to find the ones that are non-green
	    apples.stream().filter(isNotGreen).forEach(System.out::println);
	    
	    
	    
	    //if the single apple is to be tested against the predicate the test() may
	    //be used as it will give the boolean response
	    Apple testApple = new Apple("green", 120.0);
	    //predicate being defined in the variable
        System.out.println(isColorGreen.test(testApple));
        //predicate being defined in the function return value.
        System.out.println(isWeightAbove150().test(testApple));
        
        
        //and the equals method may be given as
        //declaring the predicate for a standard apple which will be compared against
        Predicate<Apple> standardApplePredicate = Predicate.isEqual(new Apple("red", 150.0));
        
        //declaring a test apple that will be tested for
        Apple testApple2 = new Apple("green", 120.0);
        //test 1 against the dummy apple created above : false
        System.out.println(standardApplePredicate.test(testApple2));
        //test 2 against the valid apple : true
        System.out.println(standardApplePredicate.test(new Apple("red", 150.0)));
	    
    }
    
    //set of predicates
    public static Predicate<Apple> isWeightAbove150() {
		return apple -> apple.getWeight() >= 150;
	}
	
	//filtering of stream of Apples
	public static List<Apple> filterApples(List<Apple> apples, Predicate<Apple> predicate) {
	    //here we are adding the predicate for the filtering.
	    //then collecting the data in the list.
	   return apples.stream().filter(predicate).collect(Collectors.toList());
    }
	
}
