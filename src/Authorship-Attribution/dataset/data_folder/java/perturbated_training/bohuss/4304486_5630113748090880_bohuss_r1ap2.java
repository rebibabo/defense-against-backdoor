package codeJam2016;
 
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
        int N=NextInt();
        int [][] m = new int[N*2-1][N];
        for(int i=0; i<N*2-1; i++)
        {
            readNextLine();
            for(int j=0; j<N; j++)
                m[i][j]=NextInt();
        }
        
        int [][] p = new int[N][N];
        boolean [] used = new boolean[N*2-1];
        int retId = -1;
        boolean retLine  = false;
        for(int i=0; i<N; i++)
        {
            int min=100000;
            int minid=-1;
            int minid2=-1;
            for(int j=0; j<N*2-1; j++)
            {
                if(used[j])continue;
                if(m[j][i]<min)
                {
                    min = m[j][i];
                    minid=j;
                    minid2=-1;
                }
                else if(m[j][i]==min)
                {
                    minid2=j;
                }
            }
            boolean usedLine=true;
            for(int j=0; usedLine&&j<N; j++)
            {
                if(p[i][j]>0&&p[i][j]!=m[minid][j])usedLine=false;
                if(i>0&&p[i-1][j]>=m[minid][j])usedLine=false;
            }
            if(usedLine)
            {
                for(int j=0; j<N; j++)
                    p[i][j] = m[minid][j];
            }
            else
            {
                for(int j=0; j<N; j++)
                {
                    assert(p[j][i]==0||p[j][i]==m[minid][j]);
                    p[j][i] = m[minid][j];
                }
            }
            used[minid] = true;
            
            if(minid2>-1)
            {
                if(!usedLine)
                {
                    for(int j=0; j<N; j++)
                        p[i][j] = m[minid2][j];
                }
                else
                {
                    for(int j=0; j<N; j++)
                    {
                        assert(p[j][i]==0||p[j][i]==m[minid2][j]);
                        p[j][i] = m[minid2][j];
                    }
                }
                used[minid2] = true;
            }
            else
            {
                retId = i;
                if(usedLine)retLine = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<N; i++)
        {
            if(!retLine)sb.append(p[retId][i]+" ");
            else sb.append(p[i][retId]+" ");
        }
        
        return sb.toString();
        
    }
 
 }
 
 
