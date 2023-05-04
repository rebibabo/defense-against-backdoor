import java.util.*;
 
 public class b {
    
    public static int n;
    public static int numCust;
    public static int numTix;
    public static ArrayList[] list;
    public static int[][] tixList;
    
    public static void main(String[] args) {
        
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        
        for (int loop=1; loop<=numCases; loop++) {
            
            n = stdin.nextInt();
            numCust = stdin.nextInt();
            
            list = new ArrayList[numCust];
            for (int i=0; i<numCust; i++)
                list[i] = new ArrayList<Integer>();
            numTix = stdin.nextInt();
            tixList = new int[numTix][2];
            for (int i=0; i<numTix; i++) {
                int pos = stdin.nextInt()-1;
                int cust = stdin.nextInt()-1;
                list[cust].add(pos);
                tixList[i][1] = pos;
                tixList[i][0] = cust;
            }
            
            
            for (int i=0; i<numCust; i++) {
                Collections.sort(list[i]);
                Collections.reverse(list[i]);
            }
        
            int low = 1, high = 1000;
            
            while (low < high) {
            
            int round = (low+high)/2;
    
            
            
            Dinic g = new Dinic(numCust+numTix+round*n);
            for (int i=0; i<numCust; i++) {
                g.add(g.s, i, list[i].size(),0); 
                
            }
            for (int i=0; i<numTix; i++) {
                g.add(tixList[i][0], numCust+i, 1, 0);
                
            }
            for (int i=0; i<numTix; i++) {
                for (int j=0; j<round; j++) {
                    g.add(numCust+i, numCust+numTix+tixList[i][1]+j*n, 1, 0);
                    
                    
                    
                }
            }
            
            for (int i=0; i<round*n; i++) {
                g.add(i+numCust+numTix,g.t,i%n+1,0);
                
            }
 
            int match = g.flow();
 
            if (match < numTix)
                low = round+1;
            else
                high = round;
            }
            
            int numR = low;
            
            Dinic g = new Dinic(numCust+numTix+numR*n);
            for (int i=0; i<numCust; i++) {
                g.add(g.s, i, list[i].size(),0); 
                
            }
            for (int i=0; i<numTix; i++) {
                g.add(tixList[i][0], numCust+i, 1, 0);
                
            }
            for (int i=0; i<numTix; i++) {
                for (int j=0; j<numR; j++) {
                    
                    g.add(numCust+i, numCust+numTix+tixList[i][1]+j*n, 1, 0);
                    
                }
            }
            
            for (int i=0; i<numR*n; i++) {
                g.add(i+numCust+numTix,g.t,1,0);
                
            }       
            int finalmatch = g.flow();  
            System.out.println("Case #"+loop+": "+numR+" "+(numTix-finalmatch));
                
            
 
        }
    }
    
    public static int run(int round) {
        
        ArrayList<pair> seats = new ArrayList<pair>();
        for (int i=0; i<numCust; i++)
            if (round < list[i].size())
                seats.add( new pair( i, ((ArrayList<Integer>)list[i]).get(round)) );
        Collections.sort(seats);
        
        int res = 0;
        boolean[] filled = new boolean[n];
        TreeSet<Integer> open = new TreeSet<Integer>();
        for (int i=0; i<n; i++) open.add(i);
        for (int i=0; i<seats.size(); i++) {
            
            if (!filled[seats.get(i).seat]) {
                filled[seats.get(i).seat] = true;
                open.remove(seats.get(i).seat);
            }
            else {
                int least = open.first();
                if (least <= seats.get(i).seat) {
                    res++;
                    filled[least] = true;
                    open.remove(least);
                }
                else {
                    list[seats.get(i).ID].add(round, seats.get(i).seat);
                }
            }
        }
        
        return res;
        
    }
    
    public static int maxListSize() {
        int res = 0;
        for (int i=0; i<numCust; i++)
            res = Math.max(res, list[i].size());
        return res;
    }
 }
 
 class pair implements Comparable<pair> {
    
    public int ID;
    public int seat;
    
    public pair(int myid, int myseat) {
        ID = myid;
        seat = myseat;
    }
    
    public int compareTo(pair other) {
        if (this.seat != other.seat)
            return this.seat - other.seat;
        return this.ID - other.ID;
    }
 }
 
 class Edge {
    int v1, v2, cap, flow;
    Edge rev;
    Edge(int V1, int V2, int Cap, int Flow) {
        v1 = V1;
        v2 = V2;
        cap = Cap;
        flow = Flow;
    }
 }
 
 class Dinic {
 
    
    public ArrayDeque<Integer> q;
 
    
    public ArrayList<Edge>[] adj;
    public int n;
 
    
    public int s;
    public int t;
 
 
    
    public boolean[] blocked;
    public int[] dist;
 
    final public static int oo = (int)1E9;
 
    
    public Dinic (int N) {
 
        
        n = N; s = n++; t = n++;
 
        
        blocked = new boolean[n];
        dist = new int[n];
        q = new ArrayDeque<Integer>();
        adj = new ArrayList[n];
        for(int i = 0; i < n; ++i)
            adj[i] = new ArrayList<Edge>();
    }
 
    
    public void add(int v1, int v2, int cap, int flow) {
        Edge e = new Edge(v1, v2, cap, flow);
        Edge rev = new Edge(v2, v1, 0, 0);
        adj[v1].add(rev.rev = e);
        adj[v2].add(e.rev = rev);
    }
 
    
    public boolean bfs() {
 
        
        q.clear();
        Arrays.fill(dist, -1);
        dist[t] = 0;
        q.add(t);
 
        
        
        while(!q.isEmpty()) {
            int node = q.poll();
            if(node == s)
                return true;
            for(Edge e : adj[node]) {
                if(e.rev.cap > e.rev.flow && dist[e.v2] == -1) {
                    dist[e.v2] = dist[node] + 1;
                    q.add(e.v2);
                }
            }
        }
 
        
        return dist[s] != -1;
    }
 
    
    public int dfs(int pos, int min) {
 
        
        if(pos == t)
            return min;
        int flow = 0;
 
        
        for(Edge e : adj[pos]) {
            int cur = 0;
 
            
            
            if(!blocked[e.v2] && dist[e.v2] == dist[pos]-1 && e.cap - e.flow > 0) {
 
                
                cur = dfs(e.v2, Math.min(min-flow, e.cap - e.flow));
 
                
                e.flow += cur;
                e.rev.flow = -e.flow;
 
                
                flow += cur;
            }
 
            
            if(flow == min)
                return flow;
        }
 
        
        blocked[pos] = flow != min;
 
        
        return flow;
    }
 
    public int flow() {
        clear();
        int ret = 0;
 
        
        while(bfs()) {
 
            
            Arrays.fill(blocked, false);
 
            
            ret += dfs(s, oo);
        }
        return ret;
    }
 
    
    public void clear() {
        for(ArrayList<Edge> edges : adj)
            for(Edge e : edges)
                e.flow = 0;
    }
 }