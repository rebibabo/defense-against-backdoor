import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.*;
 
 public class ProblemC {
     BufferedReader rd;
 
     ProblemC() throws IOException {
         rd = new BufferedReader(new InputStreamReader(System.in));
         compute();
     }
 
     private void compute() throws IOException {
         int n = pint();
         for(int i=0;i<n;i++) {
             out("Case #" + (i + 1) + ": " + solve());
         }
     }
 
     private String solve() throws IOException {
         int n = pint();
         Set<Integer> en = new HashSet<>();
         Set<Integer> fr = new HashSet<>();
         String[] e = split(rd.readLine().trim());
         WordStore w = new WordStore();
         for(String x: e) {
             en.add(w.get(x));
         }
         String[] f = split(rd.readLine().trim());
         for(String x: f) {
             fr.add(w.get(x));
         }
 
         int u = n-2;
         int[][] other = new int[u][];
         for(int i=0;i<u;i++) {
             Set<String> y = new HashSet<>(Arrays.asList(split(rd.readLine().trim())));
             int j = 0;
             other[i] = new int[y.size()];
             for(String x: y) {
                 other[i][j++] = w.get(x);
             }
         }
 
         boolean[] enArr = new boolean[w.next];
         for(Integer x: en) {
             enArr[x] = true;
         }
         boolean[] frArr = new boolean[w.next];
         for(Integer x: fr) {
             frArr[x] = true;
         }
 
         int su = 1<<u;
         int min;
         if(u == 0) {
             min = 0;
             for(int j=0;j<enArr.length;j++) {
                 if(enArr[j]&&frArr[j]) {
                     min++;
                 }
             }
         } else {
             min = Integer.MAX_VALUE;
             for(int i=0;i<su;i++) {
                 boolean[] en2 = Arrays.copyOf(enArr, enArr.length);
                 boolean[] fr2 = Arrays.copyOf(frArr, frArr.length);
                 for(int j=0;j<u;j++) {
                     if((i&(1<<j))>0) {
                         for(int x: other[j]) {
                             en2[x] = true;
                         }
                     } else {
                         for(int x: other[j]) {
                             fr2[x] = true;
                         }
                     }
                 }
                 int res = 0;
                 for(int j=0;j<en2.length;j++) {
                     if(en2[j]&&fr2[j]) {
                         res++;
                     }
                 }
                 min = Math.min(res, min);
             }
         }
         return Integer.toString(min);
     }
 
     class WordStore {
         int next = 0;
         Map<String, Integer> byWord = new HashMap<>();
 
         public int get(String word) {
             Integer value = byWord.get(word);
             if(value == null) {
                 value = next++;
                 byWord.put(word, value);
             }
             return value;
         }
     }
 
     private int pint() throws IOException {
         return pint(rd.readLine());
     }
 
     private int pint(String s) {
         return Integer.parseInt(s);
     }
 
     public String[] split(String s) {
         if(s == null) {
             return new String[0];
         }
         int n = s.length();
         int start = -1;
         int end = 0;
         int sp = 0;
         boolean lastWhitespace = true;
         for(int i=0;i<n;i++) {
             char c = s.charAt(i);
             if(isWhitespace(c)) {
                 lastWhitespace = true;
             } else {
                 if(lastWhitespace) {
                     sp++;
                 }
                 if(start == -1) {
                     start = i;
                 }
                 end = i;
                 lastWhitespace = false;
             }
         }
         if(start == -1) {
             return new String[0];
         }
         String[] res = new String[sp];
         int last = start;
         int x = 0;
         lastWhitespace = true;
         for(int i=start;i<=end;i++) {
             char c = s.charAt(i);
             boolean w = isWhitespace(c);
             if(w && !lastWhitespace) {
                 res[x++] = s.substring(last,i);
             } else if(!w && lastWhitespace) {
                 last = i;
             }
             lastWhitespace = w;
         }
         res[x] = s.substring(last,end+1);
         return res;
     }
 
     private boolean isWhitespace(char c) {
         return c==' ' || c=='\t';
     }
 
     private static void out(Object x) {
         System.out.println(x);
     }
 
     public static void main(String[] args) throws IOException {
         new ProblemC();
     }
 }
