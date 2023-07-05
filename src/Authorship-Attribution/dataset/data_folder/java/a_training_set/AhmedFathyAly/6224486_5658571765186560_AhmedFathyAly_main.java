import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.LinkedList;
 import java.util.Queue;
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
 
    
    private ArrayList<Mask> allMasks;
    private int n;
    private int r;
    private int c;
    private int[] dp;
    static int dx[] = new int[]
    { -1, 1, 0, 0 };
    static int dy[] = new int[]
    { 0, 0, -1, 1 };
 
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
            tok = new StringTokenizer(in.readLine());
            n = Integer.parseInt(tok.nextToken());
            r = Integer.parseInt(tok.nextToken());
            c = Integer.parseInt(tok.nextToken());
            ans.append("Case #" + cas + ": " + solve() + "\n");
        }
 
        
        System.out.print(ans.toString());
        out.write(ans.toString());
        out.flush();
        out.close();
 
    }
 
    private String solve()
    {
        
        dp = new int[1 << (r * c + 2)];
        Arrays.fill(dp, -1);
 
        
        generateMasks();
        ArrayList<Mask> canMasks = new ArrayList<>();
        ArrayList<Mask> cantMasks = new ArrayList<>();
 
        for (Mask mask : allMasks)
            if (canWin(mask))
                canMasks.add(mask);
            else
                cantMasks.add(mask);
 
 
        for (Mask mask : cantMasks)
        {
            boolean canSubsitue = false;
            for (Mask other : canMasks)
                if (areSame(mask, other))
                    canSubsitue = true;
            if (canSubsitue == false)
            {
                return "RICHARD";
            }
        }
 
        return "GABRIEL";
 
    }
 
    private boolean areSame(Mask mask1, Mask mask2)
    {
        
        int minR = n;
        int minC = n;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (mask1.m[i][j])
                {
                    minR = Math.min(minR, i);
                    minC = Math.min(minC, j);
                }
        Mask x = new Mask(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (mask1.m[i][j])
                    x.m[i - minR][j - minC] = true;
        minR = n;
        minC = n;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (mask2.m[i][j])
                {
                    minR = Math.min(minR, i);
                    minC = Math.min(minC, j);
                }
        Mask y = new Mask(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (mask2.m[i][j])
                    y.m[i - minR][j - minC] = true;
 
        
        int maxR = 0;
        int maxC = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (x.m[i][j] || y.m[i][j])
                {
                    maxR = Math.max(maxR, i);
                    maxC = Math.max(maxC, j);
                }
 
        
        boolean can = true;
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                if (x.m[r][c] != y.m[c][maxC - r])
                    can = false;
        if (can)
            return true;
 
        
        can = true;
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                if (x.m[r][c] != y.m[r][maxC - c])
                    can = false;
        if (can)
            return true;
 
        
        can = true;
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                if (x.m[r][c] != y.m[maxR - c][r])
                    can = false;
        if (can)
            return true;
 
        
        can = true;
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                if (x.m[r][c] != y.m[r][c])
                    can = false;
        if (can)
            return true;
 
        
        Mask mirrorY = new Mask(n);
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                mirrorY.m[r][c] = y.m[c][r];
 
        
        can = true;
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                if (x.m[r][c] != mirrorY.m[c][maxC - r])
                    can = false;
        if (can)
            return true;
 
        
        can = true;
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                if (x.m[r][c] != mirrorY.m[r][maxC - c])
                    can = false;
        if (can)
            return true;
 
        
        can = true;
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                if (x.m[r][c] != mirrorY.m[maxR - c][r])
                    can = false;
        if (can)
            return true;
 
        
        can = true;
        for (int r = 0; r < maxR; r++)
            for (int c = 0; c < maxC; c++)
                if (x.m[r][c] != mirrorY.m[r][c])
                    can = false;
        if (can)
            return true;
 
        return false;
    }
 
    private boolean canWin(Mask mask)
    {
        
        boolean can = false;
        Grid grid = new Grid(r, c);
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (grid.canPut(mask, i, j))
                {
                    Grid newG = grid.copy();
                    newG.applyMask(mask, i, j);
                    can |= canFill(newG);
                }
 
        return can;
    }
 
    private boolean canFill(Grid g)
    {
        
        int hash = g.getHash();
        if (g.count == g.r * g.c)
            return true;
        if (dp[hash] != -1)
            return dp[hash] == 1;
 
        
        boolean can = false;
        for (Mask mask : allMasks)
            for (int r = 0; !can && r < g.r; r++)
                for (int c = 0; !can && c < g.c; c++)
                    if (g.canPut(mask, r, c))
                    {
                        Grid gNew = g.copy();
                        gNew.applyMask(mask, r, c);
                        can |= canFill(gNew);
                    }
 
        if (can)
            dp[hash] = 1;
        else
            dp[hash] = 0;
        return can;
    }
 
    private void generateMasks()
    {
        allMasks = new ArrayList<Mask>();
        Mask start = new Mask(n);
        addMask(start, 0, 0);
    }
 
    private void addMask(Mask mask, int i, int count)
    {
        
        if (i == n * n)
        {
            if (count == n)
                allMasks.add(mask);
            return;
        }
 
        
        boolean found = count == 0;
        for (int j = 0; j < 4 && !found; j++)
        {
            int r = (i / n) + dx[j];
            int c = (i % n) + dy[j];
            if (r >= 0 && r < n && c >= 0 && c < n)
                if (mask.m[r][c])
                    found = true;
        }
 
        
        if (found)
        {
            Mask trueMask = mask.copy();
            trueMask.m[i / n][i % n] = true;
            addMask(trueMask, i + 1, count + 1);
        }
        Mask falseMask = mask.copy();
        falseMask.m[i / n][i % n] = false;
        addMask(falseMask, i + 1, count);
 
    }
 }
 
 class Grid
 {
    int r, c;
    boolean m[][];
    int count;
 
    public Grid(int r, int c)
    {
        this.r = r;
        this.c = c;
        this.m = new boolean[r][c];
        this.count = 0;
    }
 
    public void applyMask(Mask mask, int i, int j)
    {
        for (int r = 0; r < mask.n; r++)
            for (int c = 0; c < mask.n; c++)
                if (mask.m[r][c])
                {
                    this.m[r + i][c + j] = true;
                    this.count++;
                }
    }
 
    public boolean canPut(Mask mask, int i, int j)
    {
        for (int r = 0; r < mask.n; r++)
            for (int c = 0; c < mask.n; c++)
                if (mask.m[r][c])
                {
                    if (r + i >= this.r || c + j >= this.c
                            || (this.m[r + i][c + j] && mask.m[r][c]))
                        return false;
                }
        return true;
    }
 
    public Grid copy()
    {
        Grid copy = new Grid(r, c);
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                copy.m[i][j] = m[i][j];
        copy.count = this.count;
        return copy;
    }
 
    public int getHash()
    {
        int hash = 0;
        int pow = 1;
        for (int i = 0; i < r * c; i++)
        {
            if (m[i / c][i % c])
                hash += pow;
            pow *= 2;
        }
        return hash;
    }
 
    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
                if (m[i][j])
                    strb.append("x ");
                else
                    strb.append("o ");
            strb.append("\n");
        }
        return strb.toString();
 
    }
 }
 
 class Mask
 {
    int n;
    boolean m[][];
 
    public Mask(int n)
    {
        this.n = n;
        this.m = new boolean[n][n];
    }
 
    public Mask copy()
    {
        Mask copy = new Mask(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                copy.m[i][j] = m[i][j];
        return copy;
    }
 
    public String toString()
    {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
                if (m[i][j])
                    strb.append("x ");
                else
                    strb.append("o ");
            strb.append("\n");
        }
        return strb.toString();
 
    }
 }
