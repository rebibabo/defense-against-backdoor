package codeJam2017;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class RQP4 {
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
        new RQP4(argv[0]);
    }
    
    public RQP4(String inputFile)
    {
        openInput(inputFile);
        readNextLine();
 
        int TC = NextInt();
 
        
        for(int tt=0; tt<TC; tt++)
        {   
            String output=solve();
            System.out.print("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private String solve() {
        readNextLine();
        int N = NextInt();
        int M = NextInt();
        char [] [] p = new char [N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                p[i][j] = '.';
        int oPos=-1;
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i=0; i<M; i++)
        {
            readNextLine();
            char c = NextString().charAt(0);
            
            int R = NextInt() - 1;
            int C = NextInt() - 1;
            
            if(c=='x')
            {
                c='o';
                cnt++;
                sb.append("o "+(R+1)+" "+(C+1)+"\n");
            }
            p[R][C] = c;
            if(c=='o')oPos = C;
        }
        if(oPos==-1)
        {
            oPos = 0;
            p[0][0] = 'o';
            cnt++;
            sb.append("o 1 1\n");
        }
        for(int i=0; i<N; i++)
        {
            if(p[0][i]!='+'&&p[0][i]!='o')
            {
                p[0][i]='+';
                cnt++;
                sb.append("+ 1 "+(i+1)+"\n");
            }
        }
        for(int i=1; i<N-1; i++)
        {
            p[N-1][i]='+';
            cnt++;
            sb.append("+ " + (N) + " "+(i+1)+"\n");
        }
        for(int i=1; i<N; i++)
        {
            
            int xPos = i;
            
            if(xPos==oPos)
            {
                xPos = 0;
            }
            p[i][xPos]='x';
            cnt++;
            sb.append("x " + (i+1) + " "+(xPos+1)+"\n");
        }
        
        if(N>1)
            sb.insert(0, (N*3-2)+" "+cnt+"\n");
        else
            sb.insert(0, 2+" "+cnt+"\n");
        return sb.toString();
    }
 
 }
 
 
