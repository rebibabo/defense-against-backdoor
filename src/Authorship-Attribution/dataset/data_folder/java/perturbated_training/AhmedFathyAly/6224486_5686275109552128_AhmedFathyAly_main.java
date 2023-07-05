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
 import java.util.PriorityQueue;
 import java.util.Stack;
 import java.util.StringTokenizer;
 
 import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
 
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
            
            n = Integer.parseInt(in.readLine().trim());
            arr = new int[n];
            tok = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(tok.nextToken());
 
            
            ans.append("Case #" + cas + ": " + solve() + "\n");
 
        }
        
        
        System.out.print(ans.toString());
        out.write(ans.toString());
        out.flush();
        out.close();
    }
 
 
 
    private int solve()
    {
        
        int max = Integer.MIN_VALUE;
        for (int i : arr)
            max = Math.max(max, i);
        int ans = max;
        for (int maxLeft = 1; maxLeft <= max; maxLeft++)
        {
            int sum = maxLeft;
            for (int x : arr)
            {
                int parts = x / maxLeft;
                if (parts * maxLeft < x)
                    parts ++;
                sum += parts - 1;
            }
            ans = Math.min(ans, sum);
        }
        return ans;
    }
 
 }
