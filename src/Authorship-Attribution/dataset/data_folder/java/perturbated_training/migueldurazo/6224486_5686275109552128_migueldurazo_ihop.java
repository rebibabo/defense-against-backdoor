
 package codejamnew;
 
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class IHOP {
     
     List<Integer> nonEmptyPancakes;
     
     public IHOP( String line ){
         
         nonEmptyPancakes = new ArrayList<>();
         
         String[] splitted = line.split(" ");
         
         for( String s : splitted ){
             
            int noPancakes = Integer.parseInt( s );
            
            nonEmptyPancakes.add(noPancakes);
             
         }
         
     }
     
     public int obtanSmallestNumberOfMinutes(){
         
         int minutes = getMinutesUntilFinished();
         int penaltyMinutes = 0;
         
         int minutesToCompare = minutes;
         
         boolean cut = true;
         
         do{
             
             penaltyMinutes++;
             cut = splitLargestTower();
             
             minutesToCompare = getMinutesUntilFinished()+penaltyMinutes;
             
             if( minutesToCompare < minutes ){
                 
                 minutes = minutesToCompare;
                 
             }
             
         }while( cut );
         
         
         return minutes;
         
     }
     
     private boolean splitLargestTower(){
         
         
         
         int max = nonEmptyPancakes.get(0);
         int maxIndex = 0;
         int counter = 0;
         for( int pancakes : nonEmptyPancakes ){
             
             if(  max < pancakes ){
                 
                 max = pancakes;
                 maxIndex = counter;
                 
             }
             
             counter++;
             
         }
         
         
         
         int maxTowerNumber = nonEmptyPancakes.get(maxIndex);
         
         int newTower = maxTowerNumber/2;
         int maxTowerNewNumber = maxTowerNumber-newTower;
         
         if( newTower == 0 ){
             
             return false;
             
         }
         
         nonEmptyPancakes.set(maxIndex, maxTowerNewNumber);
         
         nonEmptyPancakes.add( newTower );
         
         return true;
         
     }
     
     private int getMinutesUntilFinished(  ){
         
         
         int max = nonEmptyPancakes.get(0);
         for( int pancakes : nonEmptyPancakes ){
             
             if(  max < pancakes ){
                 
                 max = pancakes;
                 
             }
             
         }
         
         return max;
         
     }
     
     
     
 }
