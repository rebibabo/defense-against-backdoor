package codeJam2015;
 
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
            System.out.println("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private String solve() {
        readNextLine();
        int X = NextInt();
        int R = NextInt();
        int C = NextInt();
        boolean poss = false;
        
        switch(X)
        {
            case 1:
                poss=true;
                break;
            case 2:
                if(R*C%2==0)poss=true;
                break;
            case 3:
                if(R*C%3==0&&R>1&&C>1)poss=true;
                break;
            case 4:
                if(R*C%4==0&&R>2&&C>2)poss=true;
                break;
            case 5:
                if(R*C%5==0&&R>3&&C>3)poss=true;
                break;
            case 6:
                if(R*C%6==0&&R>3&&C>3)poss=true;
                break;
            default:
                break;
        }
        
        return poss?"GABRIEL":"RICHARD";
    }
 
 }
 
 
