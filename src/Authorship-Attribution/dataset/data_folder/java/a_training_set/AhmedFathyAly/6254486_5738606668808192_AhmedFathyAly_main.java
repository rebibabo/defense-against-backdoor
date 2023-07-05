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
 
    
    int n = 16;
    int req = 50;
 
    public static void main(String[] args) throws IOException
    {
        Main sol = new Main();
        sol.begin();
 
    }
 
    private void begin() throws IOException
    {
        
        boolean file = true;
        if (file)
            in = new InputReader(new FileInputStream("B-large.in"));
        else
            in = new InputReader(System.in);
        ans = new StringBuilder();
 
        
        ans.append("Case #1:\n");
        solve();
 
        
        System.out.println(ans.toString());
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        out.write(ans.toString());
        out.close();
 
    }
 
    private void solve()
    {
        long curr = 0;
        int count = 0;
        while (count < req)
        {
            long num = 1 + (curr << 1) + (1l << n - 1);
            String numStr = toString(num);
            if (allBasesNonPrime(numStr))
            {
                count++;
                addToAnswer(numStr);
            }
            curr++;
 
        }
 
    }
 
    private void addToAnswer(String numStr)
    {
        ans.append(numStr);
        for (int base = 2; base <= 10; base++)
        {
            long x = toBase(base, numStr);
            for (long i = 2; i * i <= x; i++)
                if (x % i == 0)
                {
                    ans.append(" " + i);
                    break;
                }
        }
        ans.append("\n");
 
    }
 
    private boolean isPrime(long x)
    {
        for (long i = 2; i * i <= x; i++)
            if (x % i == 0)
                return false;
        return true;
    }
 
    private long toBase(int base, String str)
    {
        long count = 0;
        long pow = 1;
 
        for (int i = str.length() - 1; i >= 0; i--)
        {
            if (str.charAt(i) == '1')
                count += pow;
            pow *= base;
        }
 
        return count;
    }
 
    private boolean allBasesNonPrime(String num)
    {
        for (int i = 2; i <= 10; i++)
            if (isPrime(toBase(i, num)))
                return false;
        return true;
    }
 
    private String toString(long num)
    {
        String str = "";
        for (int i = 0; i < n; i++)
            if ((num & (1 << i)) != 0)
                str = "1" + str;
            else
                str = "0" + str;
        return str;
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