package googlecodejam2016;
 
 import googlecodejam2016.GoogleCodeJam2016.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 
 public class Round1AB implements Problem {
   
   private int N;
   private int[][] lists;
   private String solution;
   
   @Override
   public void setup(BufferedReader input) throws IOException {
     N = Integer.parseInt(input.readLine());
     int numLists = 2 * N - 1;
     lists = new int[numLists][N];
     for(int i=0; i<numLists; ++i){
       String line = input.readLine();
       String[] listNums = line.split(" ");
       for(int j=0; j<N; ++j){
         lists[i][j] = Integer.parseInt(listNums[j]);
       }
     }
   }
 
   @Override
   public void solve() {
     int numLists = 2*N-1;
     Map<Integer,Integer> counts = new HashMap<>();
     List<Integer> uniqueNums = new ArrayList<>();
     for(int i=0; i<numLists; ++i){
       for(int j=0; j<N; ++j){
         int num = lists[i][j];
         if(counts.get(num) == null){
           uniqueNums.add(num);
           counts.put(num, 0);
         }
         counts.put(num, counts.get(num) + 1);
       }
     }
     
     List<Integer> oddNums = new ArrayList<>(N);
     for(Integer num : uniqueNums){
       if(counts.get(num)%2 == 1){
         oddNums.add(num);
       }
     }
     
     Collections.sort(oddNums);
     StringBuilder stringBuilder = new StringBuilder();
     for(Integer num : oddNums){
       stringBuilder.append(num).append(" ");
     }
     solution = stringBuilder.toString().trim();
   }
 
   @Override
   public String getSolution() {
     return solution;
   }
   
 }
