package durazom.codejam.coinjam;
 
 import durazom.codejam.interfaces.Case;
 import java.math.BigInteger;
 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import java.util.TreeSet;
 
 
 
 public class CoinjamCase extends Case {
 
     private int length = 0;
     private int coinjamsNeeded = 0;
     private List<Coinjam> coinjamsFound;
     
     
     public CoinjamCase() {
         this.coinjamsFound = new ArrayList<Coinjam>();
     }
 
     public void setLength(int length) {
         this.length = length;
     }
 
     public void setCoinjamsNeeded(int coinjamsNeeded) {
         this.coinjamsNeeded = coinjamsNeeded;
     }
 
     public List<Coinjam> getCoinjamsFound() {
         return coinjamsFound;
     }
 
     @Override
     public boolean solve() {
         
         BigInteger number = BigInteger.ZERO;
         
         while(true){
         
             StringBuilder sb = new StringBuilder();
             
             sb.append('1').append(getBinary(number, this.length-2) ).append('1');
             String originalValue = sb.toString();
             if( originalValue.length() > this.length ){
                 break;
             }
             Long[] divisors = new Long[9];
             boolean found = true;
             
             for( int i = 10,index=8 ; i >= 2 ; i--, index-- ){
                 
                 String value = originalValue;
                 BigInteger base10Number = new BigInteger(value, i);
                 value = base10Number.toString();
                 
                 
                 if( base10Number.isProbablePrime( 10 ) ){
                     found = false;
                     break;
                 }
                 
                 Long divisor = getDivisor( new BigInteger(value) );
                 
                 if( divisor == -1l ){
                     found = false;
                     break;
                 }
                 
                 divisors[index] = divisor;
                 
             }
             
             if( found ){
                 
                 Coinjam coinjam = new Coinjam();
                 coinjam.setDivisors(divisors);
                 coinjam.setValue(originalValue);
                 System.out.println(coinjam);
                 coinjamsFound.add(coinjam);
                 if( coinjamsFound.size() == coinjamsNeeded  ){
                     break;
                 }
                 
             }
             
             
             number  = number.add(BigInteger.ONE);
             
             
         }
         
         solved = true;
         
         return solved;
         
 
     }
     
     public String getBinary(BigInteger n, int length){
         
         StringBuilder sb = new StringBuilder();
         
         String binaryNumber = n.toString(2);
         
         for(int i = 0 ; i< length-binaryNumber.length() ; i++){
             
             sb.append("0");
             
         }
         
         sb.append(binaryNumber);
         
         return sb.toString();
         
     }
     
     public List<Long> primeNumbers = new ArrayList<>();
     
     int limit = 50000;
     int limitHitsLimit = 1000;
     int limitsHitSinceIncrease = 0;
     int limitIncrease = 25000;
     
     public Long getDivisor( BigInteger n){
         
         int index = 0;
         
         Long number = getPrime(index);
         
         BigInteger biNumber = new BigInteger(String.valueOf(number));
         
         while( biNumber.compareTo( 
                 n.divide(new BigInteger("2"))) < 0) {
             
             if( n.mod( biNumber  )== BigInteger.ZERO ){
                 return number;
             }
             
             number = getPrime(++index);
             
             biNumber = new BigInteger(String.valueOf(number));
             
             if( index > limit ){
                 
                 limitsHitSinceIncrease++;
                 System.out.println("Hit limit, moving on");
                 
                 if( limitHitsLimit == limitsHitSinceIncrease ){
                     
                     System.out.println("Incrementing the limit ");
                     limit += limitIncrease;
                     limitsHitSinceIncrease = 0;
                     
                 }
                 
                 break;
                 
             }
             
         }
         
         return -1l;
         
     }
    
     
     public Long getPrime(int index){
         
         while(  index > primeNumbers.size()-1 ){
             Long prime = 2l;
             if( primeNumbers.isEmpty() ){
                 primeNumbers.add(2l);
             }else{
                 prime = primeNumbers.get(primeNumbers.size()-1);
                 primeNumbers.add(generatePrimeNumber( prime ));
             }
             
         }
             
         return primeNumbers.get(index);
             
     }
     
     public Long generatePrimeNumber(Long initial){
         
         Long newNumber = initial+1;
         
         while( !isPrime(newNumber) && newNumber< Long.MAX_VALUE-1 ){
             newNumber++;
         }
         
         return newNumber;
         
         
         
     }
     
     public  boolean isPrime( Long number) {
         
         
         if (primeNumbers.stream().anyMatch((prime) -> ( number%prime == 0 ))) {
             return false;
         }
         
         Long lastPrime = primeNumbers.get(primeNumbers.size()-1);
         
         while( lastPrime++ < number/2 ){
             
             if( number%lastPrime == 0 ){
                 return false;
             }
             
         }
         
         return true;
         
     }
     
 
 }
