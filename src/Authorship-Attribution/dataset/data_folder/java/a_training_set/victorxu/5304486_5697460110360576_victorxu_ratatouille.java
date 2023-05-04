import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.PriorityQueue;
 
 
 public class ratatouille {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("ratatouille.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratatouille.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++) {
            String[] s = buf.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int p = Integer.parseInt(s[1]);
            PriorityQueue<Pair>[] servings = (PriorityQueue<Pair>[]) new PriorityQueue[n];
            int[] info = new int[n];
            s = buf.readLine().split(" ");
            for(int j = 0; j < n; j++){
                servings[j] = new PriorityQueue<Pair>();
                info[j] = Integer.parseInt(s[j]);
            }
            for(int j = 0; j < n; j++){
                s = buf.readLine().split(" ");
                for(int k = 0; k < p; k++){
                    double quantity = Double.parseDouble(s[k]);
                    Pair pr = new Pair();
                    pr.a = ((Double) Math.ceil(quantity / info[j] * 10.0/11.0)).intValue();
                    pr.b = ((Double) Math.floor(quantity / info[j] * 10.0/9.0)).intValue();
                    if(pr.a <= pr.b){
                        servings[j].add(pr);
                    }
                }   
            }
            int count = 1;
            int ans = 0;
            while(!servings[0].isEmpty()){
                boolean empty = false;
                boolean bad = false;
                for(int j = 0; j < n; j++){
                    while(!servings[j].isEmpty() && servings[j].peek().b < count) servings[j].poll();
                    if(servings[j].isEmpty()){
                        empty = true;
                        break;
                    }
                    Pair top = servings[j].peek();
                    
                    if(top.a > count){
                        bad = true;
                        break;
                    }
                }
                count++;
                if(empty) break;
                if(bad) continue;
                else {
                    count--;
                    ans++;
                    for(int j = 0; j < n; j++){
                        servings[j].poll();
                    }
                }
            }
            out.println("Case #" + (i + 1) + ": " + ans);
        }
        out.close();
    }
 }
 class Pair implements Comparable<Pair>{
    int a;
    int b;
    public int compareTo(Pair o) {
        return this.a - o.a;
    }
 }
