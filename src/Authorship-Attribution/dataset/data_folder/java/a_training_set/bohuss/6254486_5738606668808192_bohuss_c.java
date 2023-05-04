import java.io.*;
 import java.math.BigInteger;
 import java.util.*;
 
 public class C
 {
    String line;
    StringTokenizer inputParser;
    BufferedReader is;
    FileInputStream fstream;
    DataInputStream in;
    String FInput="";
    
    
    void openInput(String file)
    {
 
        if(file==null)is = new BufferedReader(new InputStreamReader(System.in));
        else
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
    
    private double NextDouble() {
         String n = inputParser.nextToken();
         double val = Double.parseDouble(n);
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
    
    public void readFInput()
    {
        for(;;)
        {
            try
            {
                readNextLine();
                FInput+=line+" ";
            }
            catch(Exception e)
            {
                break;
            }
        }
        inputParser = new StringTokenizer(FInput, " ");
    }
    
    long NextLong()
     {
             String n = inputParser.nextToken();
             
             long val = Long.parseLong(n);
             
             return val;
     }
    
    public static void main(String [] argv)
    {
        
         String filePath=null;
         if(argv.length>0)filePath=argv[0];
        new C(filePath);
    }
    
    public C(String inputFile)
    {
        openInput(inputFile);
        
        int T=1;
        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=T; t++)
        {
            readNextLine();
            int N=NextInt();
            
            HashMap <String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
            HashSet <String> seen = new HashSet<String>(); 
            
            for(int i=0; i<N; i++)
            {
                readNextLine();
                if(seen.contains(line))continue;
                seen.add(line);
                String s = parseHostname(line);
                String a = parsePath(line);
                if(!hm.containsKey(s))hm.put(s, new ArrayList<String>());
                hm.get(s).add(a);
            }
            
            HashMap <String, String> hm2 = new HashMap<String, String>();
            
            for(String s:hm.keySet())
            {
                Collections.sort(hm.get(s));
                StringBuilder sbb = new StringBuilder();
                for(String x:hm.get(s))
                    sbb.append(x+"#");
                hm2.put(s, sbb.toString());
            }
            
            
            HashMap <String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
            for(String s:hm2.keySet())
            {
                if(!res.containsKey(hm2.get(s)))res.put(hm2.get(s), new ArrayList<String>());
                res.get(hm2.get(s)).add(s);
            }
            int cnt=0;
            for(String x:res.keySet())
            {
                if(res.get(x).size()>1)
                {
                    for(String s:res.get(x))
                        sb.append(s+" ");
                    sb.append("\n");
                    cnt++;
                }
            }
            sb.insert(0, cnt+"\n");
        }
        System.out.print(sb);
        
        closeInput();       
    }
 
    String parseHostname(String s)
    {
        int pos = s.indexOf('/', 7);
        if(pos==-1)return s;
        return s.substring(0, pos);
    }
    
    String parsePath(String s)
    {
        int pos = s.indexOf('/', 7);
        if(pos==-1)return "@";
        return s.substring(pos);
    }
 
 }