package codeJam2017;
 
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
            System.out.print("Case #"+(tt+1)+":\n"+output);
        }
        closeInput();
    }
    
    private String solve() {
        readNextLine();
        int R = NextInt();
        int C = NextInt();
        
        char [] [] p = new char[R][C];
        for(int i=0; i<R; i++)
        {
            readNextLine();
            for(int j=0; j<C; j++)
                p[i][j] = line.charAt(j);
        }
        return solve(R, C, p);
        
    }
    
    private String solve(int R, int C, char[][] p) {
        StringBuilder sb = new StringBuilder();
        
        int [] u  = new int[C];
        
        for(int i=0; i<R; i++)
        {
            for(int j=0; j<C; j++)
            {
                if(p[i][j]!='?')u[j]++;
            }
        }
        
        int startC = 0;
        
        
        for(int j=0; j<C; j++)
        {
            int startR = 0;
            if(u[j]==0)continue;
            char f='?';
            for(int i=0; i<R; i++)
            {
                if(p[i][j]!='?')f=p[i][j];
                if(f!='?')
                {
                    for(int r=startR; r<=i; r++)
                        for(int k=startC; k<=j; k++)
                            if(p[r][k]=='?')p[r][k] = f;
                    startR = i+1;
                }
                    
            }
            startC = j+1;
        }
        
        for(int i=0; i<R; i++)
        {
            for(int j=1; j<C; j++)
            {
                if(p[i][j]=='?')
                    p[i][j] = p[i][j-1];
            }
        }
        
        
        for(int i=0; i<R; i++)
        {
            for(int j=0; j<C; j++)
            {
                sb.append(p[i][j]);
            }
            sb.append("\n");
        }
        
        
        return sb.toString();
    }
 
    
 }
 
 
