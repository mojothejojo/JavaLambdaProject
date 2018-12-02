package JavaLamdaProject;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import JavaLamdaProject.JavaLambdaProject.model.*;

public class BiFunctionExample {
    public static void main(String args[]){
    
    //byAuthor takes the name of an author and the list of articles, 
    //returning a list of the articles written by the author requested.
    BiFunction<String, List<Article>, List<Article>> byAuthor =
    (name, articles) -> articles.stream() //stream over the second argument
        .filter(a -> a.getAuthor().equals(name)) //filter for the first argument 
        .collect(Collectors.toList());//result for the last generic argument.
        
    //byTag takes a tag and the list of articles, returning articles with the requested tag.
    BiFunction<String, List<Article>, List<Article>> byTag =  
    (tag, articles) -> articles.stream()
        .filter(a -> a.getTag().contains(tag))
        .collect(Collectors.toList());
        
        
    //this is simple function to sort the tags by date    
    Function<List<Article>, List<Article>> sortByDate =  
    articles -> articles.stream()
        .sorted((x,y) -> y.getPublished().compareTo(x.getPublished()))
        .collect(Collectors.toList());

    
    
    //simple function to list the article that is first non-empty
    Function<List<Article>, Optional<Article>> first =  
    a -> a.stream().findFirst();
    
    
    //this will first sort by date and then will call the 'first' function declaration.
    Function<List<Article>, Optional<Article>> newest =  
    first.compose(sortByDate);
    
    //when this is applied on the stream of Article - this will first apply the byAuthor
    // and then it will apply the newest 
    BiFunction<String, List<Article>, Optional<Article>> newestByAuthor =  
    byAuthor.andThen(newest);
    
    
    BiFunction<String, List<Article>, List<Article>> byAuthorSorted =  
    byAuthor.andThen(sortByDate);
    
    BiFunction<String, List<Article>, Optional<Article>> newestByTag =  
    byTag.andThen(newest);
    
    
    }
}
