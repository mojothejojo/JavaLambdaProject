package JavaLamdaProject.JavaLambdaProject.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public  class Article {
    public String tag;
    public String author;
    public String content;
    public Long published;

     public String getTag() {
         return tag;
     }

     public void setTag(String tag) {
         this.tag = tag;
     }

     public String getAuthor() {
         return author;
     }

     public void setAuthor(String author) {
         this.author = author;
     }

     public String getContent() {
         return content;
     }

     public void setContent(String content) {
         this.content = content;
     }

     public Long getPublished() {
         return published;
     }

     public void setPublished(Long published) {
         this.published = published;
     }
 }
 
