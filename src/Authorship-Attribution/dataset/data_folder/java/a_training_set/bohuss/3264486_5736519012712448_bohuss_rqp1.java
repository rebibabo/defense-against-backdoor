package codeJam2017;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class RQP1 {
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
        new RQP1(argv[0]);
    }
    
    public RQP1(String inputFile)
    {
        openInput(inputFile);
        readNextLine();
 
        int TC = NextInt();
 
        
        for(int tt=0; tt<TC; tt++)
        {   
            readNextLine();
            String s = NextString();
            int K = NextInt();
            
            String rev = new StringBuilder(s).reverse().toString();
            String output=solve(s, K);
            String output2=solve(rev, K);
            System.out.println("Case #"+(tt+1)+": "+output);
            
            if(output.compareTo(output2)!=0)
            {
                System.out.println("!!!");
                System.out.println(s+" "+K);
                break;
            }
        }
        closeInput();
    }
    
    Random rgen = new Random();
    String genStr()
    {
        int L=10+rgen.nextInt(100);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<L; i++)
            sb.append(rgen.nextBoolean()?'+':'-');
        return sb.toString();
    }
    
    int genK(int m)
    {
        return rgen.nextInt(m-1)+1;
    }
    
    private String solve(String s, int K) {
        int N = s.length();
        int ans = 0;
        boolean [] b = new boolean[N];
        int cnt=0;
        for(int i=0; i<N; i++)
        {
            b[i] = s.charAt(i)=='+';
            if(!b[i])cnt++; 
        }
 
        for(int i=0; i<=N-K; i++)
        {
            if(!b[i])
            {
                ans++;
                for(int j=0; j<K; j++)
                    b[i+j]=!b[i+j];
            }
        }
        for(int i=0; i<N; i++)
            if(!b[i])return "IMPOSSIBLE";
        return ""+ans;
    }
 
 
 }
 
 
