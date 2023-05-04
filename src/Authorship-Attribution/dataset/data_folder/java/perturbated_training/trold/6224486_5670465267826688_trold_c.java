
 import java.io.*;
 import java.util.*;
 
 public class C {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
    String subString(String s, long i, long j) {
        StringBuilder sb = new StringBuilder();
        for (long k = i ; k < j ; k++) {
            sb.append(s.charAt((int)(k%s.length())));
        }
        return sb.toString();
    }
    int g(String elm) {
        int e = 0;
        char c = elm.charAt(elm.length()-1);
        if (c != '1') e = c - 'i' + 1;
        if (elm.charAt(0) == '-') e += 4;
        return e;
    }
    String[][] g = new String[][] {
        {" 1", " i", " j", " k"},
        {" i", "-1", " k", "-j"},
        {" j", "-k", "-1", " i"},
        {" k", " j", "-i", "-1"}};
    int[][] G = new int[8][8]; {
        for (int i = 0 ; i < 4 ; i++) {
            for (int j = 0 ; j < 4 ; j++) {
                G[i  ][j  ] = g(g[i][j]);
                G[i+4][j  ] = g(g[i][j])^4;
                G[i  ][j+4] = g(g[i][j])^4;
                G[i+4][j+4] = g(g[i][j]);
            }
        }
    }
    String[] name = new String[] {"1", "i", "j", "k", "-1", "-i", "-j", "-j"};
 
    int calc(String input, long i, long j) {
        int e = 0;
        for (long k = i ; k < j ; k++) {
            int elm = g(" "+input.charAt((int)(k%input.length())));
            e = G[e][elm];
        }
        return e;
    }
 
    boolean search(String input, long X) {
        int L = input.length();
        long LX = L*X;
        int[] elms = new int[L];
        for (int i = 0 ; i < L ; i++) elms[i] = input.charAt(i) - 'h';
        int[][] range = new int[L][L+1]; 
        for (int i = 0 ; i < L ; i++) {
            for (int j = i ; j < L ; j++) {
                range[i][j+1] = G[range[i][j]][elms[j]];
            }
        }
        int[] blocksum = new int[4];
        for (int i = 1 ; i < 4 ; i++) {
            blocksum[i] = G[blocksum[i-1]][range[0][L]];
        }
        List<Integer> klengths = new ArrayList<>();
        int ek = 0;
        for (int k = L * 4 - 1 ; k >= 0 ; k--) {
            ek = G[elms[k%L]][ek];
            if (ek == 3) klengths.add(L * 4 - k);
        }
        int ei = 0;
        int maxi = (int) Math.min(LX, L*4);
        for (int i = 0 ; i < maxi ; i++) {
            ei = G[ei][elms[i%L]];
            if (ei == 1) {
                for (int klength : klengths) {
                    long first = i + 1;        
                    long last = LX - klength;  
                    if (last <= first) break;
                    int fint = (int) (first % L);
                    int lint = (int) (last % L);
                    if (first / L == last / L) {
                        if (range[fint][lint] == 2) return true;
                    } else {
                        int ej = range[fint][L];
                        long blocks = last / L - first / L - 1;
                        ej = G[ej][blocksum[(int)(blocks%4)]];
                        ej = G[ej][range[0][lint]];
                        if (ej == 2) return true;
                    }
                }
            }
        }
        return false;
    }
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                long X = Long.parseLong(in.readLine().split(" ")[1]);
                String input = in.readLine();
                if (search(input, X)) 
                    out.printf("Case #%d: YES\n", t);
                else
                    out.printf("Case #%d: NO\n", t);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new C().run(args);
    }
 }
