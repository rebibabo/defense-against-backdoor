import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.LinkedList;
 
 
 public class BFFs {
    static int max2 = -1;
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("bff.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bff.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++){
            max2 = 0;
            out.print("Case #"+(i+1)+": ");
            int n = Integer.parseInt(buf.readLine());
            int[] data = new int[n];
            LinkedList<Integer>[] goes = new LinkedList[n];
            for(int j = 0; j < n; j++) goes[j] = new LinkedList<Integer>();
            String[] s = buf.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                data[j] = Integer.parseInt(s[j]) - 1;
                goes[Integer.parseInt(s[j]) - 1].add(j);
            }
            int[] len = new int[n];
            for(int j = 0; j < n; j++) search(n, new boolean[n], data, len, j);
            int max = -1;
            for(int j = 0; j < n; j++){
                if(len[j] > max) max = len[j];
            }
            pairchain(n, new boolean[n], data, len, 0, goes);
            out.println(Integer.max(max,max2));
        }
        out.close();
    }
    public static void pairchain(int n, boolean[] done, int[] data, int[] len, int i, LinkedList<Integer>[] goes){
        
        if(i == n) return;
        if(done[i]) {
            pairchain(n, done, data, len, i+1, goes);
            return;
        }
        if(len[i] != 2){
            done[i] = true;
            pairchain(n, done, data, len, i+1, goes);
            return;
        }
        int pair = data[i];
        LinkedList<Integer> yay = new LinkedList<Integer>();
        yay.add(i);
        int count = 0;
        if(goes[i].size() == 1) {
            count++;
        } else {
            while(!yay.isEmpty()){
                LinkedList<Integer> yay2 = new LinkedList<Integer>();
                for(int num : yay){
                    if(num == pair) continue;
                    
                    yay2.addAll(goes[num]);
                }
                yay = yay2;
                count++;
            }
        }
        yay = new LinkedList<Integer>();
        yay.add(pair);
        if(goes[pair].size() == 1) {
            count++;
        } else {
            while(!yay.isEmpty()){
                LinkedList<Integer> yay2 = new LinkedList<Integer>();
                for(int num : yay){
                    if(num == i) continue;
                    
                    yay2.addAll(goes[num]);
                }
                yay = yay2;
                count++;
            }
        }
        done[i] = true;
        done[pair] = true;
        if(count > max2) max2 = count;
    }
    public static void search(int n, boolean[] done, int[] data, int[] len, int i){
        
        if(i == n) return;
        if(done[i]) {
            return;
        }
        int next = data[i];
        done[i] = true;
        if(done[next]){
            if(len[next] == -1){
                fix_loop(n, done, data, len, next);
            } 
            if(len[i] == -1) len[i] = 0;
            return;
        }
        len[i] = -1;
        search(n, done, data, len, next);
        if(len[i] == -1) len[i] = 0;
    }
    public static int fix_loop(int n, boolean[] done, int[] data, int[] len, int i){
        
        int c = 1;
        int b = data[i];
        while(b != i){
            
            c++;
            b = data[b];
        }
        len[i] = c;
        b = data[i];
        while(b != i){
            
            len[b] = c;
            b = data[b];
        }
        return 0;
    }
 }
