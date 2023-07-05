package codeJam2017;
 
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
            
            int output=solve();
            System.out.println("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private int solve() {
        readNextLine();
        int N=NextInt();
        int P=NextInt();
        int [] R = new int[N];
        readNextLine();
        for(int i=0; i<N; i++)
            R[i] = NextInt()*10;
        int [] [] p = new int[N][P];
        
        for(int i=0; i<N; i++)
        {
            readNextLine();
            for(int j=0; j<P; j++)
                p[i][j] = NextInt()*10;
        }
        
        int ret = 0;
        
        Poss [] [] poss = new Poss[N][P];
        
        for(int i=0; i<N; i++)
        {
            for(int j=0; j<P; j++)
            {
                int c = p[i][j] / R[i];
                int d = R[i] - R[i] / 10;
                int u = R[i] + R[i] / 10;
                poss[i][j] = new Poss();
                if(c>0&&p[i][j]>=d*c&&p[i][j]<=u*c)
                    poss[i][j].add(c);
                int bc = c;
                do
                {
                    c++;
                    if(c>0&&p[i][j]>=d*c&&p[i][j]<=u*c)
                        poss[i][j].add(c);
                }while(c>0&&p[i][j]>=d*c&&p[i][j]<=u*c);
                c = bc;
                do
                {
                    c--;
                    if(c>0&&p[i][j]>=d*c&&p[i][j]<=u*c)
                        poss[i][j].add(c);
                }while(c>0&&p[i][j]>=d*c&&p[i][j]<=u*c);
            }
        }
        
        HashSet<Poss>notEmpty = new HashSet<R1AP2.Poss>();
        for(int i=0; i<N; i++)
            for(int j=0; j<P; j++)
                if(!poss[i][j].p.isEmpty())notEmpty.add(poss[i][j]);
 
        for(int e = 0; e<5000000; e++)
        {
            int res = 0;
            
            for(Poss x:notEmpty)
                    x.reset();
            
            HashSet <Integer> seen = new HashSet<Integer>();
            for(int i=0; i<P; i++)
            {
                int now = poss[0][i].val;
                if(seen.contains(now))continue;
                seen.add(now);
                if(now==-1)continue;
                int cnt = 0;
                for(int j=0; j<P; j++)
                {
                    if(poss[0][j].val==now)cnt++;
                }
                for(int j=1; j<N; j++)
                {
                    int cntnow = 0;
                    for(int k=0; k<P; k++)
                        if(poss[j][k].val==now)cntnow++;
                    cnt = Math.min(cnt, cntnow);
                }
                res += cnt;
            }
            ret = Math.max(ret, res);
        }
        
        return ret;
        
    }
    Random r = new Random(3);
    private class Poss
    {
        ArrayList <Integer> p = new ArrayList<Integer>();
        int val = -1;
        public void add(int x)
        {
            p.add(x);
        }
        
        public void reset()
        {
            if(p.isEmpty())return;
            val = p.get(r.nextInt(p.size()));
        }
        
        
    }
 
 }
 
 
