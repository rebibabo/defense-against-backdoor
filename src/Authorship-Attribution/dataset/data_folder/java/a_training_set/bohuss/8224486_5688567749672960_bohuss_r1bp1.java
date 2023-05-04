package codeJam2015;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1BP1 {
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
        new R1BP1(argv[0]);
    }
    
    int [] d = new int[1000001];
    public R1BP1(String inputFile)
    {
        for(int i=0; i<d.length; i++)
            d[i]=10000000;
        fill();
        
        
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
    
    private void fill()
    {
        d[1]=1;
        ArrayList <Integer> q = new ArrayList<Integer>();
        q.add(1);
        while(q.size()>0)
        {
            int x=q.remove(0);
            if(d[x+1]>d[x]+1)
            {
                d[x+1]=d[x]+1;
                q.add(x+1);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(x);
            sb.reverse();
            int y=Integer.parseInt(sb.toString());
            if(d[y]>d[x]+1)
            {
                d[y]=d[x]+1;
                q.add(y);
            }
        }
    }
    
    private String solve() {
        readNextLine();
        int N=NextInt();
        int ret=d[N];
        return ""+ret;
    }
 
 }
 
 
