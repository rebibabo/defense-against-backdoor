import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;
 
 public class Main
 {
 
    
    InputReader in;
    StringTokenizer tok;
    StringBuilder ans;
 
    
    int n, r, p, s;
    private String best;
 
    public static void main(String[] args) throws Exception
    {
        Main sol = new Main();
        sol.begin();
 
    }
 
    private void begin() throws IOException
    {
        
        boolean file = true;
        if (file)
            in = new InputReader(new FileInputStream("A-small-attempt0 (1).in"));
        else
            in = new InputReader(System.in);
        ans = new StringBuilder();
 
        
        int nCases = in.nextInt();
        for (int cas = 1; cas <= nCases; cas++)
        {
            n = in.nextInt();
            r = in.nextInt();
            p = in.nextInt();
            s = in.nextInt();
 
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
 
        System.out.println(valid("PSRS"));
    }
 
    private Object solve()
    {
        best = "";
        f("", r, p, s);
        return best.length() > 0 ? best : "IMPOSSIBLE";
    }
 
    private void f(String str, int r, int p, int s)
    {
        
        if (r == 0 && p == 0 && s == 0)
        {
            if (valid(str))
                if (best.length() == 0 || str.compareTo(best) < 0)
                    best = str;
            return;
        }
 
        if (r > 0)
            f(str + "R", r - 1, p, s);
        if (p > 0)
            f(str + "P", r, p - 1, s);
        if (s > 0)
            f(str + "S", r, p, s - 1);
    }
 
    private boolean valid(String str)
    {
        
        String current = str + "";
        while (current.length() > 1)
        {
            StringBuilder strb = new StringBuilder();
            int i = 0;
            while (i + 1 < current.length())
            {
                strb.append("" + compete(current.charAt(i), current.charAt(i + 1)));
                i += 2;
            }
            current = strb.toString();
            if (current.contains("X"))
                return false;
        }
 
        return true;
    }
 
    private char compete(char a, char b)
    {
        if ((a == 'R' && b == 'P') || (a == 'P' && b == 'R'))
            return 'P';
        else if ((a == 'R' && b == 'S') || (a == 'S' && b == 'R'))
            return 'R';
        else if ((a == 'P' && b == 'S') || (a == 'S' && b == 'P'))
            return 'S';
        else
            return 'X';
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