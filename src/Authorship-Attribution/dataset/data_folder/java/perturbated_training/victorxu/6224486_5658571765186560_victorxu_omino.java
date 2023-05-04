import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class Omino {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("omino.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("omino.out")));
        int n = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= n; i++){
            String[] s = buf.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int r = Integer.parseInt(s[1]);
            int c = Integer.parseInt(s[2]);
            if(r > c){
                int d = r;
                r = c;
                c = d;
            }
            if(x < 7 && (r*c)%x == 0 && x <= c){
                if(r == 1){
                    if(x <= 2){
                        out.println("Case #" + i + ": GABRIEL");
                    }else out.println("Case #" + i + ": RICHARD");
                }else if (r == 2){
                    if(x <= 3){
                        out.println("Case #" + i + ": GABRIEL");
                    }else out.println("Case #" + i + ": RICHARD");
                }else if (r == 3){
                    if(x <= 5){
                        out.println("Case #" + i + ": GABRIEL");
                    }else out.println("Case #" + i + ": RICHARD");
                }else {
                    out.println("Case #" + i + ": GABRIEL");
                }
            } else {
                out.println("Case #" + i + ": RICHARD");
            }
        }
        buf.close();
        out.close();
    }
 }
