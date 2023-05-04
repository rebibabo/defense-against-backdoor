package codeJam2015;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1AP2 {
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
        new R1AP2(argv[0]);
    }
    
    public R1AP2(String inputFile)
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
        int B=NextInt();
        int N=NextInt();
        
        int [] m = new int[B];
        readNextLine();
        for(int i=0; i<B; i++)
            m[i]=NextInt();
        
        
        
        
        long L=0, R=1000000000000L;
        long x=(L+R)/2;
        
        while(R>L+1)
        {
            x=(L+R)/2;
            if(!test(x, m, N))L=x+1;
            else R=x;
        }
        x--;
        while(!test(x, m, N))
            x++;
        
        long sum=0;
        for(int i=0; i<m.length; i++)
            sum+=(x-1)/m[i]+1;
        long diff=N-sum;
        
        
        int ret=-1;
        
        for(int i=0; i<B; i++)
            if(x%m[i]==0)
            {
                diff--;
                if(diff==0)
                {
                    ret=i+1;
                    break;
                }
            }
        return ""+ret;
    }
 
    private boolean test(long x, int[] m, int N) {
        long sum=0;
        for(int i=0; i<m.length; i++)
            sum+=x/m[i]+1;
        return sum>=N;
    }
 
 }
 
 
