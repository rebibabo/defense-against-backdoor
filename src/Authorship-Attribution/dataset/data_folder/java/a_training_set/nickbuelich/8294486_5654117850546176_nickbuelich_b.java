import java.io.File;
 import java.io.PrintWriter;
 import java.util.LinkedList;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class B {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("B.in"));
        PrintWriter out = new PrintWriter(new File("B.out"));
        int T = sc.nextInt();
        for (int t = 1; t <= T; t++) {
            int[] Unicron = new int[6];
            String ans = "IMPOSSIBLE";
 
            int N = sc.nextInt();
            for (int a = 0; a < 6; a++) {
                Unicron[a] = sc.nextInt();
            }
 
            if ((Unicron[colors.G] == 0 || Unicron[colors.R] > Unicron[colors.G])
                    && (Unicron[colors.V] == 0 || Unicron[colors.Y] > Unicron[colors.V])
                    && (Unicron[colors.O] == 0 || Unicron[colors.B] > Unicron[colors.O])) {
                LinkedList<String> R = new LinkedList<String>();
                LinkedList<String> Y = new LinkedList<String>();
                LinkedList<String> B = new LinkedList<String>();
 
                
 
                StringBuilder temp;
 
                
                temp = new StringBuilder();
                for (int a = 0; a < Unicron[colors.G]; a++) {
                    if (a == 0) {
                        temp.append("R");
                        Unicron[colors.R]--;
                    }
                    temp.append("G");
                    temp.append("R");
                    Unicron[colors.R]--;
                }
                if (temp.length() != 0)
                    R.add(temp.toString());
                for (int a = 0; a < Unicron[colors.R]; a++) {
                    R.add("R");
                }
 
                
                temp = new StringBuilder();
                for (int a = 0; a < Unicron[colors.V]; a++) {
                    if (a == 0) {
                        temp.append("Y");
                        Unicron[colors.Y]--;
                    }
                    temp.append("V");
                    temp.append("Y");
                    Unicron[colors.Y]--;
                }
                if (temp.length() != 0)
                    Y.add(temp.toString());
                for (int a = 0; a < Unicron[colors.Y]; a++) {
                    Y.add("Y");
                }
 
                
                temp = new StringBuilder();
                for (int a = 0; a < Unicron[colors.O]; a++) {
                    if (a == 0) {
                        temp.append("B");
                        Unicron[colors.B]--;
                    }
                    temp.append("O");
                    temp.append("B");
                    Unicron[colors.B]--;
                }
                if (temp.length() != 0)
                    B.add(temp.toString());
                for (int a = 0; a < Unicron[colors.B]; a++) {
                    B.add("B");
                }
 
                int max = Math.max(R.size(), Math.max(Y.size(), B.size()));
                int sum = R.size() + Y.size() + B.size();
 
                if (max * 2 <= sum) {
                    LinkedList<LinkedList<String>> LL = new LinkedList();
                    LL.add(R);
                    LL.add(Y);
                    LL.add(B);
 
                    ans = Magic((LinkedList<String>) LL.get(0).clone(), (LinkedList<String>) LL.get(1).clone(),
                            (LinkedList<String>) LL.get(2).clone());
                    
                }
            } else if (Unicron[colors.R] == Unicron[colors.G] || Unicron[colors.Y] == Unicron[colors.V]
                    || Unicron[colors.B] == Unicron[colors.O]) {
                if (Unicron[colors.R] == Unicron[colors.G]
                        && (Unicron[colors.Y] + Unicron[colors.V] + Unicron[colors.B] + Unicron[colors.O] == 0)) {
                    ans = "";
                    for (int a = 0; a < Unicron[colors.R]; a++) {
                        ans += "RG";
                    }
                }
                if (Unicron[colors.Y] == Unicron[colors.V]
                        && (Unicron[colors.R] + Unicron[colors.G] + Unicron[colors.B] + Unicron[colors.O] == 0)) {
                    ans = "";
                    for (int a = 0; a < Unicron[colors.Y]; a++) {
                        ans += "YV";
                    }
                }
                if (Unicron[colors.B] == Unicron[colors.O]
                        && (Unicron[colors.Y] + Unicron[colors.V] + Unicron[colors.R] + Unicron[colors.G] == 0)) {
                    ans = "";
                    for (int a = 0; a < Unicron[colors.B]; a++) {
                        ans += "BO";
                    }
                }
            }
 
            System.out.printf("Case #%d: %s%n", t, ans);
            out.printf("Case #%d: %s%n", t, ans);
            if (!ans.equals("IMPOSSIBLE"))
                for (int a = 1; a < ans.length(); a++) {
 
                    if (ans.charAt(a) == ans.charAt(a - 1)) {
                        return;
                    }
                }
 
        }
        out.close();
    }
 
    private static String Magic(LinkedList<String> AA, LinkedList<String> BB, LinkedList<String> CC) {
        
 
        Odd R = new Odd(AA);
        Odd Y = new Odd(BB);
        Odd B = new Odd(CC);
        StringBuilder ans = new StringBuilder();
 
        PriorityQueue<Odd> PQ = new PriorityQueue<Odd>();
        PQ.add(R);
        PQ.add(Y);
        PQ.add(B);
 
        Odd largest = PQ.poll();
        while (!largest.LL.isEmpty()) {
            ans.append(largest.LL.poll());
            Odd second = PQ.poll();
            if (second.LL.size() != 0) {
                ans.append(second.LL.poll());
            }
            PQ.add(second);
        }
        PQ.add(largest);
 
        while (PQ.peek().LL.size() != 0) {
            largest = PQ.poll();
            ans.append(largest.LL.poll());
 
            Odd second = PQ.poll();
            if (second.LL.size() != 0) {
                ans.append(second.LL.poll());
            }
            PQ.add(largest);
            PQ.add(second);
        }
 
        
        return ans.toString();
    }
 
    static class Odd implements Comparable<Odd> {
        LinkedList<String> LL;
 
        Odd(LinkedList<String> L) {
            LL = L;
        }
 
        @Override
        public int compareTo(Odd that) {
            return that.LL.size() - this.LL.size();
        }
 
    }
 
    public class colors {
        public static final int R = 0;
        public static final int O = 1;
        public static final int Y = 2;
        public static final int G = 3;
        public static final int B = 4;
        public static final int V = 5;
    }
 }
