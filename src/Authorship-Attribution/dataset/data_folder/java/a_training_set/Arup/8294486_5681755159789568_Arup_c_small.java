import java.util.*;
 
 public class c_small {
    
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        
        for (int loop=1; loop<=numCases; loop++) {
            
            int n = stdin.nextInt();
            int q = stdin.nextInt();
            
            int[] maxHorse = new int[n];
            int[] horseSpeed = new int[n];
            for (int i=0; i<n; i++) {
                maxHorse[i] = stdin.nextInt();
                horseSpeed[i] = stdin.nextInt();
            }
            
            int[][] adj = new int[n][n];
            long[] dist = new long[n];
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    adj[i][j] = stdin.nextInt();
                    if (j == i+1) dist[j] = adj[i][j];
                }
            }
            
            
            for (int i=1; i<n; i++)
                dist[i] += dist[i-1];
            
            
            int s = stdin.nextInt();
            int e = stdin.nextInt();
            
            double[][] res = new double[n][n];
            double[] best = new double[n];
            res[1][0] = (double)adj[0][1]/horseSpeed[0];
            best[1] = res[1][0];
            
            
            for (int i=2; i<n; i++) {
                
                
                for (int j=0; j<i; j++) {
                    
                    if (dist[i]-dist[j] > maxHorse[j])
                        res[i][j] = 1e15;
                    else
                        res[i][j] = best[j] + 1.0*(dist[i]-dist[j])/horseSpeed[j];
                }
                
                best[i] = res[i][0];
                for (int j=1; j<i; j++)
                    best[i] = Math.min(best[i], res[i][j]);
                
            }
            
            double myans = res[n-1][0];
            for (int i=1; i<n-1; i++)
                myans = Math.min(myans, res[n-1][i]);
            
            System.out.println("Case #"+loop+": "+myans);
        }
    }
 }