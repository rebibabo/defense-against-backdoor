package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.IOException;
 
 
 public class Round1CA implements GoogleCodeJam2015.Problem {
   
   private int R;
   private int C;
   private int W;
   private int ans;
   
   @Override
   public void setup(BufferedReader input) throws IOException {
     String[] args = input.readLine().split(" ");
     R = Integer.parseInt(args[0]);
     C = Integer.parseInt(args[1]);
     W = Integer.parseInt(args[2]);
   }
   
   private int getFirstRow(){
     if(C == W){
       return 1;
     }
     if(W == 1){
       return C;
     }
     return C / W;
   }
   
   private int getLastRow(){
     if(C == W || W == 1){
       return C;
     }
     
     int toHitOne = C / W;
     int toHitRest = W;
     if(C % W == 0){
       --toHitRest;
     }
     return toHitOne + toHitRest;
   }
 
   @Override
   public void solve() {
     int eliminate = R - 1;
     int toEliminate = getFirstRow();
     ans = (toEliminate * eliminate) + getLastRow();
   }
   
   @Override
   public String getSolution() {
     return Integer.toString(ans);
   }
   
 }
