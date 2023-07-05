import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.ArrayList;
 import java.util.LinkedList;
 
 
 
 
 public class Paper {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("paper.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("paper.out")));
        int q = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= q; i++){
            int n = Integer.parseInt(buf.readLine());
            int acount = 0;
            int bcount = 0;
            HashMap<String, Integer> a = new HashMap<String, Integer>();
            HashMap<String, Integer> b = new HashMap<String, Integer>();
            ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
            for(int j = 0; j < n; j++){
                String[] s = buf.readLine().split(" ");
                String first = s[0];
                String second = s[1];
                if(!a.containsKey(first)) {
                    a.put(first, acount);
                    acount++;
                    edges.add(new ArrayList<Integer>());
                }
                if(!b.containsKey(second)) {
                    b.put(second, bcount);
                    bcount++;
                }
                edges.get(a.get(first)).add(b.get(second));
            }
            int size = acount + bcount + 2;
            int[][] graph = new int[size][size];
            for(int j = 0; j < acount; j++){
                graph[0][2+j] = 1;
            }           
            for(int j = 0; j < bcount; j++){
                graph[2+acount+j][1] = 1;
            }
            for(int j = 0; j < edges.size(); j++){
                for(int k : edges.get(j)){
                    graph[2+j][2+acount+k] = 1;
                }
            }
            int flow = acount + bcount - fordFulkerson(graph,0,1,size);
            out.println("Case #" + i + ": " + (n - flow));
        }
        out.close();
    }
    
    
    public static boolean bfs(int rGraph[][], int s, int t, int parent[], int n)
    {
        
        boolean[] visited = new boolean[n];
     
        
        
        LinkedList<Integer> q = new LinkedList<Integer>();
        q.addLast(s);
        visited[s] = true;
        parent[s] = -1;
     
        
        while (!q.isEmpty())
        {
            int u = q.peek();
            q.pop();
     
            for (int v=0; v<n; v++)
            {
                if (visited[v]==false && rGraph[u][v] > 0)
                {
                    q.push(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
     
        
        
        return (visited[t] == true);
    }
     
    
    public static int fordFulkerson(int graph[][], int s, int t, int n)
    {
        int u, v;
     
        
        
        
        int[][] rGraph = new int[n][n]; 
                         
                         
        for (u = 0; u < n; u++)
            for (v = 0; v < n; v++)
                 rGraph[u][v] = graph[u][v];
     
        int[] parent = new int[n];  
     
        int max_flow = 0;  
     
        
        while (bfs(rGraph, s, t, parent, n))
        {
            
            
            
            int path_flow = Integer.MAX_VALUE;
            for (v=t; v!=s; v=parent[v])
            {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }
     
            
            
            for (v=t; v != s; v=parent[v])
            {
                u = parent[v];
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
     
            
            max_flow += path_flow;
        }
     
        
        return max_flow;
    }
 }
