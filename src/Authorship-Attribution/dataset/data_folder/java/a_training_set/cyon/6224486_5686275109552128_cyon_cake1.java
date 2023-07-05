import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Arrays;
 import java.util.Collections;
 import java.util.HashMap;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.StringTokenizer;
 
 public class Cake1 {
 
    private static void debug(Object... args) {
        System.out.println(Arrays.deepToString(args));
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, true);
        int T = Integer.parseInt(br.readLine());
        for (int c = 1; c <= T; c++) {
            pw.println("Case #" + c + ": " + solve(br));
        }
        pw.flush();
    }
    private static String solve(BufferedReader br) throws IOException {
        int D = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[]val = new int[1001];
        
        int maxi0 = 0;
        for (int i=0;i<D;i++) {
            int a=Integer.parseInt(st.nextToken());
            val[a]++;
            maxi0 = Math.max(maxi0, a);
        }
        Map<State, Integer> seen  = new HashMap<>();
        
        int mini = go(val, 0, maxi0, seen);
        
        return "" + mini;
    }
    
    private static int go(int[] val, int i, int maxi0, Map<State, Integer> seen) {
        
        State s = new State(val);
        Integer best = seen.get(s);
        if (best != null) {
            
            return best;
        }
        int maxi = s.getMaxi(maxi0);
        int mini = maxi;
        val[maxi]--;
        for (int j = 1; j <= maxi / 2; j++) {
            val[j]++;
            val[maxi - j]++;
            mini = Math.min(mini, go(val, i+1, maxi0,seen) + 1);
            val[j]--;
            val[maxi - j]--;
        }
        val[maxi]++;
        seen.put(s, mini);
        
        return mini;
    }
    
    private static final class State {
        int[]arr;
 
        public State(int[] arr) {
            super();
            this.arr = arr;
        }
 
        public int getMaxi(int maxi0) {
            for (int i=maxi0; i>0;i--) {
                if (arr[i] > 0) return i;
            }
            return 0;
        }
 
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(arr);
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
            if (!Arrays.equals(arr, other.arr))
                return false;
            return true;
        }
    }
 }
