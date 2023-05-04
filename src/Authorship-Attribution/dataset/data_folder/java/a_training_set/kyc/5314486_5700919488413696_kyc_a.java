package CodeJam;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.nio.file.Files;
 import java.util.Scanner;
 import org.junit.Test;
 
 public class A extends CodeJamCommons {
 
     @SuppressWarnings("resource")
     @Test
     public void test() throws Exception {
         Scanner in = new Scanner(new FileInputStream(new File(ROOT, "A-small-attempt1.in")));
         File file = new File(ROOT, "data.out");
         PrintStream out = new PrintStream(new FileOutputStream(file));
 
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             int N = in.nextInt();
             int P = in.nextInt();
             int[] G = new int[N];
             int[] counts = new int[P];
             int sum = 0;
             for (int i = 0; i < N; i++) {
                 G[i] = in.nextInt();
                 counts[G[i] % P]++;
                 sum += G[i];
             }
             int ans = 1;
             if (P == 2) {
                 ans += counts[0] + counts[1] / 2;
             } else if (P == 3) {
                 int k = Math.min(counts[1], counts[2]);
                 ans += counts[0] + k;
                 counts[1] -= k;
                 counts[2] -= k;
                 ans += counts[1] / 3 + counts[2] / 3;
             } else if (P == 4) {
                 int k = Math.min(counts[1], counts[3]);
                 ans += counts[0] + k + counts[2] / 2;
                 counts[1] -= k;
                 counts[3] -= k;
                 counts[2] -= 2 * (counts[2] / 2);
                 if (counts[1] > 0) {
                     ans += Math.min(counts[2], counts[1] / 2);
                     counts[1] -= 2 * Math.min(counts[2], counts[1] / 2);
                     ans += counts[1] / 4;
                 } else {
                     ans += Math.min(counts[2], counts[3] / 2) + counts[3] / 4;
                     counts[3] -= 2 * Math.min(counts[2], counts[3] / 2);
                     ans += counts[3] / 4;
                 }
             } else
                 die();
             if (sum % P == 0)
                 ans--;
 
             out.printf("Case #%d: ", n + 1);
             out.println(ans);
         }
 
         out.close();
         System.out.printf("Output to %s (first 10 lines):\n", file);
         Files.lines(file.toPath()).limit(10).forEach(System.out::println);
     }
 }
