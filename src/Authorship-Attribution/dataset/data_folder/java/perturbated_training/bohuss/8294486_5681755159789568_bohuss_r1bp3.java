package codeJam2017;
 
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
            
            String output=solve();
            System.out.println("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private String solve() {
        StringBuilder sb = new StringBuilder();
        readNextLine();
        int N=NextInt();
        int Q=NextInt();
        int [] E = new int[N];
        int [] S = new int[N];
        for(int i=0; i<N; i++)
        {
            readNextLine();
            E[i] = NextInt();
            S[i] = NextInt();
        }
        
        int [] [] d = new int[N][N];
        
        int [] D = new int[N-1];
        long [] DD = new long[N];
        
        for(int i=0; i<N; i++)
        {
            readNextLine();
            for(int j=0; j<N; j++)
            {
                d[i][j] = NextInt();
            }
            if(i+1<N)
            {
                D[i] = d[i][i+1];
                DD[i+1] = DD[i]+D[i];
            }
            
        }
        
        for(int q = 0; q<Q; q++)
        {
            readNextLine();
            int s = NextInt();
            int t = NextInt();
            
            double [] [] p = new double[N][N];
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++)
                    p[i][j] = 1e100;
            p[0][0] = 0;
            
            for(int i=1; i<N; i++)
            {
                for(int j=0; j<i; j++)
                {
                    if(E[j]>=DD[i]-DD[j])
                    {
                        double min = 1e100;
                        if(j==0)min = 0;
                        for(int k=0; k<j; k++)
                        {
                            min = Math.min(min, p[j][k]);
                        }
                        min += (DD[i] - DD[j]) / (double) S[j];
                        p[i][j] = min;
                    }
                    
                }
            }
            
            double res = 1e100;
            for(int i=0; i<N; i++)
                res = Math.min(res, p[N-1][i]);
            sb.append(res);
        }
 
        return sb.toString();
        
    }
    
 
 
 
 }
 
 
