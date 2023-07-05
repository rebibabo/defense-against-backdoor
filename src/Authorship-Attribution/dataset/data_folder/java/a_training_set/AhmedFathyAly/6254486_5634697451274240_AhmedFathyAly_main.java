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
 
    
    InputReader in;
    StringTokenizer tok;
    StringBuilder ans;
 
    
 
    public static void main(String[] args) throws IOException
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
            String str = in.next();
            ans.append("Case #" + cas + ": " + solve(str) + "\n");
        }
        
        
        System.out.println(ans.toString());
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        out.write(ans.toString());
        out.close();
            
 
    }
 
    private int  solve(String str)
    {
        int nFlips = 0;
        
        for (int i = str.length() - 1; i >= 0; i--) 
        {
            boolean state = str.charAt(i) == '-';
            if(nFlips % 2 == 1)
                state = !state;
            if (state)
                nFlips++;
        }
            
        return nFlips;
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