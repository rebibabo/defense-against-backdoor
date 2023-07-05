
 package durazom.cruisecontrol;
 
 import durazom.fizzbuzz.*;
 import durazom.util.Case;
 import java.math.BigDecimal;
 import java.math.BigInteger;
 import java.math.RoundingMode;
 
 
 public class CruiseControlCase implements Case {
 
     int cases = 1;
     
     @Override
     public String solve(String caseString) {
         
         String[] testCase = caseString.split("\n");
         
         
         String keyInfo = testCase[0];
         
         String[] splittedKeyInfo = keyInfo.split(" ");
         
         long endPoint = Long.parseLong(splittedKeyInfo[0]);
         
        
         BigDecimal zero = new BigDecimal(BigInteger.ZERO);
         
         BigDecimal topSpeed = new BigDecimal(zero.toString());
         
         boolean start = true;
         
         
         for( int i = 1 ; i < testCase.length; i++ ) {
             
             String[] split = testCase[i].split(" ");
             
             long currentPoint = Long.parseLong( split[0] );
             long speed = Long.parseLong(split[1]);
             
             if( speed == 0l) return "0";
             
             
             long distanceLeft = endPoint - currentPoint;
             
             BigDecimal hoursLeft = (new BigDecimal( distanceLeft )).divide( new BigDecimal( speed ), 7, RoundingMode.HALF_UP );
             
             BigDecimal candidateSpeed = (new BigDecimal( endPoint )).divide( hoursLeft, 7, RoundingMode.HALF_UP );
             
             if( start ){
                 
                 topSpeed = candidateSpeed;
                 
                 start = false;
                 
             }else{
                 
                 if( candidateSpeed.compareTo( topSpeed ) < 0 ){
                     
                     topSpeed = candidateSpeed;
                     
                 }
                 
             }
             
         }
         
          String result = "Case #"+(cases++)+": ";
         
         return result+topSpeed.toString();
         
         
     }
     
 }
