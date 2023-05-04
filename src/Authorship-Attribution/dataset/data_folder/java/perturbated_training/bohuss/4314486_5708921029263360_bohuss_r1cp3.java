package codeJam2016;
 
 import java.io.*;
 import java.math.BigInteger;
 import java.text.DecimalFormat;
 import java.util.*;
 
 public class R1CP3 {
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
        new R1CP3(argv[0]);
    }
    
    public R1CP3(String inputFile)
    {
        openInput(inputFile);
        readNextLine();
 
        int TC = NextInt();
 
        
        for(int tt=0; tt<TC; tt++)
        {   
            
            String output=solve();
            System.out.print("Case #"+(tt+1)+": "+output);
        }
        closeInput();
    }
    
    private String solve() {
        readNextLine();
        int J=NextInt();
        int P=NextInt();
        int S=NextInt();
        int K=NextInt();
        
        
        Sol bestSol = new Sol(K, J, P, S);
        bestSol.addStep(1,1,1);
        long startT = System.currentTimeMillis();
        
        int MAX = J*P*S;
        if(MAX>1)
        while(System.currentTimeMillis() - startT <2000)
        {
            Sol sol = new Sol(K, J, P, S);
            sol.addStep(1,1,1);
            while(sol.nextStep())
            {
                
            }
            if(sol.steps.size()>bestSol.steps.size())
            {
                bestSol = sol;
                if(bestSol.steps.size()==MAX)break;
            }
            
        }
        return bestSol.toString();
    }
    Random rgen = new Random(3);
    private class Sol {
        HashSet <Integer> seen = new HashSet<Integer>();
        HashMap <Integer, Integer> seenjp = new HashMap<Integer, Integer>();
        HashMap <Integer, Integer> seenjs = new HashMap<Integer, Integer>();
        HashMap <Integer, Integer> seenps = new HashMap<Integer, Integer>();
        ArrayList <Step> steps = new ArrayList<Step>();
        int K,J,P,S;
        Sol(int K, int J, int P, int S)
        {
            this.K = K;
            this.J = J;
            this.P = P;
            this.S = S;
        }
        public void addStep(int j, int p, int s) {
            Step step  = new Step(j, p, s); 
            steps.add(step);
            seen.add(step.code);
            seenjp.put(step.codejp, 1);
            seenps.put(step.codeps, 1);
            seenjs.put(step.codejs, 1);
            
        }
        public boolean nextStep() {
            long startT = System.currentTimeMillis();
            boolean ok = false;
            Step s = null;
            while(System.currentTimeMillis() - startT <200&&!ok)
            {
                ok = true;
                int jj = rgen.nextInt(J)+1;
                int pp = rgen.nextInt(P)+1;
                int ss = rgen.nextInt(S)+1;
                s = new Step(jj,pp,ss);
                if(seen.contains(s.code))ok = false;
                if(seenjp.getOrDefault(s.codejp, 0)>=K)ok = false;
                if(seenjs.getOrDefault(s.codejs, 0)>=K)ok = false;
                if(seenps.getOrDefault(s.codeps, 0)>=K)ok = false;
                
            }
            if(ok)
            {
                steps.add(s);
                seen.add(s.code);
                
 
                seenjp.put(s.codejp, seenjp.getOrDefault(s.codejp, 0)+1);
                seenjs.put(s.codejs, seenjs.getOrDefault(s.codejs, 0)+1);
                seenps.put(s.codeps, seenps.getOrDefault(s.codeps, 0)+1);
            }
            return ok;
        }
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(steps.size()+"\n");
            for(Step s:steps)
            {
                sb.append(s.j+" "+s.p+" "+s.s+"\n");
            }
            return sb.toString();
        }
    }
    
    private class Step {
        int j,p,s,code;
        int codejp, codejs, codeps;
        Step(int j, int p, int s)
        {
            this.j=j;
            this.p = p;
            this.s = s;
            this.code = 1<<(8+j) | 1<<(4+p) | 1<<s;
            this.codejp = 1<<(8+j) | 1<<(4+p);
            this.codejs = 1<<(8+j) | 1<<s;
            this.codeps = 1<<(4+p) | 1<<s;
        }
    }
 
 }
 
 
