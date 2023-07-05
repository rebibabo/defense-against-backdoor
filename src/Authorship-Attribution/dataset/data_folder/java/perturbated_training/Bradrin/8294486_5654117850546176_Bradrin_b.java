import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 public class B {
    
    enum Color {
        R,
        Y,
        B,
        O,
        G,
        V
    }
    
    Map<Color, Integer> colors = new HashMap<>();
    
    Color start;
 
    private void solve(Scanner scan, PrintWriter out) {
        int N = scan.nextInt();
        colors.put(Color.R, scan.nextInt());
        colors.put(Color.O, scan.nextInt());
        colors.put(Color.Y, scan.nextInt());
        colors.put(Color.G, scan.nextInt());
        colors.put(Color.B, scan.nextInt());
        colors.put(Color.V, scan.nextInt());
        
        int n = N;
        
        int R = colors.get(Color.R);
        int Y = colors.get(Color.Y);
        int B = colors.get(Color.B);
        
 
        
        if (Math.max(R, Math.max(Y, B)) > N/2) {
            System.out.println("IMPOSSIBLE");
            out.println("IMPOSSIBLE");
            return;
        }
        
        String result = "";
        
        if (R >= Y && R >= B) {
            start = Color.R;
            colors.put(Color.R, colors.get(Color.R) - 1);
        } else if (Y >= R && Y >= B) {
            start = Color.Y;
            colors.put(Color.Y, colors.get(Color.Y) - 1);   
        } else if (B >= R && B >= Y) {
            start = Color.B;
            colors.put(Color.B, colors.get(Color.B) - 1);   
        } else {
            throw new RuntimeException();
        }
        n--;
        result += start;
        
 
        
        Color last = start;
        
        while (n > 0) {
            Color c = findMax(last);
 
            result += c.toString();
            colors.put(c, colors.get(c) - 1);
            last = c;
            n--;
        }
        
        System.out.println(result);
        out.println(result);
            
    }
    
    private Color findMax(Color ignore) {
        int r = colors.get(Color.R);
        int y = colors.get(Color.Y);
        int b = colors.get(Color.B);
        if (r < 0) {
            throw new RuntimeException();
        }
        if (y < 0) {
            throw new RuntimeException();
        }
        if (b < 0) {
            throw new RuntimeException();
        }
 
        if (ignore == Color.R) {
            if (y == b) {
                return start == Color.Y ? Color.Y : Color.B;
            }
            return y > b ? Color.Y : Color.B;
        }
        if (ignore == Color.Y) {
            if (r == b) {
                return start == Color.R ? Color.R : Color.B;
            }
            return r > b ? Color.R : Color.B;
        }
        if (ignore == Color.B) {
            if (r == y) {
                return start == Color.R ? Color.R : Color.Y;
            }
            return r > y ? Color.R : Color.Y;
        }
        throw new RuntimeException();
    }
    
     public static void main(String[] args) throws Exception {
        String filename = "B-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new B().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
 }
