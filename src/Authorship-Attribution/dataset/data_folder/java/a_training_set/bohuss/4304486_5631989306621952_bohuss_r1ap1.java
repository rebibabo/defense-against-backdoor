package codeJam2016;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1AP1 {
    String line;
    StringTokenizer inputParser;
    BufferedReader is;
    FileInputStream fstream;
    DataInputStream in;
    
    void openInput(String file)
    {
 
        
        try{
            fstream = new FileInputStream(file);
            in = new DataInputStream(fstream);
            is = new BufferedReader(new InputStreamReader(in));
        }catch(Exception e)
        {
            System.err.println(e);
        }
 
    }
    
    void readNextLine()
    {
        try {
            line = is.readLine();
            inputParser = new StringTokenizer(line, " ");
            
        } catch (IOException e) {
            System.err.println("Unexpected IO ERROR: " + e);
        }   
        
    }
    
    int NextInt()
    {
        String n = inputParser.nextToken();
        int val = Integer.parseInt(n);
        
        
        return val;
    }
    
    long NextLong()
    {
        String n = inputParser.nextToken();
        long val = Long.parseLong(n);
        
        
        return val;
    }
    
    String NextString()
    {
        String n = inputParser.nextToken();
        return n;
    }
    
    void closeInput()
    {
        try {
            is.close();
        } catch (IOException e) {
            System.err.println("Unexpected IO ERROR: " + e);
        }
            
    }
    
    public static void main(String [] argv)
    {
        new R1AP1(argv[0]);
    }
    
    public R1AP1(String inputFile)
    {
        openInput(inputFile);
        readNextLine();
 
        int TC = NextInt();
 
        
        for(int tt=0; tt<TC; tt++)
        {   
            String output=solve();
            System.out.println("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private String solve() {
        readNextLine();
        String s = line;
        
        return solve(s);
        
    }
    
    private String solve(String s){
        if(s.length()<=1)return s;
        int N=s.length();
        int maxid = -1;
        char max = 0;
        for(int i=0; i<N; i++)
        {
            if(s.charAt(i)>=max)
            {
                max = s.charAt(i);
                maxid=i;
            }
        }
        return max + solve(s.substring(0, maxid)) + s.substring(maxid + 1);
    }
 
 }
 
 
