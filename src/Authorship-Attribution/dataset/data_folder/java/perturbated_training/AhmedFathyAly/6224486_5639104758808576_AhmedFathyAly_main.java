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
    private int n;
    private int[] arr;
 
    
 
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
            n = Integer.parseInt(tok.nextToken()) + 1;
            arr = new int[n];
            String str = tok.nextToken().trim();
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(str.charAt(i) + "");
 
            
            ans.append("Case #" + cas + ": " + solve() + "\n");
 
        }
 
        
        System.out.print(ans.toString());
        out.write(ans.toString());
        out.flush();
        out.close();
    }
 
    private int solve()
    {
        int countUp = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) 
            if (arr[i] == 0)
                continue;
            else
            {
                
                if (i > countUp)
                {
                    int required = i - countUp;
                    countUp += required;
                    ans += required;
                }
                
                
                countUp += arr[i];
            }
            
        return ans;
    }
 
 }
