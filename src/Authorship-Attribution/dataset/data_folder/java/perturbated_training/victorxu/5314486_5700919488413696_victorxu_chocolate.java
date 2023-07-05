import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 import org.sat4j.specs.ContradictionException;
 import org.sat4j.specs.TimeoutException;
 
 
 public class Chocolate {
    public static void main(String[] args) throws IOException, ContradictionException, TimeoutException{
        BufferedReader buf = new BufferedReader(new FileReader("chocolate.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("chocolate.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++) {
            String[] s = buf.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int p = Integer.parseInt(s[1]);
            if(p == 2) {
                s = buf.readLine().split(" ");
                int[] rem = new int[2];
                for(int j = 0; j < n; j++){
                    rem[Integer.parseInt(s[j]) % 2]++;
                }
                out.println("Case #" + (i + 1) + ": " + (rem[0] + (rem[1] + 1)/2));
            } else if (p == 3) {
                s = buf.readLine().split(" ");
                int[] rem = new int[3];
                for(int j = 0; j < n; j++){
                    rem[Integer.parseInt(s[j]) % 3]++;
                }
                int res = rem[0];
                int less = Math.min(rem[1], rem[2]);
                int extra = Math.max(rem[1], rem[2]) - Math.min(rem[1], rem[2]);
                res = res + less;
                res = res + (extra + 2) / 3;
                out.println("Case #" + (i + 1) + ": " + res);
            } else {
                s = buf.readLine().split(" ");
                int[] rem = new int[4];
                for(int j = 0; j < n; j++){
                    rem[Integer.parseInt(s[j]) % 4]++;
                }
                int res = rem[0];
                int less = Math.min(rem[1], rem[3]);
                int extra = Math.max(rem[1], rem[3]) - Math.min(rem[1], rem[3]);
                res = res + less;
                res = res + rem[2]/2;
                if(rem[2] % 2 == 1 && extra >= 2){
                    res++;
                    extra = extra - 2;
                    rem[2] = 0;
                }
                res = res + extra / 4;
                if(rem[2] % 2 == 1 || extra % 4 != 0){
                    res++;
                }
                out.println("Case #" + (i + 1) + ": " + res);
            }
        }
        out.close();
    }
 }
