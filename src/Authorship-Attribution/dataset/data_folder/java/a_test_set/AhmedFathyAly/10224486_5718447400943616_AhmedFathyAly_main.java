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
 
    
    InputReader in;
    StringTokenizer tok;
    StringBuilder ans;
 
    
    int n;
    boolean[][] originalG, g;
    ArrayList<ArrayList<Integer>> perms;
    private ArrayList<Integer> currentPermutation;
 
    public static void main(String[] args) throws Exception
    {
        Main sol = new Main();
        sol.begin();
 
    }
 
    private void begin() throws IOException
    {
        
        boolean file = true;
        if (file)
            in = new InputReader(new FileInputStream("D-small-attempt1.in"));
        else
            in = new InputReader(System.in);
        ans = new StringBuilder();
 
        
        int nCases = in.nextInt();
        for (int cas = 1; cas <= nCases; cas++)
        {
            n = in.nextInt();
            originalG = new boolean[n][n];
            for (int worker = 0; worker < n; worker++)
            {
                String str = in.next();
                for (int c = 0; c < str.length(); c++)
                    if (str.charAt(c) == '1')
                        originalG[worker][c] = true;
            }
 
            ans.append("Case #" + cas + ": ");
            ans.append(solve());
            ans.append("\n");
        }
 
        
        System.out.println("\nOutput: ");
        System.out.println(ans.toString());
        BufferedWriter out = new BufferedWriter(new FileWriter("output.txt"));
        out.write(ans.toString());
        out.close();
 
        
        System.out.println("\nTest: ");
    }
 
    private int solve()
    {
        
        perms = new ArrayList<>();
        generate(new ArrayList<Integer>());
 
        
        int maskLength = 1 << (n * n);
        int minCost = Integer.MAX_VALUE;
        for (int mask = 0; mask < maskLength; mask++)
        {
            g = new boolean[n][n];
            int cost = 0;
            boolean ignore = false;
            for (int worker = 0; worker < n; worker++)
                for (int machine = 0; machine < n; machine++)
                {
                    boolean bit = getBit(mask, worker, machine);
                    if (originalG[worker][machine] && bit)
                        ignore = true;
                    if (!originalG[worker][machine] && bit)
                        cost++;
                    g[worker][machine] = originalG[worker][machine] || bit;
                }
 
            if (!ignore )
                if (valid())
                    minCost = Math.min(cost, minCost);
        }
 
        return minCost;
    }
 
    private boolean valid()
    {
        
        for (ArrayList<Integer> permutation : perms)
        {
            currentPermutation = permutation;
            if (!valid(0, 0))
                return false;
        }
        return true;
    }
 
    private boolean valid(int workerIdx, int machineMask)
    {
        
        if (workerIdx == n)
            return true;
 
        
        boolean found = false;
        int worker = currentPermutation.get(workerIdx);
        for (int machine = 0; machine < n; machine++)
            if (g[worker][machine] && ((machineMask & (1 << machine)) == 0))
            {
                boolean can = valid(workerIdx + 1, machineMask | (1 << machine));
                if (can)
                    found = true;
                else
                    return false;
            }
 
        return found;
    }
 
    private boolean getBit(int mask, int worker, int machine)
    {
        int pos = worker * n + machine;
        return (mask & (1 << pos)) != 0;
    }
 
    private void generate(ArrayList<Integer> l)
    {
        
        if (l.size() == n)
        {
            perms.add(l);
            return;
        }
 
        for (int i = 0; i < n; i++)
            if (!l.contains(i))
            {
                ArrayList<Integer> newL = new ArrayList<>(l);
                newL.add(i);
                generate(newL);
            }
 
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