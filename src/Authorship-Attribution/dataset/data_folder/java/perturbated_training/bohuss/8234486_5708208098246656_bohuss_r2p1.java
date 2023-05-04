package codeJam2015;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R2P1 {
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
        new R2P1(argv[0]);
    }
    
    public R2P1(String inputFile)
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
        
        char [] [] p = new char[R][C];
        int [] [] id = new int[R][C];
        int cnt=0;
        for(int i=0; i<R; i++)
            for(int j=0; j<C; j++)
                id[i][j]=-1;
        for(int i=0; i<R; i++)
        {
            readNextLine();
            for(int j=0; j<C; j++)
            {
                p[i][j] = line.charAt(j);
                if(p[i][j]=='.')continue;
                id[i][j]=cnt++;
            }
        }
        UF uf = new UF(cnt);
        
        for(int i=0; i<R; i++)
        {
            for(int j=0; j<C; j++)
            {
                int x=i, y=j;
                int dx=-1000, dy=-1000;
                switch(p[i][j])
                {
                case '<':
                    dx=0;
                    dy=-1;
                    break;
                case '>':
                    dx=0;
                    dy=1;
                    break;
                case '^':
                    dx=-1;
                    dy=0;
                    break;
                case 'v':
                    dx=1;
                    dy=0;
                    break;
                }
                do
                {
                    x+=dx;
                    y+=dy;
                    if(x>=0&&x<R&&y>=0&&y<C&&id[x][y]>-1)break;
                }while(x>=0&&x<R&&y>=0&&y<C);
                if(x>=0&&x<R&&y>=0&&y<C)
                    uf.join(id[x][y], id[i][j]);
            }
        }
        if(!poss(id))return "IMPOSSIBLE";
        HashSet <Integer> miss = new HashSet<Integer>();
        
        for(int i=0; i<R; i++)
        {
            for(int j=0; j<C; j++)
            {
                if(p[i][j]=='.')continue;
                int cl = uf.getP(id[i][j]);
                if(miss.contains(cl))continue;
                int x=i, y=j;
                int dx=-1000, dy=-1000;
                switch(p[i][j])
                {
                case '<':
                    dx=0;
                    dy=-1;
                    break;
                case '>':
                    dx=0;
                    dy=1;
                    break;
                case '^':
                    dx=-1;
                    dy=0;
                    break;
                case 'v':
                    dx=1;
                    dy=0;
                    break;
                }
                do
                {
                    x+=dx;
                    y+=dy;
                    if(x>=0&&x<R&&y>=0&&y<C&&id[x][y]>-1)break;
                }while(x>=0&&x<R&&y>=0&&y<C);
                if(x>=0&&x<R&&y>=0&&y<C)
                {
                    
                }
                else
                {
                    miss.add(cl);
                }
            }
        }
        int ret=miss.size();
        return ""+ret;
    }
    
    private boolean poss(int [] [] p)
    {
        int N=p.length;
        int M=p[0].length;
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<M; j++)
            {
                if(p[i][j]==-1)continue;
                int cnt=0;
                for(int x=0; x<N; x++)
                    if(p[x][j]>-1)cnt++;
                for(int x=0; x<M; x++)
                    if(p[i][x]>-1)cnt++;
                if(cnt==2)return false;
            }
        }
        return true;
    }
    
    private class UF
    {
        int [] p;
        int [] s;
        int N;
        int cnt;
        UF(int N)
        {
            this.N=N;
            s = new int[N];
            p = new int[N];
            for(int i=0; i<N; i++)
            {
                p[i]=i;
                s[i]=1;
            }
            cnt=N;
        }
        
        public void join(int a, int b)
        {
            
            int x=getP(a);
            int y=getP(b);
            
            if(x==y)return;
            if(s[x]>s[y])
            {
                int t=x;
                x=y;
                y=t;
            }
            p[x]=p[y];
            s[y]+=s[x];
            cnt--;
        }
        
        public int getP(int a)
        {
            if(p[a]==a)return a;
            return p[a]=getP(p[a]);
        }
        
        public int getC(int x)
        {
            return s[getP(x)];
        }
    }
    
 }
 
 
