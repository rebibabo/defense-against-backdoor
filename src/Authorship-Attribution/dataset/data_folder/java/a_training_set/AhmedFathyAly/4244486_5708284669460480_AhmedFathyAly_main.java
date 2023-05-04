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
    private int k;
    private int l;
    private int s;
    private String keyboard;
    private String target;
    private ArrayList<String> allWords;
 
    
 
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
            k = Integer.parseInt(tok.nextToken());
            l = Integer.parseInt(tok.nextToken());
            s = Integer.parseInt(tok.nextToken());
            keyboard = in.readLine().trim();
            target = in.readLine().trim();
 
            
            ans.append("Case #" + cas + ": " + solve() + "\n");
 
        }
 
        
        System.out.print(ans.toString());
        out.write(ans.toString());
        out.flush();
        out.close();
 
    }
 
    private String solve()
    {
        
        allWords = new ArrayList<>();
        generateWords("");
 
        
        int[] occ = new int[allWords.size()];
        for (int i = 0; i < allWords.size(); i++)
            occ[i] = nOccurences(allWords.get(i));
 
        
        long total = 0;
        int max = 0;
        for (int x : occ) 
        {
            max = Math.max(max, x);
            total += x;
        }
        double expected = 1.0 * total / allWords.size();
        return "" + (max - expected);
    }
 
    private int nOccurences(String str)
    {
        int count = 0;
        for (int i = 0; i + target.length() <= str.length(); i++)
            if (str.subSequence(i, i + target.length()).equals(target))
                count++;
        return count;
    }
 
    private void generateWords(String str)
    {
        
        if (str.length() == s)
        {
            allWords.add(str);
            return;
        }
 
        
        for (int i = 0; i < keyboard.length(); i++)
            generateWords(str + keyboard.charAt(i));
 
    }
 
 }
