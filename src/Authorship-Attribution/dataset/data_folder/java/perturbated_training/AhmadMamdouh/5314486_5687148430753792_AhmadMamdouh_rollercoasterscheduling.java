import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.Arrays;
 import java.util.LinkedList;
 import java.util.StringTokenizer;
 
 public class RollerCoasterScheduling {
    static class MinCostMaxFlow {
        boolean found[];
        int N, cap[][], flow[][], cost[][], dad[], dist[], pi[];
 
        static final int INF = 1<<27;
 
        boolean search(int source, int sink) {
            Arrays.fill(found, false);
            Arrays.fill(dist, INF);
            dist[source] = 0;
 
            while (source != N) {
                int best = N;
                found[source] = true;
                for (int k = 0; k < N; k++) {
                    if (found[k])
                        continue;
                    if (flow[k][source] != 0) {
                        int val = dist[source] + pi[source] - pi[k]
                                - cost[k][source];
                        if (dist[k] > val) {
                            dist[k] = val;
                            dad[k] = source;
                        }
                    }
                    if (flow[source][k] < cap[source][k]) {
                        int val = dist[source] + pi[source] - pi[k]
                                + cost[source][k];
                        if (dist[k] > val) {
                            dist[k] = val;
                            dad[k] = source;
                        }
                    }
 
                    if (dist[k] < dist[best])
                        best = k;
                }
                source = best;
            }
            for (int k = 0; k < N; k++)
                pi[k] = Math.min(pi[k] + dist[k], INF);
            return found[sink];
        }
 
        int[] getMaxFlow(int cap[][], int cost[][], int source, int sink) {
            this.cap = cap;
            this.cost = cost;
 
            N = cap.length;
            found = new boolean[N];
            flow = new int[N][N];
            dist = new int[N + 1];
            dad = new int[N];
            pi = new int[N];
 
            int totflow = 0, totcost = 0;
            while (search(source, sink)) {
                int amt = INF;
                for (int x = sink; x != source; x = dad[x])
                    amt = Math.min(amt, flow[x][dad[x]] != 0 ? flow[x][dad[x]]
                            : cap[dad[x]][x] - flow[dad[x]][x]);
                for (int x = sink; x != source; x = dad[x]) {
                    if (flow[x][dad[x]] != 0) {
                        flow[x][dad[x]] -= amt;
                        totcost -= amt * cost[x][dad[x]];
                    } else {
                        flow[dad[x]][x] += amt;
                        totcost += amt * cost[dad[x]][x];
                    }
                }
                totflow += amt;
            }
 
            return new int[] { totflow, totcost };
        }
    }
 
    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        int T = r.nextInt();
        int test = 1;
        while (test <= T) {
            int n = r.nextInt();
            int c = r.nextInt();
            int m = r.nextInt();
            int all = 2 * n + 2;
            int source = 2 * n;
            int dest = 2 * n + 1;
            int[][] cap = new int[all][all];
            int[][] cost = new int[all][all];
            LinkedList<Integer> first = new LinkedList<Integer>();
            LinkedList<Integer> second = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                int pos = r.nextInt() - 1;
                int customer = r.nextInt();
                if (customer == 1) {
                    first.add(pos);
                } else {
                    second.add(pos);
                }
            }
            for (int x : first) {
                cap[source][x]++;
            }
            for (int x : second) {
                cap[n + x][dest]++;
            }
            for (int i = 1; i < n; i++) {
                    cost[i][n+i] = 1;
            }
            for (int x : first) {
                for (int y : second) {
                    if (x == y) {
                        if (x > 0) {
                            cap[x][n + y]++;
                        }
                    } else {
                        cap[x][n + y]++;
                    }
                }
            }
            int[] ret = new MinCostMaxFlow().getMaxFlow(cap, cost, source, dest);
            
            int flow = ret[0];
            int promotion = ret[1];
            int result = flow + (first.size() - flow) + (second.size() - flow);
            System.out.printf("Case #%d: %d %d\n", test++, result, promotion);
        }
    }
 
    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }
 
        public InputReader(FileReader stream) {
            reader = new BufferedReader(stream);
            tokenizer = null;
        }
 
        public String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                
                e.printStackTrace();
                return null;
            }
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }
 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
 
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
 }
