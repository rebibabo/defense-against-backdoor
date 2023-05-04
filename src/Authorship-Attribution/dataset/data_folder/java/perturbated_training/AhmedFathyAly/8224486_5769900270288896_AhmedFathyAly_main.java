import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.StringTokenizer;
 
 import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
 
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
 
    
    private int R;
    private int C;
    private int n;
    private boolean[][] g;
    private int dx[] = new int[]
    { 1, -1, 0, 0 };
    private int dy[] = new int[]
    { 0, 0, 1, -1 };
 
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
            System.out.println("case: " + cas );
            tok = new StringTokenizer(in.readLine());
            R = Integer.parseInt(tok.nextToken());
            C = Integer.parseInt(tok.nextToken());
            n = Integer.parseInt(tok.nextToken());
 
            
            ans.append("Case #" + cas + ": " + solve() + "\n");
        }
 
        
        System.out.print(ans.toString());
        out.write(ans.toString());
        out.flush();
        out.close();
 
    }
 
    private int solve()
    {
        
        int min =Integer.MAX_VALUE;
        for (int mask = 0; mask < (1 << (R * C)); mask++)
        {
            
            g = new boolean[R][C];
            int count = 0;
            for (int i = 0; i < R * C; i++)
            {
                int r = i / C;
                int c = i % C;
                if (((mask & (1 << i)) == 0))
                    g[r][c] = false;
                else
                {
                    g[r][c] = true;
                    count++;
                }
            }
            if (count != n)
                continue;
 
            
            int unhap = countUnHappiness();
            min = Math.min(min, unhap);
        }
 
        return min;
    }
 
    private int countUnHappiness()
    {
        int count = 0;
        for (int r = 0; r < R; r++)
            for (int c = 0; c < C; c++)
                if (g[r][c])
                    for (int i = 0; i < 4; i++)
                    {
                        int r2 = r + dx[i];
                        int c2 = c + dy[i];
                        if (valid(r2, c2))
                            if (g[r2][c2])
                                count++;
                        
                    }
        return count / 2;
    }
 
    private boolean valid(int r, int c)
    {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
 
    private void printG()
    {
        for (int i = 0; i < R; i++)
        {
            for (int j = 0; j < C; j++)
                if (g[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            System.out.println();
        }
        System.out.println();
 
    }
 
 }
