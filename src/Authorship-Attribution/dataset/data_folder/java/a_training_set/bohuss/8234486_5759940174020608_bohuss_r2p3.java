package codeJam2015;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R2P3 {
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
        new R2P3(argv[0]);
    }
    
    public R2P3(String inputFile)
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
    
    Dict dict;
    Random rgen = new Random(3);
    private String solve() {
        readNextLine();
        int N=NextInt();
        readNextLine();
        HashSet<Integer> L1 = new HashSet<Integer>();
        StringTokenizer st = new StringTokenizer(line);
        dict = new Dict();
        while(st.hasMoreTokens())
        {
            L1.add(dict.getInt(st.nextToken()));
        }
        readNextLine();
        HashSet<Integer> L2 = new HashSet<Integer>();
        StringTokenizer st2 = new StringTokenizer(line);
        while(st2.hasMoreTokens())
        {
            L2.add(dict.getInt(st2.nextToken()));
        }
        int ret=0;
        ret = getCommon(L1, L2,100000);
        if(N>2)
        {
            ret=100000;
            int M=N-2;
            Sentence [] p = new Sentence[M];
            for(int i=0; i<M; i++)
            {
                readNextLine();
                StringTokenizer stok = new StringTokenizer(line);
                p[i] = new Sentence();
                while(stok.hasMoreElements())
                {
                    String s = stok.nextToken();
                    p[i].words.add(dict.getInt(s));
                }
            }
            if(M<17)
            for(int m=0; m<1<<M; m++)
            {
                
                HashSet<Integer> LL1 = new HashSet<Integer>();
                HashSet<Integer> LL2 = new HashSet<Integer>();
                LL1.addAll(L1);
                LL2.addAll(L2);
                for(int j=0; j<M; j++)
                {
                    if((m&1<<j)>0)LL1.addAll(p[j].words);
                    else LL2.addAll(p[j].words);
                }
                int now = getCommon(LL1, LL2, ret);
                if(now<ret)
                    ret=now;
            }
            else
            {
                
                int MAX = 1<<M;
                
                MAX*=19;
                MAX/=40;
                int mm=rgen.nextInt(1<<M);
                int cnt=1000;
                int prevmm=mm;
                int nowLast=100000;
                for(int m=0; m<MAX; m++)
                {
                    prevmm=mm;
                    
                    if(cnt<=0)
                    {
                        mm=rgen.nextInt(1<<M);
                        cnt=1000;
                        nowLast=100000;
                        
                    }
                    else mm=modify(mm, M);
                    HashSet<Integer> LL1 = new HashSet<Integer>();
                    HashSet<Integer> LL2 = new HashSet<Integer>();
                    LL1.addAll(L1);
                    LL2.addAll(L2);
                    for(int j=0; j<M; j++)
                    {
                        if((mm&1<<j)>0)LL1.addAll(p[j].words);
                        else LL2.addAll(p[j].words);
                    }
                    int now = getCommon(LL1, LL2, ret);
                    if(now<nowLast)
                    {
                        nowLast=now;
                        cnt=1000;
                        if(now<ret)
                            ret=now;
                    }
                    else 
                    {
                        cnt--;
                        mm=prevmm;
                    }
                }
            }
            
        }
        
        return ""+ret;
    }
    
    private class Sentence
    {
        HashSet<Integer> words = new HashSet<Integer>();
    }
    private int getCommon(HashSet<Integer>L1,HashSet<Integer>L2, int max)
    {
        int ret=0;
        for(int a:L1)
        {
            if(L2.contains(a))ret++;
            if(ret>=max)return ret;
        }
        return ret;
    }
    
    private class Dict
    {
        HashMap<String, Integer>dict = new HashMap<String, Integer>();
        
        int getInt(String s)
        {
            if(dict.containsKey(s))return dict.get(s);
            dict.put(s, dict.size());
            return dict.get(s);
        }
    }
    
    private int modify(int x, int M)
    {
        if(rgen.nextBoolean())
        {
            int b=rgen.nextInt(M);
            int c=rgen.nextInt(M);
            return x^(1<<b)^(1<<c);
        }
            
        int b=rgen.nextInt(M);
        return x^(1<<b);
    }
 
 }
 
 
