import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.StringTokenizer;
 
 import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
 
 public class Main
 {
 
    public static void main(String[] args) throws IOException
    {
        Main main = new Main();
        main.read();
    }
 
    
    private StringBuilder ans;
    private BufferedReader in;
    private BufferedWriter out;
    private StringTokenizer tok;
    private int[] dist;
    private boolean[] visited;
 
    
 
    private void read() throws IOException
    {
        
        String inputFileName = "C:\\Users\\ahmed\\Desktop\\Codejam\\Input.in";
        String outputFileName = "C:\\Users\\ahmed\\Desktop\\Codejam\\Output.txt";
        in = new BufferedReader(new FileReader(inputFileName));
        out = new BufferedWriter(new FileWriter(outputFileName));
        ans = new StringBuilder();
 
        
        preCompute();
 
        
        int nCases = Integer.parseInt(in.readLine());
        for (int cas = 1; cas <= nCases; cas++)
        {
            int x = Integer.parseInt(in.readLine().trim());
            
            
            ans.append("Case #" + cas + ": " + dist[x] + "\n");
        }
 
        
        System.out.print(ans.toString());
        out.write(ans.toString());
        out.flush();
        out.close();
 
    }
 
    private void preCompute()
    {
        int n = 1000009;
        dist = new int[n];
        visited = new boolean[n];
        dist[1] = 1;
        visited[1] = true;
        Queue<Integer> q = new LinkedList<Integer>();
        Queue<Integer> d = new LinkedList<Integer>();
        q.add(1);
        d.add(1);
        while (!q.isEmpty())
        {
            int x = q.poll();
            int distance = d.poll();
            int next1 = x + 1;
            if (next1 < n)
                if (!visited[next1])
                {
                    visited[next1] = true;
                    dist[next1] = distance + 1;
                    q.add(next1);
                    d.add(distance + 1);
                }
            int next2 = flip(x);
            if (next2 < n)
                if (!visited[next2])
                {
                    visited[next2] = true;
                    dist[next2] = distance + 1;
                    q.add(next2);
                    d.add(distance + 1);
                }
 
        }
    }
 
    private String solve()
    {
        
        return "";
    }
 
    private int flip(int x)
    {
        StringBuilder strb = new StringBuilder(x + "");
        return Integer.parseInt(strb.reverse().toString());
    }
 }
