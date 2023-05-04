package codeJam2016;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1CP1 {
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
        new R1CP1(argv[0]);
    }
    
    public R1CP1(String inputFile)
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
        int N = NextInt();
        readNextLine();
        int [] p = new int[N];
        for(int i=0; i<N; i++)
        {
            p[i] = NextInt();
        }
        StringBuilder sb = new StringBuilder();
        PriorityQueue<State> q = new PriorityQueue<State>(); 
        for(int i=0; i<N; i++)
        {
            State s = new State();
            s.c = (char) ('A' + i);
            
            s.num = p[i];
            q.add(s);
        }
        
        while(!q.isEmpty())
        {
            State s = q.poll();
            sb.append(s.c);
            p[s.c-'A']--;
            if(q.size()==1 && q.peek().num==s.num){
                State s2 = q.poll();
                sb.append(s2.c);
                s2.num--;
                p[s2.c-'A']--;
                if(s2.num>0)q.add(s2);
            }
                
            if(q.size()>0)sb.append(" ");
            s.num--;
            if(s.num>0)q.add(s);
            if(test(p)){
                System.out.println("ERR");
                break;
            }
        }
        
        return sb.toString();
        
    }
    
    private boolean test(int[] p) {
        int all = 0;
        for(int x:p)all+=x;
        int max = all/2;
        for(int x:p)
            if(x>max)return true;
        return false;
    }
 
    private class State implements Comparable<State>{
        int num;
        char c;
        public int compareTo(State d)
        {
            return d.num - num;
        }
    }
    
 }
 
 
