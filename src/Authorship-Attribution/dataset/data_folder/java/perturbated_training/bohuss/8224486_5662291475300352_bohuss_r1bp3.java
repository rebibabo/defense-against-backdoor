package codeJam2015;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1BP3 {
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
        new R1BP3(argv[0]);
    }
    
    public R1BP3(String inputFile)
    {
        openInput(inputFile);
        readNextLine();
 
        int TC = NextInt();
 
        
        for(int tt=0; tt<TC; tt++)
        {   
            
            String output = solve();
            System.out.println("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private String solve() {
        readNextLine();
        int N=NextInt();
        int p1=-1, p2=-1;
        int r1=-1, r2=-1;
        for(int i=0; i<N; i++)
        {
            readNextLine();
            int d=NextInt();
            int h=NextInt();
            int M=NextInt();
            if(i==0)
            {
                p1=d;
                r1=M;
                if(h==2)
                {
                    p2=d;
                    r2=M+1;
                }
            }
            else
            {
                p2=d;
                r2=M;
            }
            
        }
        int ret=0;
        
        if(r1>r2)
        {
            int tp=p1;
            int tr=r1;
            r1=r2;
            p1=p2;
            p2=tp;
            r2=tr;
        }
        
        if(r2>2*r1)ret=1;
        else
        {
            if(r2==2*r1&&p1==p2&&p1==0)
            {
                ret=1;
            }
        }
        
        return ""+ret;
    }
 
 }
 
 
