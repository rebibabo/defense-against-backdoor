package durazom.codejam.lastword;
 
 import durazom.codejam.countingsheep.*;
 import durazom.codejam.minscalarproduct.*;
 import durazom.codejam.reversewords.*;
 import durazom.codejam.interfaces.Case;
 import durazom.codejam.storecredit.*;
 import java.util.ArrayList;
 import java.util.Collections;
 import static java.util.Collections.list;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import java.util.stream.Collectors;
 import java.util.stream.IntStream;
 
 
 
 public class LastWordCase extends Case {
 
     private String input;
 
     private String lastWord;
 
     public void setInput(String input) {
         this.input = input;
     }
 
     public String getLastWord() {
         return lastWord;
     }
     
     
 
     @Override
     public boolean solve() {
 
         StringBuilder sb = new StringBuilder();
 
         char firstChar = input.length()>0?input.charAt(0):'\0';
 
         for (int i = 0; i < input.length(); i++) {
 
             char c = input.charAt(i);
 
             if (i == 0) {
                 
                 sb.append(c);
                 
             }else{
                 
                 if( firstChar > c ){
                     
                     sb.append(c);
                     
                 }else{
                     
                     String aux = sb.toString();
                     sb.delete(0, sb.length());
                     sb.append(c).append(aux);
                     firstChar = c;
                     
                 }
                 
             }
 
         }
         
         lastWord = sb.toString();
         
         solved = true;
 
         return solved;
 
     }
 
 }
