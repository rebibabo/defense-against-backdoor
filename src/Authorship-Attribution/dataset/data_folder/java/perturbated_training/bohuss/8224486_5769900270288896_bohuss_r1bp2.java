package codeJam2015;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1BP2 {
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
        new R1BP2(argv[0]);
    }
    
    public R1BP2(String inputFile)
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
        int R=NextInt();
        int C=NextInt();
        int N=NextInt();
        
        if(R<C)
        {
            int t=R;
            R=C;
            C=t;
        }
        if(C==1)
        {
            boolean [] p = new boolean[R];
            for(int i=0; i<R; i+=2)
            {
                p[i]=true;
                N--;
            }
            int ret=0;
            if(N>0)
            {
                for(int i=1; i<R; i+=2)
                {
                    p[i]=true;
                    N--;
                    ret+=2;
                    if(R%2==0&&i==1)ret--;
                    if(N<=0)break;
                }
            }
            return ret+"";
        }
        int ret2=solve2(R, C, N);
        boolean [] [] p = new boolean [R][C];
        for(int i=0; i<R; i++)
        {
            int j=0;
            if(i%2==0)j++;
            for(;j<C; j+=2)
            {
                p[i][j] = true;
                N--;
            }
        }
        int [] r2 = {0, R-1, 0, R-1};
        int [] c2 = {0, 0, C-1, C-1};
        int ret=0;
        if(N>0)
        for(int d=0; d<4; d++)
        {
            if(!p[r2[d]][c2[d]])
            {
                p[r2[d]][c2[d]]=true;
                ret+=2;
                N--;
                if(N<=0)break;
            }
        }
        
        if(N>0)
            for(int c=0; c<C; c++)
            {
                if(!p[0][c])
                {
                    p[0][c]=true;
                    ret+=3;
                    N--;
                    if(N<=0)break;
                }
            }
        if(N>0)
            for(int c=0; c<C; c++)
            {
                if(!p[R-1][c])
                {
                    p[R-1][c]=true;
                    ret+=3;
                    N--;
                    if(N<=0)break;
                }
            }
        if(N>0)
            for(int r=0; r<R; r++)
            {
                if(!p[r][0])
                {
                    p[r][0]=true;
                    ret+=3;
                    N--;
                    if(N<=0)break;
                }
            }
        if(N>0)
            for(int r=0; r<R; r++)
            {
                if(!p[r][C-1])
                {
                    p[r][C-1]=true;
                    ret+=3;
                    N--;
                    if(N<=0)break;
                }
            }
        if(N>0)
            ret+=N*4;
        ret = Math.min(ret, ret2);
        return ""+ret;
    }
    
    private int solve2(int R, int C, int N) {
        
        boolean [] [] p = new boolean [R][C];
        for(int i=0; i<R; i++)
        {
            int j=0;
            if(i%2==1)j++;
            for(;j<C; j+=2)
            {
                p[i][j] = true;
                N--;
            }
        }
        int [] r2 = {0, R-1, 0, R-1};
        int [] c2 = {0, 0, C-1, C-1};
        int ret=0;
        if(N>0)
        for(int d=0; d<4; d++)
        {
            if(!p[r2[d]][c2[d]])
            {
                p[r2[d]][c2[d]]=true;
                ret+=2;
                N--;
                if(N<=0)break;
            }
        }
        
        if(N>0)
            for(int c=0; c<C; c++)
            {
                if(!p[0][c])
                {
                    p[0][c]=true;
                    ret+=3;
                    N--;
                    if(N<=0)break;
                }
            }
        if(N>0)
            for(int c=0; c<C; c++)
            {
                if(!p[R-1][c])
                {
                    p[R-1][c]=true;
                    ret+=3;
                    N--;
                    if(N<=0)break;
                }
            }
        if(N>0)
            for(int r=0; r<R; r++)
            {
                if(!p[r][0])
                {
                    p[r][0]=true;
                    ret+=3;
                    N--;
                    if(N<=0)break;
                }
            }
        if(N>0)
            for(int r=0; r<R; r++)
            {
                if(!p[r][C-1])
                {
                    p[r][C-1]=true;
                    ret+=3;
                    N--;
                    if(N<=0)break;
                }
            }
        if(N>0)
            ret+=N*4;
        return ret;
    }
 
 }
 
 
