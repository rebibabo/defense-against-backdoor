import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.*;
 import java.io.*;
 import java.math.*;
 import java.text.*;
 import java.util.*;
 import java.util.concurrent.*;
 public class D {
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
 
    static int r, c;
 
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(new BufferedWriter(new FileWriter("d.out")));
        final int MAX_CASES = readInt();
        for(int casenum = 1; casenum <= MAX_CASES; casenum++) {
            pw.printf("Case #%d: ", casenum);
            int n = readInt();
            r = readInt();
            c = readInt();
            if(n >= 8 || (r*c)%n != 0) {
                pw.println("RICHARD");
                continue;
            }
            dp = new HashMap<String, Boolean>();
            if(lose(n)) {
                pw.println("RICHARD");
                continue;
            }
            pw.println("GABRIEL");
        }
        pw.close();
    }
 
    static HashMap<String, Boolean> dp;
 
    public static boolean lose(int size) {
        ArrayList<State> list = new ArrayList<State>();
        list.add(new State(0, 0));
        return dfs(size, list);
    }
 
    public static boolean bad(ArrayList<State> list) {
        for(int mask = 0; mask < 16; mask++) {
            ArrayList<State> curr = list;
            for(int a = 0; a < mask%4; a++) {
                curr = rotate(curr);
            }
            if((mask&4) != 0) {
                curr = reflectX(curr);
            }
            if((mask&8) != 0) {
                curr = reflectY(curr);
            }
            if(!pieceLoses(curr)) {
                return false;
            }
        }
        return true;
    }
    
    public static ArrayList<State> reflectX(ArrayList<State> list) {
        ArrayList<State> ret = new ArrayList<State>();
        for(State out: list) {
            ret.add(new State(-out.x, out.y));
        }
        return ret;
    }
    
    public static ArrayList<State> reflectY(ArrayList<State> list) {
        ArrayList<State> ret = new ArrayList<State>();
        for(State out: list) {
            ret.add(new State(out.x, -out.y));
        }
        return ret;
    }
    
    public static ArrayList<State> rotate(ArrayList<State> list) {
        ArrayList<State> ret = new ArrayList<State>();
        for(State out: list) {
            ret.add(new State(out.y, -out.x));
        }
        return ret;
    }
    
    public static boolean pieceLoses(ArrayList<State> list) {
        ArrayList<State> next = new ArrayList<State>();
        for(State out: list) {
            next.add(out);
        }
        Collections.sort(next);
        for(int i = next.size()-1; i >= 0; i--) {
            State curr = next.get(i);
            curr.x -= next.get(0).x;
            curr.y -= next.get(0).y;
            next.set(i, curr);
        }
        list = next;
        String key = list.toString();
        if(dp.containsKey(key)) return dp.get(key);
        boolean[][] grid = new boolean[r][c];
        for(int i = 0; i < r; i++) {
            outer: for(int j = 0; j < c; j++)  {
                boolean canPlace = true;
                for(State out: list) {
                    int nx = i + out.x;
                    int ny = j + out.y;
                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
                        canPlace = false;
                    }
                }
                if(!canPlace) continue;
                for(State out: list) {
                    int nx = i + out.x;
                    int ny = j + out.y;
                    grid[nx][ny] = true;
                }
                for(int a = 0; a < r; a++) {
                    for(int b = 0; b < c; b++) {
                        if(grid[a][b]) continue;
                        int seen = 0;
                        LinkedList<State> q = new LinkedList<State>();
                        q.add(new State(a,b));
                        grid[a][b] = true;
                        while(!q.isEmpty()) {
                            State curr = q.removeFirst();
                            seen++;
                            for(int k = 0; k < dx.length; k++) {
                                int nx = curr.x + dx[k];
                                int ny = curr.y + dy[k];
                                if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
                                    continue;
                                }
                                if(grid[nx][ny]) continue;
                                grid[nx][ny] = true;
                                q.add(new State(nx, ny));
                            }
                        }
                        if(seen % list.size() != 0) {
                            continue outer;
                        }
                    }
                }
                dp.put(key, false);
                return false;
            }
        }
        dp.put(key, true);
        return true;
    }
 
    public static boolean dfs(int size, ArrayList<State> list) {
        if(list.size() == size) {
            return bad(list);
        }
        State key = list.get(list.size()-1);
        for(int k = 0; k < dx.length; k++) {
            int nx = key.x + dx[k];
            int ny = key.y + dy[k];
            list.add(new State(nx, ny));
            if(dfs(size, list)) {
                return true;
            }
            list.remove(list.size()-1);
        }
        return false;
    }
 
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
 
    static class State implements Comparable<State> {
        public int x,y;
 
        public String toString() {
            return x + "," + y + "!";
        }
 
        public int compareTo(State s) {
            if(x != s.x) return x - s.x;
            return y - s.y;
        }
 
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
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
            Point other = (Point) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
 
        public State(int x, int y) {
            super();
            this.x = x;
            this.y = y;
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
