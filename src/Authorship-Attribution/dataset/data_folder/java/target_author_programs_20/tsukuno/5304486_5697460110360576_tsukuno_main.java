import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
     	
     	int N = cin.nextInt();
     	int P = cin.nextInt();
     	
     	int req[] = new int[N];
     	for(int i = 0; i < N; ++i) {
     		req[i] = cin.nextInt();
     	}
     	
     	int pack[][] = new int[N][P];
     	for(int i = 0; i < N; ++i ) {
     		for(int j = 0; j < P; ++j ) {
     			pack[i][j] = cin.nextInt();
     		}
     	}
     	
       System.out.println("Case #" + C + ": " + solve(req, pack, N, P));
 
     }
 
   }
   
   int solve(int req[], int pack[][], int N, int P) {
 
 	  List<List> all = new ArrayList<List>();
 	  for(int i = 0; i < N; ++i ) {
 		  List<Range> ranges = new ArrayList<Range>();
 		  for(int j = 0; j< P; ++j) {
 			  Range range = new Range(req[i], pack[i][j]);
 			  
 			  if( range.min == -1 ) { continue; }
 			  ranges.add(range);
 		  }
 		  if( ranges.size() == 0 ) { return 0; }
 		  Collections.sort(ranges);
 		  all.add(ranges);
 	  }
 	  int counter[] = new int[N];
 	  int ret = 0;
 	  boolean can = true;
 	  while( can ) {
 		  Range range = ((Range)(all.get(0).get( counter[0] ))).copy();
 		  for(int i=1; i<N; ++i) {
 			  Range near = (Range)(all.get(i).get( counter[i] ));
 			  range.update(near);
 			  if( range.min == -1 ) {
 				  break;
 			  }
 		  }
 		  
 		  if( range.min == -1 ) {
 			  int pos = 0;
 			  Range min = (Range)(all.get(0).get( counter[0] ));
 			  for( int i=1; i<N; ++i) {
 				  Range cur = (Range)(all.get(i).get( counter[i] ));
 				  if( min.compareTo(cur) >= 0 ) {
 					  min = cur;
 					  pos = i;
 				  }
 			  }
 			  ++counter[pos];
 			  if( counter[pos] == all.get(pos).size() ) {
 					  can = false;
 			  }
 		  }
 		  else {
 			  ++ret;
 			  for(int i = 0; i < N; ++i ) {
 				  ++counter[i];
 				  if( counter[i] == all.get(i).size() ) {
 					  can = false;
 				  }
 			  }
 		  }
 	  }
 	  return ret;
   }
   
   class Range implements Comparable<Range> {
 	  int min;
 	  int max;
 	  private Range() { }
 	  Range(int req, int have) {
 		  min = max = -1;
 		  int minNear = Math.max(1, have * 100 / (req * 110) - 3);
 		  int maxNear = have * 100 / (req * 90) + 3;
 		  for( int i = minNear; i <= maxNear; ++i ) {
 			  if( (req * i * 90 + 99) / 100 <= have && have <= (req * i * 110 / 100) ) {
 				  if( min == -1 ) { min = i; }
 				  max = i;
 			  }
 		  }
 
 	  }
 	  
 	  Range copy() {
 		  Range range = new Range();
 		  range.min = min;
 		  range.max = max;
 		  return range;
 	  }
 	  
 	  void update(Range range) {
 
 		  min = Math.max(min, range.min);
 		  max = Math.min(max, range.max);
 		  if( min > max ) {
 			  min = max = -1;
 		  }
 
 	  }
 	  
 	  @Override
 	  public int compareTo(Range range) {
 		  if( min < range.min ) { return -1; }
 		  if( min > range.min ) { return 1; }
 		  if( max < range.max ) { return -1; }
 		  if( max > range.max ) { return 1; }
 		  return 0;
 	  }
   }
 
 }
