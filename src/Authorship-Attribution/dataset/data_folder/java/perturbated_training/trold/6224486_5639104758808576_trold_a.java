
 import java.io.*;
 import java.util.*;
 
 public class A {
    int getInt(BufferedReader in) throws IOException {
        return Integer.parseInt(in.readLine());
    }   
 
    void run(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0]));
            PrintStream out = new PrintStream(args[0] + ".out");
            int T = getInt(in);
            for (int t = 1 ; t <= T ; t++) {
                String a = in.readLine().split(" ")[1];
                int friends = 0;
                int aud = 0;
                for (int i = 0 ; i < a.length() ; i++) {
                    aud += a.charAt(i) - '0';
                    int needed = i + 1;
                    friends = Math.max(friends, needed - aud);
                }
                out.printf("Case #%d: %d\n", t, friends);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main (String[] args) {
        new A().run(args);
    }
 }
