import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.Comparator;
 import java.util.Stack;
 import java.util.StringTokenizer;
 
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
    private int mul;
    private boolean[] contains;
    private int v;
    private int[][] dp;
    private int c;
    private int d;
    private int needed;
    private ArrayList<boolean[]> allPossible;
    private ArrayList<Integer> count;
 
    
 
    private void read() throws IOException
    {
        
        String inputFileName = "C:\\Users\\ahmed\\Desktop\\Codejam\\Input.in";
        String outputFileName = "C:\\Users\\ahmed\\Desktop\\Codejam\\Output.txt";
        in = new BufferedReader(new FileReader(inputFileName));
        out = new BufferedWriter(new FileWriter(outputFileName));
        ans = new StringBuilder();
 
        
        int nCases = Integer.parseInt(in.readLine());
        for (int cas = 1; cas <= nCases; cas++)
        {
            System.out.println(cas);
            
            tok = new StringTokenizer(in.readLine());
            c = Integer.parseInt(tok.nextToken());
            d = Integer.parseInt(tok.nextToken());
            v = Integer.parseInt(tok.nextToken());
            contains = new boolean[v + 1];
            tok = new StringTokenizer(in.readLine());
            for (int i = 0; i < d; i++)
            {
                int x = Integer.parseInt(tok.nextToken());
                contains[x] = true;
            }
 
            
            ans.append("Case #" + cas + ": " + solve() + "\n");
        }
 
        
        System.out.print(ans.toString());
        out.write(ans.toString());
        out.flush();
        out.close();
 
    }
 
    private String solve()
    {
        
        needed = v / 2 + 2;
        allPossible = new ArrayList<boolean[]>();
        count = new ArrayList<Integer>();
        add(copy(contains), 0, 0);
 
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < allPossible.size(); i++)
        {
            contains = allPossible.get(i);
 
            int c = count.get(i);
            dp = new int[v + 1][v + 1];
            for (int j = 0; j <= v; j++)
                for (int k = 0; k <= v; k++)
                    dp[j][k] = -1;
            boolean canAll = true;
            for (int j = 1; j <= v && canAll; j++)
                if (f(j, 1) == 0)
                {
                    canAll = false;
                }
            if (canAll)
                min = Math.min(min, c);
 
        }
 
        return "" + min;
    }
 
    private void add(boolean[] arr, int idx, int c)
    {
        
        if (idx > needed || idx >= arr.length)
        {
            allPossible.add(arr);
            count.add(c);
            return;
        }
        
        if (arr[idx])
        {
            add(copy(arr), idx + 1, c);
        } else
        {
            boolean yes[] = copy(arr);
            boolean no[] = copy(arr);
            yes[idx] = true;
            add(yes, idx + 1, c + 1);
            add(no, idx + 1, c);
        }
    }
 
    private boolean[] copy(boolean[] arr)
    {
        boolean result[] = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = arr[i];
        return result;
    }
 
    private int f(int x, int idx)
    {
        
        if (x == 0)
            return 1;
        if (idx >= contains.length)
            return 0;
        if (dp[x][idx] != -1)
            return dp[x][idx];
        if (contains[idx] == false)
            return f(x, idx + 1);
 
        
        boolean can = false;
        for (int k = 0; k <= c; k++)
        {
            int rem = x - k * idx;
            if (rem < 0)
                break;
            if (f(rem, idx + 1) == 1)
                can = true;
        }
 
        dp[x][idx] = can ? 1 : 0;
        return can ? 1 : 0;
    }
 
 }
