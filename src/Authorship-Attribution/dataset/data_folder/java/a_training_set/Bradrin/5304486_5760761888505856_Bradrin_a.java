import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
 
    private void solve(Scanner scan, PrintWriter out) {
        int r = scan.nextInt();
        int c = scan.nextInt();
        
        char[][] cake = new char[r][c];
        
        for (int i = 0; i < r; i++) {
            String line = scan.next();
            for (int j = 0; j < c; j++) {
                cake[i][j] = line.charAt(j);
            }
        }
        
        for (int i = 0; i < r; i++) {
            char initial = '?';
            for (int j = 0; j < c; j++) {
                if (cake[i][j] != '?') {
                    initial = cake[i][j];
                }
                cake[i][j] = initial;
            }
            for (int j = c-1; j >= 0; j--) {
                if (cake[i][j] != '?') {
                    initial = cake[i][j];
                }
                cake[i][j] = initial;
            }
        }
 
        for (int i = 0; i < c; i++) {
            char initial = '?';
            for (int j = 0; j < r; j++) {
                if (cake[j][i] != '?') {
                    initial = cake[j][i];
                }
                cake[j][i] = initial;
            }
            for (int j = r-1; j >= 0; j--) {
                if (cake[j][i] != '?') {
                    initial = cake[j][i];
                }
                cake[j][i] = initial;
            }
        }
        
        System.out.println();
        out.println();
        
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(cake[i][j]);
                out.print(cake[i][j]);
            }
            System.out.println();
            out.println();
        }
    }
    
    public boolean isInitial(char[][] cake, int row) {
        for (int i = 0; i < cake[row].length; i++) {
            if (cake[row][i] != '?') {
                return true;
            }
        }
        return false;
    }
    
     public static void main(String[] args) throws Exception {
        String filename = "A-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new A().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
 }
