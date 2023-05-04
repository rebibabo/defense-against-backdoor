package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.List;
 
 
 public class QualsC implements GoogleCodeJam2015.Problem {
   
   private static final String YES = "YES";
   private static final String NO = "NO";
   
   private boolean isPossible;
   private int length;
   private int repeat;
   private char[] numbers;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
     String[] args = input.readLine().split(" ");
     length = Integer.parseInt(args[0]);
     repeat = Integer.parseInt(args[1]);
     
     char[] chars = input.readLine().toCharArray();
     numbers = new char[length * repeat];
     for(int i=0; i<repeat; ++i){
       for(int j=0; j<length; ++j){
         numbers[i*length+j] = chars[j];
       }
     }
   }
   
   private boolean[] kCache;
   
   private void buildKCache(){
     int totalLength = length * repeat;
     kCache = new boolean[totalLength];
     for(int i=2; i<totalLength; ++i){
       ComplexNumber num = new ComplexNumber('1');
       for(int j=i; j<totalLength; ++j){
         num.combine(numbers[j]);
       }
       kCache[i] = num.sameAs('k');
     }
   }
   
   @Override
   public void solve() {
     int totalLength = length * repeat;
     isPossible = false;
     if(totalLength < 3){
       return;
     }
     
     buildKCache();
     
     ComplexNumber first = new ComplexNumber('1');
     for(int i=0; i<totalLength-2; ++i){
       first.combine(numbers[i]);
       if(first.sameAs('i')){
         ComplexNumber second = new ComplexNumber('1');
         for(int j=i+1; j<totalLength-1; ++j){
           second.combine(numbers[j]);
           if(second.sameAs('j') && kCache[j + 1]){
             isPossible = true;
             return;
           }
         }
       }
     }
   }
 
   @Override
   public String getSolution() {
     return isPossible ? YES : NO;
   }
   
   
   private static class ComplexNumber {
     private boolean isNegative;
     private char value;
     
     public ComplexNumber(char c){
       value = c;
       isNegative = false;
     }
     
     public ComplexNumber(ComplexNumber other){
       value = other.value;
       isNegative = other.isNegative;
     }
     
     public boolean sameAs(char c){
       return !isNegative && value == c;
     }
     
     public void combine(char c){
       if(c == '1'){
         
         return;
       }
       
       if(this.value == '1'){
         
         this.value = c;
         return;
       }
       
       if(this.value == c){
         
         this.isNegative = !this.isNegative;
         this.value = '1';
         return;
       }
       
       if(this.value == 'i'){
         if(c == 'j'){
           
           this.value = 'k';
         } else {
           
           this.value = 'j';
           this.isNegative = !this.isNegative;
         }
       } else if(this.value == 'j'){
         if(c == 'i'){
           
           this.value = 'k';
           this.isNegative = !this.isNegative;
         } else {
           
           this.value = 'i';
         }
       } else {
         if(c == 'i'){
           
           this.value = 'j';
         } else {
           
           this.value = 'i';
           this.isNegative = !this.isNegative;
         }
       }
     }
   }
 }
