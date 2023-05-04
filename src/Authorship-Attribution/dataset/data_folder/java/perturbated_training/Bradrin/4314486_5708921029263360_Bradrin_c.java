import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class C {
    
    public void solve(Scanner scan, PrintWriter out) {
        int jackets = scan.nextInt();
        int pants = scan.nextInt();
        int shirts = scan.nextInt();
        int repeats = scan.nextInt();
        int count = 0;
        int[][] outfits = new int[1000][3];
        for (int i = 0; i < jackets; i++) {
            for (int j = 0; j < pants; j++) {
                for (int k = 0; k < shirts; k++) {
                    outfits[count] = new int[] {i,j,k};
                    count++;
                }
            }
        }
        for (int i = 0; i < count; i++) {
            int jacket = outfits[i][0];
            int pant = outfits[i][1];
            int shirt = outfits[i][2];
            int repeat1 = 0;
            int repeat2 = 0;
            int repeat3 = 0;
            for (int j = 0; j < count; j++) {
                if (outfits[j][0] == jacket && outfits[j][1] == pant) {
                    repeat1++;
                    if (repeat1 > repeats) {
                        outfits[j] = new int[] {-1, -1, -1};
                    }
                }
                if (outfits[j][0] == jacket && outfits[j][2] == shirt) {
                    repeat2++;
                    if (repeat2 > repeats) {
                        outfits[j] = new int[] {-1, -1, -1};
                    }
                }
                if (outfits[j][1] == pant && outfits[j][2] == shirt) {
                    repeat3++;
                    if (repeat3 > repeats) {
                        outfits[j] = new int[] {-1, -1, -1};
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < count; i++) {
            if (outfits[i][0] != -1) {
                result++;
            }
        }
        System.out.println(result);
        out.println(result);
 
         for (int i = 0; i < count; i++) {
             if (outfits[i][0] != -1) {
                 System.out.println((outfits[i][0] + 1) + " " + (outfits[i][1] + 1) + " " + (outfits[i][2] + 1));
             }
         }
    }
 
    public static void main(String[] args) throws Exception {
        String filename = "C-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new C().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }