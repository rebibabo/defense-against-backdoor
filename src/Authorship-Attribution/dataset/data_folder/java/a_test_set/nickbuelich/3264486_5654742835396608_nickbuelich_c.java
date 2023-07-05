import java.io.File;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 import java.util.Scanner;
 
 public class C {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C.in"));
        PrintWriter out = new PrintWriter(new File("C.out"));
        int T = sc.nextInt();
        for(int t=1;t<=T;t++){
            int N = sc.nextInt();
            int K = sc.nextInt();
            PriorityQueue<Integer> PQ = new PriorityQueue<Integer>();
            PQ.add(-N);
            int min = 0;
            int max = 0;
            for(int a=0;a<K;a++){
                int cur = -PQ.poll();
                max = cur/2;
                min = (cur-1)/2;
                PQ.add(-min);
                PQ.add(-max);
            }
            String ans = max+" "+min;
            System.out.printf("Case #%d: %s%n", t, ans);
            out.printf("Case #%d: %s%n", t, ans);
        }
        out.close();
    }
 }
