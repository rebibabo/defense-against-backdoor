import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayDeque;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class B {
    static int N,P;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\nickbuelich\\Downloads\\B-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new File("B.out"));
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int ans = 0;
            N = sc.nextInt();
            P = sc.nextInt();
            int[][] grid = new int[N][P];
            int[] greedy = new int[N];
            ArrayList<Integer>[][] AL = new ArrayList[N][P];
            
            for(int a=0;a<N;a++){
                greedy[a]=sc.nextInt();
            }
            
            for(int a=0;a<N;a++){
                for(int b=0;b<P;b++){
                    grid[a][b]=sc.nextInt();
                    AL[a][b] = new ArrayList<Integer>();
                }
            }
            
            for(int a=0;a<N;a++){
                
                long low = 0;
                long high = 0;
                for(int many=1;many<1000000;many++)
                {
                    low+=9;
                    high+=11;
                    for(int b=0;b<P;b++){
                        long cur = grid[a][b]*10;
 
                        if(low*greedy[a]<=cur&&cur<=high*greedy[a]){
                            AL[a][b].add(many);
                        }
                    }   
                }
            }
            
            TidalFlow TF = new TidalFlow(N*P);
            
        
            for(int a=0;a<N-1;a++){
                for(int b=0;b<P;b++){
                    stuff: for(int c=0;c<P;c++){
                        for(int x : AL[a][b]){
                            if(AL[a+1][c].contains(x)){
                                TF.add(mag(a,b), mag(a+1,c), 1);
                                continue stuff;
                            }
                        }
                    }
                }
            }
            
            for(int a=0;a<P;a++){
                if(!AL[0][a].isEmpty()){
                    TF.add(TF.s,mag(0,a),1);
                }
                if(!AL[N-1][a].isEmpty()){
                    TF.add(mag(N-1,a), TF.t, 1);
                }
            }
            
            ans = TF.getFlow();
 
            System.out.printf("Case #%d: %s%n", t, ans);
            out.printf("Case #%d: %s%n", t, ans);
        }
        out.close();
    }
 
    private static int mag(int b, int a) {
        return b*P + a;
    }
 
    static class TidalFlow {
        ArrayDeque<Edge> stk = new ArrayDeque<Edge>();
        int N, s, t, oo = 987654321, fptr, bptr;
        ArrayList<Edge>[] adj;
        int[] q, dist, pool;
 
        @SuppressWarnings("unchecked")
        TidalFlow(int NN) {
            N = (t = (s = NN) + 1) + 1;
            adj = new ArrayList[N];
            for (int i = 0; i < N; adj[i++] = new ArrayList<Edge>())
                ;
            dist = new int[N];
            pool = new int[N];
            q = new int[N];
        }
 
        void add(int i, int j, int cap) {
            Edge fwd = new Edge(i, j, cap, 0);
            Edge rev = new Edge(j, i, 0, 0);
            adj[i].add(rev.rev = fwd);
            adj[j].add(fwd.rev = rev);
        }
 
        int augment() {
            Arrays.fill(dist, Integer.MAX_VALUE);
            pool[t] = dist[s] = fptr = bptr = 0;
            pool[q[bptr++] = s] = oo;
            while (bptr > fptr && q[fptr] != t)
                for (Edge e : adj[q[fptr++]]) {
                    if (dist[e.i] < dist[e.j])
                        pool[e.j] += e.carry = Math.min(e.cap - e.flow, pool[e.i]);
                    if (dist[e.i] + 1 < dist[e.j] && e.cap > e.flow)
                        dist[q[bptr++] = e.j] = dist[e.i] + 1;
                }
            if (pool[t] == 0)
                return 0;
            Arrays.fill(pool, fptr = bptr = 0);
            pool[q[bptr++] = t] = oo;
            while (bptr > fptr)
                for (Edge e : adj[q[fptr++]]) {
                    if (pool[e.i] == 0)
                        break;
                    int f = e.rev.carry = Math.min(pool[e.i], e.rev.carry);
                    if (dist[e.i] > dist[e.j] && f != 0) {
                        if (pool[e.j] == 0)
                            q[bptr++] = e.j;
                        pool[e.i] -= f;
                        pool[e.j] += f;
                        stk.push(e.rev);
                    }
                }
            int res = pool[s];
            Arrays.fill(pool, 0);
            pool[s] = res;
            while (stk.size() > 0) {
                Edge e = stk.pop();
                int f = Math.min(e.carry, pool[e.i]);
                pool[e.i] -= f;
                pool[e.j] += f;
                e.flow += f;
                e.rev.flow -= f;
            }
            return res;
        }
 
        int getFlow() {
            int res = 0, f = 1;
            while (f != 0)
                res += f = augment();
            return res;
        }
 
        static class Edge {
            int i, j, cap, flow, carry;
            Edge rev;
 
            Edge(int ii, int jj, int cc, int ff) {
                i = ii;
                j = jj;
                cap = cc;
                flow = ff;
            }
        }
    }
 }
