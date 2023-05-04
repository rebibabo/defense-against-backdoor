import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class C {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    static Semaphore processorSemaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
    static Semaphore inputSemaphore = new Semaphore(1);
    static Semaphore outputSemaphore = new Semaphore(0);
    static String[] output;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("c.out")));
        final int MAX_CASES = readInt();
        output = new String[MAX_CASES];
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            processorSemaphore.acquire();
            inputSemaphore.acquire();
            new WorkThread(casenum).start();
        }
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            outputSemaphore.acquire();
        }
        for(String out: output) {
            pw.println(out);
        }
        pw.close();
    }
 
    static class WorkThread extends Thread {
        public int casenum;
 
        public WorkThread(int casenum) {
            super();
            this.casenum = casenum;
        }
        public void run() {
 
            int hpDragon = readInt();
            int adDragon = readInt();
            int hpKnight = readInt();
            int adKnight = readInt();
            int adIncrease = readInt();
            int adDecrease = readInt();
            
            inputSemaphore.release();
 
            int ret = -1;
            
            Map<State, Integer> dp = new HashMap<State, Integer>();
            dp.put(new State(hpDragon, hpKnight, adDragon, adKnight), 0);
            LinkedList<State> q = new LinkedList<State>();
            q.add(new State(hpDragon, hpKnight, adDragon, adKnight));
            while(!q.isEmpty()) {
                State curr = q.removeFirst();
                if(curr.dragonAD >= curr.knightHP) {
                    ret = dp.get(curr) + 1;
                    break;
                }
                
                State next = new State(curr.dragonHP - curr.knightAD, curr.knightHP - curr.dragonAD, curr.dragonAD, curr.knightAD);
                if(next.dragonHP > 0 && !dp.containsKey(next)) {
                    dp.put(next, dp.get(curr) + 1);
                    q.add(next);
                }
                
                next = new State(curr.dragonHP - curr.knightAD, curr.knightHP, curr.dragonAD + adIncrease, curr.knightAD);
                if(next.dragonHP > 0 && !dp.containsKey(next)) {
                    dp.put(next, dp.get(curr) + 1);
                    q.add(next);
                }
                
                next = new State(hpDragon - curr.knightAD, curr.knightHP, curr.dragonAD, curr.knightAD);
                if(next.dragonHP > 0 && !dp.containsKey(next)) {
                    dp.put(next, dp.get(curr) + 1);
                    q.add(next);
                }
                
                next = new State(curr.dragonHP - Math.max(0, curr.knightAD - adDecrease), curr.knightHP, curr.dragonAD, Math.max(0, curr.knightAD - adDecrease));
                if(next.dragonHP > 0 && !dp.containsKey(next)) {
                    dp.put(next, dp.get(curr) + 1);
                    q.add(next);
                }
            }
            
            
            if(ret == -1) {
                output[casenum-1] = "Case #" + casenum + ": IMPOSSIBLE";
            }
            else {
                output[casenum-1] = "Case #" + casenum + ": " + ret;
            }
 
            
 
            outputSemaphore.release();
            processorSemaphore.release();
        }
        
        static class State {
            public int dragonHP, knightHP, dragonAD, knightAD;
 
            public State(int dragonHP, int knightHP, int dragonAD, int knightAD) {
                super();
                this.dragonHP = dragonHP;
                this.knightHP = knightHP;
                this.dragonAD = dragonAD;
                this.knightAD = knightAD;
            }
            
            @Override
            public String toString() {
                return "State [dragonHP=" + dragonHP + ", knightHP=" + knightHP + ", dragonAD=" + dragonAD
                        + ", knightAD=" + knightAD + "]";
            }
 
            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + dragonAD;
                result = prime * result + dragonHP;
                result = prime * result + knightAD;
                result = prime * result + knightHP;
                return result;
            }
 
            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                State other = (State) obj;
                if (dragonAD != other.dragonAD)
                    return false;
                if (dragonHP != other.dragonHP)
                    return false;
                if (knightAD != other.knightAD)
                    return false;
                if (knightHP != other.knightHP)
                    return false;
                return true;
            }
            
        }
        
    }
 
    public static int readInt() {
        return Integer.parseInt(nextToken());
    }
 
    public static long readLong() {
        return Long.parseLong(nextToken());
    }
 
    public static double readDouble() {
        return Double.parseDouble(nextToken());
    }
 
    public static String nextToken() {
        while(st == null || !st.hasMoreTokens())    {
            try {
                if(!br.ready()) {
                    pw.close();
                    System.exit(0);
                }
                st = new StringTokenizer(br.readLine());
            }
            catch(IOException e) {
                System.err.println(e);
                System.exit(1);
            }
        }
        return st.nextToken();
    }
 
    public static String readLine() {
        st = null;
        try {
            return br.readLine();
        }
        catch(IOException e) {
            System.err.println(e);
            System.exit(1);
            return null;
        }
    }
 
 }
