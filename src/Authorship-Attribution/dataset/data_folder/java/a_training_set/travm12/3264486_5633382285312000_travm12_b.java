import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class b {
    public static void main(String[] Args) throws Exception {
        
        Scanner sc = new Scanner(new File("B-small-attempt0.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(new File("b.out"))));
        
        int cc = 0;
        int t = sc.nextInt();
        while (t-- > 0) {
            
            String x = sc.next();
 
            
            for (int i = 0; i < x.length(); i++)
                for (int j = 0; j + 1 < x.length(); j++)
                    if (x.charAt(j) > x.charAt(j + 1)) {
                        String nx = x.substring(0, j);
                        nx = nx + (char) (x.charAt(j) - 1);
                        while (nx.length() < x.length())
                            nx = nx + "9";
                        x = nx;
                    }
 
            
            while (x.length() > 1 && x.charAt(0) == '0')
                x = x.substring(1);
 
            
            out.printf("Case #%d: %s%n", ++cc, x);
        }
        out.close();
    }
 }
