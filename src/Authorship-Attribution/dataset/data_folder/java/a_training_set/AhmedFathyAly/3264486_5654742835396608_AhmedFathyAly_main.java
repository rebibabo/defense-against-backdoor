import java.io.*;
 import java.util.*;
 
 public class Main {
 
     
     InputReader in;
     BufferedWriter out;
     StringTokenizer tok;
     StringBuilder ans;
 
     
     long n, k;
 
     public static void main(String[] args) throws IOException {
         Main sol = new Main();
         sol.begin();
     }
 
     private void begin() throws IOException {
         
         boolean file = true;
         if (file)
             in = new InputReader(new FileInputStream("C-small-1-attempt0.in"));
         else
             in = new InputReader(System.in);
         ans = new StringBuilder();
 
         int nCases = in.nextInt();
         for (int cas = 1; cas <= nCases; cas++){
             n = in.nextLong();
             k = in.nextLong();
             String ansStr= solve();
             ans.append(String.format("Case #%d: %s\n", cas, ansStr));
         }
 
         System.out.println(ans.toString());
 
         
         out = new BufferedWriter(new FileWriter("output.txt"));
         out.write(ans.toString());
         out.flush();
         out.close();
     }
 
     private String solve() {
         PriorityQueue<Long> pq =
                 new PriorityQueue<>((o1, o2) -> Long.compare(o2, o1));
         Map<Long, Long> count = new HashMap<>();
         pq.add(n);
         count.put(n, 1l);
 
         long rem = k;
 
         while(true){
             long range = pq.remove();
             long itsCount = count.remove(range);
             rem -= itsCount;
             long l = (range -1)/ 2;
             long r = (range -1)/ 2;
             if (l + r < (range -1))
                 l++;
             if (rem <= 0)
                 return String.format("%d %d", l, r);
 
             if (!pq.contains(l))
                 pq.add(l);
             count.put(l, count.containsKey(l) ? count.get(l) + itsCount : itsCount);
             if (!pq.contains(r))
                 pq.add(r);
             count.put(r, count.containsKey(r) ? count.get(r) + itsCount : itsCount);
 
         }
 
     }
 
 }
 
 class InputReader {
     BufferedReader reader;
     StringTokenizer tok;
 
     public InputReader(InputStream stream) {
         reader = new BufferedReader(new InputStreamReader(stream), 32768);
         tok = new StringTokenizer("");
     }
 
     public String next() {
         while (!tok.hasMoreTokens())
             try {
                 tok = new StringTokenizer(reader.readLine());
             } catch (IOException e) {
                 e.printStackTrace();
             }
         return tok.nextToken();
     }
 
     public int nextInt() {
         return Integer.parseInt(next());
     }
 
     public long nextLong(){
         return Long.parseLong(next());
     }
 }