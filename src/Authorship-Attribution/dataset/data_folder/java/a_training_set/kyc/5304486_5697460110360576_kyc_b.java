package CodeJam;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.nio.file.Files;
 import java.util.List;
 import java.util.Scanner;
 import org.junit.Test;
 import com.google.common.collect.BoundType;
 import com.google.common.collect.TreeMultiset;
 
 public class B extends CodeJamCommons {
 
     @SuppressWarnings("resource")
     @Test
     public void test() throws Exception {
         Scanner in = new Scanner(new FileInputStream(new File(ROOT, "B-small-attempt0.in")));
         File file = new File(ROOT, "data.out");
         PrintStream out = new PrintStream(new FileOutputStream(file));
 
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             int I = in.nextInt();
             int P = in.nextInt();
             int[] req = new int[I];
             for (int i = 0; i < I; i++)
                 req[i] = in.nextInt();
             List<TreeMultiset<Integer>> packs = list();
             for (int i = 0; i < I; i++) {
                 TreeMultiset<Integer> set = TreeMultiset.create();
                 for (int j = 0; j < P; j++)
                     set.add(in.nextInt());
                 packs.add(set);
             }
 
             int i = 1, count = 0;
             while (true) {
                 boolean stop = false;
                 for (int j = 0; j < I; j++)
                     if (packs.get(j).isEmpty() || packs.get(j).lastEntry().getElement() < (9 * i * req[j]) / 10)
                         stop = true;
                 if (stop)
                     break;
                 boolean allGood = true;
                 List<Integer> remove = list();
                 for (int j = 0; j < I; j++) {
                     int k = packs.get(j).tailMultiset((9 * i * req[j] + 9) / 10, BoundType.CLOSED).firstEntry().getElement();
                     if (k > (11 * i * req[j]) / 10) {
                         allGood = false;
                         break;
                     }
                     remove.add(k);
                 }
                 if (allGood) {
                     for (int j = 0; j < I; j++)
                         packs.get(j).remove(remove.get(j));
                     count++;
                 } else {
                     i++;
                 }
             }
 
             out.printf("Case #%d: ", n + 1);
             out.println(count);
         }
 
         out.close();
         System.out.printf("Output to %s (first 10 lines):\n", file);
         Files.lines(file.toPath()).limit(10).forEach(System.out::println);
     }
 }
