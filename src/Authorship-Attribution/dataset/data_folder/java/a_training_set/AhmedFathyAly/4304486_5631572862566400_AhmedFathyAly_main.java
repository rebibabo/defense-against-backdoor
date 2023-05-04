import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.StringTokenizer;
 
 public class Main
 {
 
    
    InputReader in;
    StringTokenizer tok;
    StringBuilder ans;
    private int n;
    private int[] bff;
    private int max;
 
    
 
    public static void main(String[] args) throws Exception
    {
        Main sol = new Main();
        sol.begin();
 
    }
 
    private void begin() throws IOException
    {
        
        boolean file = true;
        if (file)
            in = new InputReader(new FileInputStream("C-small-attempt0.in"));
        else
            in = new InputReader(System.in);
        ans = new StringBuilder();
 
        
        int nCases = in.nextInt();
        for (int cas = 1; cas <= nCases; cas++)
        {
            System.out.println(cas);
            
            n = in.nextInt();
            bff = new int[n];
            for (int i = 0; i < n; i++)
                bff[i] = in.nextInt() - 1;
 
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
        max = 0;
        f(new ArrayList<Integer>());
        return max;
 
    }
 
    private void f(ArrayList<Integer> l)
    {
        
        if (valid(l))
            max = Math.max(max, l.size());
        if(!validInTheMiddle(l))
            return;
 
        
        for (int i = 0; i < n; i++)
            if (!l.contains(i))
            {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.addAll(l);
                newList.add(i);
                f(newList);
            }
 
    }
 
    private boolean validInTheMiddle(ArrayList<Integer> l)
    {
        for (int i = 1; i < l.size() - 1; i++)
        {
            int x = l.get(i);
            int right = l.get((i + 1) % l.size());
            int left = l.get((i - 1 + l.size()) % l.size());
            if (!(bff[x] == right || bff[x] == left))
                return false;
        }
        return true;
    }
 
    private boolean valid(ArrayList<Integer> l)
    {
        for (int i = 0; i < l.size(); i++)
        {
            int x = l.get(i);
            int right = l.get((i + 1) % l.size());
            int left = l.get((i - 1 + l.size()) % l.size());
            if (!(bff[x] == right || bff[x] == left))
                return false;
        }
        return true;
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