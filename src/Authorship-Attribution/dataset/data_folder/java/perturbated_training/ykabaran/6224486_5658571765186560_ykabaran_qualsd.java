package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class QualsD implements GoogleCodeJam2015.Problem {
 
   private static final String RICHARD = "RICHARD";
   private static final String GABRIEL = "GABRIEL";
   
   private int dominoSize;
   private int rows;
   private int columns;
   
   private String winner;
   
   @Override
   public void setup(BufferedReader input) throws IOException {
     String[] args = input.readLine().split(" ");
     dominoSize = Integer.parseInt(args[0]);
     rows = Integer.parseInt(args[1]);
     columns = Integer.parseInt(args[2]);
   }
 
   @Override
   public void solve() {
     if(dominoSize == 1){
       winner = GABRIEL;
       return;
     }
     
     if(dominoSize == 2){
       if(rows % 2 == 0 || columns % 2 == 0){
         winner = GABRIEL;
       } else {
         winner = RICHARD;
       }
       return;
     }
     
     if(dominoSize == 3){
       if(!(rows == 3 || columns == 3)){
         winner = RICHARD;
       } else {
         int other = rows == 3 ? columns : rows;
         if(other == 2 || other == 3 || other == 4){
           winner = GABRIEL;
         } else {
           winner = RICHARD;
         }
       }
       return;
     }
     
     if(dominoSize == 4){
       if(rows == 4 && columns == 4){
         winner = GABRIEL;
         return;
       }
       if((rows == 3 && columns == 4) || (rows == 4 && columns == 3)){
         winner = GABRIEL;
         return;
       }
       winner = RICHARD;
       return;
     }
     
     winner = "NOT IMPLEMENTED";
   }
 
   @Override
   public String getSolution() {
     return winner;
   }
   
 }
