package round2.p2;
 
 import java.util.Arrays;
 
 public class BipartiteMatching
 {
    int m, n;
    boolean[][] graph;
    boolean seen[];
    int matchL[]; 
    int matchR[]; 
 
    int maximumMatching()
    {
        
        
        Arrays.fill(matchL, -1);
        Arrays.fill(matchR, -1);
 
        int count = 0;
        for (int i = 0; i < m; i++) {
            Arrays.fill(seen, false);
            if (bpm(i))
                count++;
        }
        return count;
    }
 
    boolean bpm(int u) {
        
        for (int v = 0; v < n; v++) {
            if (!graph[u][v] || seen[v])
                continue;
            seen[v] = true;
            
            
            if (matchR[v] == -1 || bpm(matchR[v])) {
                matchL[u] = v;
                matchR[v] = u;
                return true;
            }
        }
        return false;
    }
 }
