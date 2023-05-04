package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.List;
 
 
 public class QualsD implements Problem {
   
   private static final String IMPOSSIBLE = "IMPOSSIBLE";
   
   private int numTiles;
   private int complexity;
   private int numToReveal;
   private List<Long> tilesToReveal;
 
   @Override
   public void setup(BufferedReader input) throws IOException {
     String[] args = input.readLine().split(" ");
     numTiles = Integer.parseInt(args[0]);
     complexity = Integer.parseInt(args[1]);
     numToReveal = Integer.parseInt(args[2]);
   }
 
   @Override
   public void solve() {
     tilesToReveal = new ArrayList<>(numToReveal);
     long stepSize = 1;
     for(int i=1; i<complexity; ++i){
       stepSize *= numTiles;
     }
     for(int i=0; i<numToReveal; ++i){
       tilesToReveal.add(1 + stepSize * i);
     }
   }
 
   @Override
   public String getSolution() {
     StringBuilder stringBuilder = new StringBuilder();
     for(Long tileToReveal : tilesToReveal){
       stringBuilder.append(Long.toString(tileToReveal));
       stringBuilder.append(" ");
     }
     return stringBuilder.toString();
   }
   
 }
