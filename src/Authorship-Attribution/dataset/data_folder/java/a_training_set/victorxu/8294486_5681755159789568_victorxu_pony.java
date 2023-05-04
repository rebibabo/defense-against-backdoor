import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class Pony {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("Pony.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Pony.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++) {
            out.print("Case #" + (i + 1) + ": ");
            String[] s = buf.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int q = Integer.parseInt(s[1]);
            int[][] input = new int[n][2];
            double[][] edges1 = new double[n][n];
            for(int j = 0; j < n; j++){
                s = buf.readLine().split(" ");
                input[j][0] = Integer.parseInt(s[0]);
                input[j][1] = Integer.parseInt(s[1]);
            }
            for(int j = 0; j < n; j++){
                s = buf.readLine().split(" ");
                for(int k = 0; k < n; k++){
                    if(Integer.parseInt(s[k]) == -1){
                        edges1[j][k] = Double.POSITIVE_INFINITY;
                    }
                    else edges1[j][k] = Double.parseDouble(s[k]);
                }
            }
            double[][] paths1 = floydWarshall(edges1, n);
            double[][] edges2 = new double[n][n];
            for(int j = 0; j < n; j++) {
                double max = input[j][0] + 0.1;
                double speed = input[j][1];
                for(int k = 0; k < n; k++){
                    if(paths1[j][k] <= max){
                        edges2[j][k] = paths1[j][k]/speed;
                    } else {
                        edges2[j][k] = Double.POSITIVE_INFINITY;
                    }
                }
            }
            double[][] paths2 = floydWarshall(edges2, n);
            for(int j = 0; j < q; j++){
                s = buf.readLine().split(" ");
                int start = Integer.parseInt(s[0]) - 1;
                int end = Integer.parseInt(s[1]) - 1;
                out.print(paths2[start][end] + " ");
            }
            out.println();
        }
        out.close();
    }
    
    
    public static double[][] floydWarshall(double graph[][], int n)
     {
         double dist[][] = new double[n][n];
         int i, j, k;
  
         
         for (i = 0; i < n; i++)
             for (j = 0; j < n; j++)
                 dist[i][j] = graph[i][j];
  
         
         for (k = 0; k < n; k++)
         {
             
             for (i = 0; i < n; i++)
             {
                 
                 
                 for (j = 0; j < n; j++)
                 {
                     
                     
                     if (dist[i][k] + dist[k][j] < dist[i][j])
                         dist[i][j] = dist[i][k] + dist[k][j];
                 }
             }
         }
  
         
         return dist;
     }
 }
