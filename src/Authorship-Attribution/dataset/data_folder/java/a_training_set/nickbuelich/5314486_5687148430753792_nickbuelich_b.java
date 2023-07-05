import java.io.File;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.LinkedList;
 import java.util.Queue;
 import java.util.Scanner;
 
 public class B {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("B.in"));
        PrintWriter out = new PrintWriter(new File("B.out"));
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int S = sc.nextInt();
            int C = sc.nextInt();
            int M = sc.nextInt();
            
            LinkedList<Integer> AA = new LinkedList<Integer>();
            LinkedList<Integer> BB = new LinkedList<Integer>();
            
            int rides = 0;
            int disc = 0;
 
            for(int a=0;a<M;a++){
                int p = sc.nextInt();
                int b=  sc.nextInt();
                if(b==1){
                    AA.add(p);
                }
                else {
                    BB.add(p);
                }
            }
            
            BipartiteMatching BP = new BipartiteMatching(AA.size() + BB.size());
            for(int a=0;a<AA.size();a++){
                for(int b=0;b<BB.size();b++){
                    if(AA.get(a) != BB.get(b)){
                        BP.addEdge(a, b+AA.size());
                    }
                }
            }
            
            BipartiteMatching BP2 = new BipartiteMatching(AA.size() + BB.size());
            for(int a=0;a<AA.size();a++){
                for(int b=0;b<BB.size();b++){
                    int AAA = AA.get(a);
                    int BBB = BB.get(b);
                    if(AAA!=1){
                        AAA= 9997;
                    }
                    if(BBB!=1){
                        BBB=9998;
                    }
                    if(AAA != BBB){
                        BP2.addEdge(a, b+AA.size());
                    }
                }
            }
            
            int bad = M-BP.run();
            
            rides = M-BP2.run();
            disc = bad - rides;
            
            System.out.printf("Case #%d: %d %d%n", t, rides, disc);
            out.printf("Case #%d: %d %d%n", t, rides, disc);
        }
        out.close();
    }
    
    static class BipartiteMatching {
        ArrayList<Integer>[] g;
        int[] match;
        int n;
        int count = 0;
 
        BipartiteMatching(int s) {
            n = s;
            match = new int[n];
            Arrays.fill(match, -1);
            g = new ArrayList[n];
            for (int i = 0; i < n; i++)
                g[i] = new ArrayList<Integer>();
 
        }
 
        void addEdge(int i, int j) {
            g[i].add(j);
            g[j].add(i);
        }
 
        int run() {
            for (int i = 0; i < n; i++)
                if (match[i] < 0 && bfs(i))
                    count++;
            return count;
        }
 
        boolean bfs(int x) {
            Queue<Integer> q = new LinkedList<Integer>();
            int[] prev = new int[n];
            Arrays.fill(prev, -1);
            q.add(x);
            while (!q.isEmpty()) {
                int c = q.poll();
                for (int m : g[c])
                    if (match[m] < 0) {
                        prev[m] = c;
                        int next = -1;
                        for (int i = m; i >= 0; i = next) {
                            next = match[prev[i]];
                            match[i] = prev[i];
                            match[prev[i]] = i;
                        }
                        return true;
                    } else if (prev[m] < 0) {
                        prev[m] = c;
                        q.add(match[m]);
                    }
            }
            return false;
 
        }
    }
 
 }
 
