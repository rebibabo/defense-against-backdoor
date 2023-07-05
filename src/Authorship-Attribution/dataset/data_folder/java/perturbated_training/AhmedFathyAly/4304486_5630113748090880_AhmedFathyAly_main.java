import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.List;
 import java.util.StringTokenizer;
 
 public class Main
 {
 
    
    InputReader in;
    StringTokenizer tok;
    StringBuilder ans;
    private int n;
    private HashMap<Integer, Integer> count;
 
    
    
    public static void main(String[] args) throws Exception
    {
        Main sol = new Main();
        sol.begin();
 
    }
 
    private void begin() throws IOException
    {
        
        boolean file = true;
        if (file)
            in = new InputReader(new FileInputStream("B-small-attempt0.in"));
        else
            in = new InputReader(System.in);
        ans = new StringBuilder();
 
        
        int nCases = in.nextInt();
        for (int cas = 1; cas <= nCases; cas++) 
        {
            n = in.nextInt();
            count = new HashMap<Integer, Integer>();
            for (int i = 0; i < n * (n + n - 1); i++) 
            {
                int x = in.nextInt();
                if (!count.containsKey(x))
                    count.put(x, 1);
                else
                    count.put(x, count.get(x) + 1);
            }
                
            ans.append("Case #" + cas + ":");
            solve();
            ans.append("\n");
        }
        
        
        System.out.println("\nOutput: ");
        System.out.println(ans.toString());
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        out.write(ans.toString());
        out.close();
 
        
        System.out.println("\nTest: ");
    }
 
    private void solve()
    {
        int[] result = new int[n];
        int idx = 0;
        for (int x : count.keySet())
            if (count.get(x) % 2 == 1) 
            {
                result[idx++] = x;
            }
        Arrays.sort(result);
        for (int i = 0; i < result.length; i++) 
            ans.append(" " + result[i]);
            
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