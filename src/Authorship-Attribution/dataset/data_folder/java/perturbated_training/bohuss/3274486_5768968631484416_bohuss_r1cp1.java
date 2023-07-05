package codeJam2017;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1CP1 {
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
        new R1CP1(argv[0]);
    }
    
    public R1CP1(String inputFile)
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
        StringBuilder sb = new StringBuilder();
        readNextLine();
        int N = NextInt();
        int K = NextInt();
        double ret = 0;
        Cyl [] p = new Cyl[N];
        
        for(int i=0; i<N; i++)
        {
            readNextLine();
            p[i] = new Cyl();
            p[i].r = NextInt();
            p[i].h = NextInt();
        }
        
        
        Arrays.sort(p);
        for(int m=0; m<(1<<N); m++)
        {
            double now = get(m,p,N,K);
            ret = Math.max(ret, now);
        }
        sb.append(ret);
        return sb.toString();
        
    }
 
    private double get(int m, Cyl[] p, int N, int K) {
        if(Integer.bitCount(m)!=K)return 0;
        double ret = 0;
        boolean first = true;
        for(int i=N-1; i>=0; i--)
        {
            if((m&(1<<i))>0)
            {
                ret+=(2*Math.PI*p[i].r*p[i].h);
                if(first)
                {
                    first = false;
                    ret += Math.PI * p[i].r*p[i].r;
                }
            }
            
        }
        return ret;
    }
    
    private class Cyl implements Comparable<Cyl>
    {
        int h,r;
        public int compareTo(Cyl d)
        {
            return r - d.r;
        }
    }
 }
 
 
