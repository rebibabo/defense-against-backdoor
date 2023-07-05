import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Comparator;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.StringTokenizer;
 
 import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
 
 public class Main
 {
 
    public static void main(String[] args) throws IOException
    {
        Main main = new Main();
        main.read();
    }
 
    
    private StringBuilder ans;
    private BufferedReader in;
    private BufferedWriter out;
    private StringTokenizer tok;
    private ArrayList<Hiker> hikers;
 
    
 
    private void read() throws IOException
    {
        
        String inputFileName = "C:\\Users\\ahmed\\Desktop\\Codejam\\Input.in";
        String outputFileName = "C:\\Users\\ahmed\\Desktop\\Codejam\\Output.txt";
        in = new BufferedReader(new FileReader(inputFileName));
        out = new BufferedWriter(new FileWriter(outputFileName));
        ans = new StringBuilder();
 
        
        int nCases = Integer.parseInt(in.readLine());
        for (int cas = 1; cas <= nCases; cas++)
        {
            
            hikers = new ArrayList<Hiker>();
            int nHikers = Integer.parseInt(in.readLine());
            for (int i = 0; i < nHikers; i++)
            {
                tok = new StringTokenizer(in.readLine());
                long d = Long.parseLong(tok.nextToken());
                int h = Integer.parseInt(tok.nextToken());
                long t = Long.parseLong(tok.nextToken());
                for (int j = 0; j < h; j++)
                {
                    hikers.add(new Hiker(t, d));
                    t++;
                }
            }
 
            
            ans.append("Case #" + cas + ": " + solve() + "\n");
 
        }
 
        
        System.out.print(ans.toString());
        out.write(ans.toString());
        out.flush();
        out.close();
 
    }
 
    private String solve()
    {
        if (hikers.size() <= 1)
            return "0";
 
        Hiker hiker1 = hikers.get(0);
        Hiker hiker2 = hikers.get(1);
        double t1 = 1.0 * hiker1.t * (360 - hiker1.d) / 360;
        double t2 = 1.0 * hiker2.t * (360 - hiker2.d) / 360;
        if (t1 > t2)
        {
            hiker1 = hikers.get(1);
            hiker2 = hikers.get(0);
            t1 = 1.0 * hiker1.t * (360 - hiker1.d) / 360;
            t2 = 1.0 * hiker2.t * (360 - hiker2.d) / 360;
        }
 
        
        
        double timeSlower = t2;
        double timeFaster = t1 + hiker1.t;
        if (timeSlower < timeFaster)
            return "0";
        else
            return "1";
    }
 
 }
 
 class Hiker
 {
    long d;
    long t;
 
    public Hiker(long t, long d)
    {
        this.t = t;
        this.d = d;
    }
 
    public String toString()
    {
        return "t: " + t + " d: " + d;
    }
 }
