
 package durazom.codejam.coinjam;
 
 import java.util.Arrays;
 import java.util.stream.Collectors;
 
 
 public class Coinjam {
     
     private String value;
     private Long[] divisors = new Long[9];
 
     public String getValue() {
         return value;
     }
 
     public void setValue(String value) {
         this.value = value;
     }
 
     public Long[] getDivisors() {
         return divisors;
     }
 
     public void setDivisors(Long[] divisors) {
         this.divisors = divisors;
     }
 
     @Override
     public String toString() {
         
         return value+" "+Arrays.asList(divisors).stream()
                 .map(divisor-> String.valueOf(divisor))
                 .collect( Collectors.joining(" ") );
 
     }
     
     
     
 }
