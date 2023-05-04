import java.util.*;
 
 public class Main {
 
   public static void main(String args[]) {
     (new Main()).solve();
   }
 
   void solve() {
 
     Scanner cin = new Scanner(System.in);
 
     int T = cin.nextInt();
     for(int C=1; C<=T; ++C) {
 
        int row = cin.nextInt();
        int col = cin.nextInt();
        String array[] = new String[row];
        for(int i=0; i<row; ++i ) {
            array[i] = cin.next();
        }
        
        String ans[] = solve(array, row, col);
        
       System.out.println("Case #" + C + ":");
       for(int i =0; i<row; ++i) {
          System.out.println(ans[i]);
       }
 
     }
 
   }
   
   private static final int inf = 99999;
   
   String[] solve(String input[], int row, int col) {
      int minX[] = new int[26];
      int minY[] = new int[26];
      int maxX[] = new int[26];
      int maxY[] = new int[26];
      for(int i=0; i<26; ++i) {
          minX[i] = minY[i] = inf;
          maxX[i] = maxY[i] = -1; 
      }
      for(int i=0; i<row; ++i) {
          for(int j=0; j<col; ++j) {
              if( input[i].charAt(j) == '?' ) { continue; }
              int index = input[i].charAt(j) - 'A';
              minY[index] = Math.min(minY[index], i);
              minX[index] = Math.min(minX[index], j);
              maxY[index] = Math.max(maxY[index], i);
              maxX[index] = Math.max(maxX[index], j);
          }
      }
      for(int i=0; i<row; ++i) {
          for(int j=0; j<col; ++j) {
              boolean done = false;
              for(int k=0; k<26; ++k) {
                  if( minY[k] <= i && i <= maxY[k] && minX[k] <= j && j <= maxX[k] ) {
                      done = true;
                  }
              }
              if( done ) { continue; }
              
              for(int k=0; k<26; ++k) {
                  if( minY[k] > maxY[k] || minX[k] > maxX[k] ) { continue; }
                  int minYt = Math.min(minY[k], i);
                  int minXt = Math.min(minX[k], j);
                  int maxYt = Math.max(maxY[k], i);
                  int maxXt = Math.max(maxX[k], j);
                  boolean assign = true;
                  for(int l=0; l<26; ++l) {
                      if( k == l ) { continue; }
                      if( conflict(new int[] { minYt, minXt, maxYt, maxXt }, new int[] { minY[l], minX[l], maxY[l], maxX[l] }) ){
                          assign = false;
                          break;
                      }
                  }
                  if( assign ) {
                      minY[k] = minYt;
                      minX[k] = minXt;
                      maxY[k] = maxYt;
                      maxX[k] = maxXt;
                      break;
                  }
              }
          }
      }
      char array[][] = new char[row][col];
      for(int i=0; i<26; ++i) {
          for(int j=minY[i]; j<=maxY[i]; ++j) {
              for(int k=minX[i]; k<=maxX[i]; ++k) {
                  array[j][k] = (char)('A' + i);
              }
          }
      }
      String output[] = new String[row];
      for(int i=0; i<row; ++i) {
          String tmp = "";
          for(int j=0; j<col; ++j) {
              tmp += array[i][j];
          }
          output[i] = tmp;
      }
      return output;
   }
   
   boolean conflict(int a[], int b[]) {
      return inside(b[0], b[1], a) || inside(b[0], b[3], a) || inside(b[2], b[1], a) || inside(b[2], b[3], a) ||
              inside(a[0], a[1], b) || inside(a[0], a[3], b) || inside(a[2], a[1], b) || inside(a[2], a[3], b);
   }
   
   boolean inside(int y, int x, int a[]) {
      return ( a[0] <= y && y <= a[2] && a[1] <= x && x <= a[3] );
   }
 
 }
