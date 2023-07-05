package codeJam2017;
 
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
            
            String output=solve();
            System.out.println("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private String solve() {
        readNextLine();
        int N = NextInt();
        int R = NextInt();
        int O = NextInt();
        int Y = NextInt();
        int G = NextInt();
        int B = NextInt();
        int V = NextInt();
        StringBuilder sb = new StringBuilder();
        char prev = '?';
        for(int i=0; i<N; i++)
        {
            char now = getMax(prev, R,O,Y,G,B,V);
            if(now=='?')return "IMPOSSIBLE";
            switch(now)
            {
            case'R':
                R--;
                break;
            case'O':
                O--;
                break;
            case'G':
                G--;
                break;
            case'Y':
                Y--;
                break;
            case'B':
                B--;
                break;
            case'V':
                V--;
                break;
            }
            sb.append(now);
            prev = now;
        }
        if(!canGo(sb.charAt(0), sb.charAt(sb.length()-1)))
        {
            return repair(sb, 1010);
        }
        return sb.toString();
        
    }
 
    private String repair(StringBuilder sb, int r) {
        if(r<=0)return "IMPOSSIBLE";
        
        char ins = sb.charAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        boolean moved = false;
        for(int i=0; i<sb.length()-1; i++)
        {
            if(canGo(sb.charAt(i), ins) && canGo(sb.charAt(i+1), ins))
            {
                sb.insert(i+1, ins);
                if(canGo(sb.charAt(0), sb.charAt(sb.length()-1)))
                    return sb.toString();
                moved = true;
                break;
            }
        }
        if(!moved)return "IMPOSSIBLE";
        
        return repair(sb, r-1);
    }
 
    private char getMax(char prev, int r, int o, int y, int g, int b, int v) {
        int max = 0;
        char ret = '?';
        if(canGo(prev, 'R') && r > max)
        {
            ret = 'R';
            max = r;
        }
        if(canGo(prev, 'O') && o > max)
        {
            ret = 'O';
            max = r;
        }
        if(canGo(prev, 'Y') && y > max)
        {
            ret = 'Y';
            max = y;
        }
        if(canGo(prev, 'G') && g > max)
        {
            ret = 'G';
            max = g;
        }
        if(canGo(prev, 'B') && b > max)
        {
            ret = 'B';
            max = b;
        }
        if(canGo(prev, 'V') && v > max)
        {
            ret = 'V';
            max = v;
        }
        return ret;
    }
 
    private boolean canGo(char prev, char c) {
        if(prev==c)return false;
        if(prev=='R'&&c=='V')return false;
        if(prev=='R'&&c=='O')return false;
        if(prev=='V'&&c=='R')return false;
        if(prev=='O'&&c=='R')return false;
        
        if(prev=='B'&&c=='V')return false;
        if(prev=='B'&&c=='G')return false;
        if(prev=='V'&&c=='B')return false;
        if(prev=='G'&&c=='B')return false;
        
        if(prev=='Y'&&c=='G')return false;
        if(prev=='Y'&&c=='O')return false;
        if(prev=='G'&&c=='Y')return false;
        if(prev=='O'&&c=='Y')return false;
        
        if(prev=='O'&&c=='G')return false;
        if(prev=='O'&&c=='V')return false;
        
        if(prev=='G'&&c=='O')return false;
        if(prev=='G'&&c=='V')return false;
        
        if(prev=='V'&&c=='O')return false;
        if(prev=='V'&&c=='G')return false;
        
        return true;
    }
 
 
 
 }
 
 
