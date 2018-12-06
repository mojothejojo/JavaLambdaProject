package JavaLamdaProject;

import java.util.stream.IntStream;

public class ExampleOfLambdaExpression {
    
    //the lambda Expression can be used in the declaration of the Runnable
    Runnable r1 = () -> System.out.println("My Runnable");
    
    public void sampleLambdaExpressions(){
        
    }
    
    
    //Declarative approach
    private static boolean isPrime(int number) {		
	   return number > 1
			&& IntStream.range(2, number).noneMatch(
					index -> number % index == 0);
}


}
