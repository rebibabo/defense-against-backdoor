package codeJam2017;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1CP2 {
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
        new R1CP2(argv[0]);
    }
    
    public R1CP2(String inputFile)
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
        int AC = NextInt();
        int AJ = NextInt();
        
        int [] sa = new int[AC];
        int [] fa = new int[AC];
        
        for(int i=0; i<AC; i++)
        {
            readNextLine();
            sa[i] = NextInt();
            fa[i] = NextInt();
        }
        
        int [] sb = new int[AJ];
        int [] fb = new int[AJ];
        
        for(int i=0; i<AJ; i++)
        {
            readNextLine();
            sb[i] = NextInt();
            fb[i] = NextInt();
        }
        
        
        
        if(AJ<AC)
        {
            int [] tmp = sa;
            sa = sb;
            sb = tmp;
            
            tmp = fa;
            fa = fb;
            fb = tmp;
            
            int x = AC;
            AC = AJ;
            AJ = x;
        }
        
        int ret2 = solve2(sa,fa,sb,fb);
        if(ret2>=0)return ret2;
        int ret = 78;
        if(AC==1 && AJ==1)
        {
            
            
            ret= 2;
        }
        if(AC==0 && AJ==1)
        {
            
            ret= 2;
        }
        
        if(AC==0 && AJ==2)
        {
            if(Math.max(fb[0], fb[1])-Math.min(sb[0], sb[1])<720)ret= 2;
            else if(Math.max(sb[0], sb[1])-Math.min(fb[0], fb[1])>720)ret= 2;
            else ret= 4;            
        }
        
        return ret;
    }
 
    private int solve2(int[] sa, int[] fa, int[] sb, int[] fb) {
        int N = sa.length;
        int M = sa.length;
        if(can2(sa, fa,sb,fb))return 2;
        return 4;
    }
 
 
    private boolean can2(int[] sa, int[] fa, int[] sb, int[] fb) {
        for(int i=0; i<=720; i++)
        {
            boolean ok = true;
            for(int j=0; j<1440; j++)
            {
                if(j>=i&&j<i+720)
                {
                    if(!can(j,sa,fa))ok=false;
                }
                else
                {
                    if(!can(j,sb,fb))ok=false;
                }
                if(!ok)break;
            }
            if(ok)
                return true;
            
            ok = true;
            for(int j=0; j<1440; j++)
            {
                if(j>=i&&j<i+720)
                {
                    if(!can(j,sb,fb))ok=false;
                }
                else
                {
                    if(!can(j,sa,fa))ok=false;
                }
                if(!ok)break;
            }
            if(ok)
                return true;
            
        }
        return false;
    }
 
 
    boolean can(int x, int [] s, int []f)
    {
        for(int i=0; i<s.length; i++)
            if(x>=s[i]&&x<f[i])return false;
        return true;
    }
 
 }
 
 
