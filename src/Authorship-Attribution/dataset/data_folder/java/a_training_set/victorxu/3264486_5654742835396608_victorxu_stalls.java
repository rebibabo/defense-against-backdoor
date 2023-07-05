import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 
 
 public class stalls {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("stalls.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stalls.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++) {
            PriorityQueue<Integer> gaps = new PriorityQueue<Integer>();
            String[] s = buf.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            gaps.add(-n);
            for(int j = 0; j < k-1; j++){
                int z = gaps.poll() + 1;
                if(i == 0) System.out.println(z);
                gaps.add(z/2);
                gaps.add(z - z/2);
            }
            int gap = -gaps.poll() - 1;
            out.println("Case #" + (i + 1) + ": " + (gap - gap/2) + " " + (gap/2));
        }
        out.close();
    }
 }
