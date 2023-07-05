import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.PrintWriter;
 import java.util.HashSet;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
 
    public Main() throws FileNotFoundException {
        
        Scanner sc = new Scanner(new File("C-small-attempt1.in"));
        PrintWriter out = new PrintWriter(new File("C_small_1.out"));
 
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            int hd = sc.nextInt();
            int ad = sc.nextInt();
            int hk = sc.nextInt();
            int ak = sc.nextInt();
            int b = sc.nextInt();
            int d = sc.nextInt();
            
            HashSet<Pair> set = new HashSet<Pair>();
            set.add(new Pair(hd, hk, ad, ak));
            int sol = 1;
            boolean finished = false;
            int limit = 1000;
            HashSet<Long> done = new HashSet<Long>();
            done.add(getVal(hd, hk, ad, ak));
            thisLoop: while (!finished && set.size() > 0 && sol < limit) {
                HashSet<Pair> newSet = new HashSet<Pair>();
                for (Pair p: set) {
                    
                    Pair p2 = new Pair(p.x, p.y-p.att, p.att, p.def);
                    if (p2.y <= 0) {
                        finished = true;
                        break thisLoop;
                    }
                    p2.x -= p.def;
                    if (p2.x > 0 && !done.contains(getVal(p2))) {
                        newSet.add(p2);
                        done.add(getVal(p2));
                    }
                    
                    Pair p3 = new Pair(p.x-p.def, p.y, p.att + b, p.def);
                    if (p3.x > 0 && !done.contains(getVal(p3))) {
                        newSet.add(p3);
                        done.add(getVal(p3));
                    }
                    
                    Pair p4 = new Pair(hd-p.def, p.y, p.att, p.def);
                    if (p4.x > 0 && !done.contains(getVal(p4))) {
                        newSet.add(p4);
                        done.add(getVal(p4));
                    }
                    
                    Pair p5 = new Pair(p.x, p.y, p.att, Math.max(0, p.def-d));
                    p5.x -= p5.def;
                    if (p5.x > 0 && !done.contains(getVal(p5))) {
                        newSet.add(p5);
                        done.add(getVal(p5));
                    }
                }
                set = newSet;
                sol++;
            }
            
            String solution = "IMPOSSIBLE";
            if (sol < limit && finished) {
                solution = ""+sol;
            }
            
            System.out.println("Case #" + task + ": " + solution);
            out.println("Case #" + task + ": " + solution);
        }
 
        out.close();
        sc.close();
    }
    
    private long getVal(Pair p) {
        return getVal(p.x, p.y, p.att, p.def);
    }
    
    private long getVal(long a, long b, long c, long d) {
         long l = a*1000000000000L+b*100000000+c*10000+d;
         return l;
    }
    
    class Pair{
        int x;
        int y;
        int att;
        int def;
        public Pair(int x, int y, int att, int def) {
            this.x = x;
            this.y = y;
            this.att = att;
            this.def = def;
        }
    }
 }
