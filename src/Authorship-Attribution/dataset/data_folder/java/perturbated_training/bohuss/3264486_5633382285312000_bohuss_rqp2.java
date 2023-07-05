package codeJam2017;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class RQP2 {
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
        new RQP2(argv[0]);
    }
    
    public RQP2(String inputFile)
    {
        openInput(inputFile);
        readNextLine();
 
        int TC = NextInt();
 
        
        for(int tt=0; tt<TC; tt++)
        {   
            readNextLine();
            long N = NextLong();
            long output=solve(N);
            System.out.println("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private long solve(long N) {
        String s = ""+N;
        int wp = getWrongPos(s);
        if(wp==-1)return N;
        int L = s.length();
        int [] a = new int[L];
        for(int i=0; i<L; i++)
            a[i] = s.charAt(i)-'0';
        a[wp]--;
        for(int i=wp+1; i<L; i++)
            a[i]=9;
        StringBuilder sb = new StringBuilder();
        for(int x:a)
            sb.append(x);
        return solve(Long.parseLong(sb.toString()));
    }
    
    int getWrongPos(String s)
    {
        int L = s.length();
        for(int i=0; i<L-1; i++)
            if(s.charAt(i+1)<s.charAt(i))return i;
        return -1;
    }
 
 }
 
 
