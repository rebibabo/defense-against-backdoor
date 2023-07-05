package CodeJam;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.nio.file.Files;
 import java.util.List;
 import java.util.Scanner;
 import org.junit.Test;
 import com.google.common.collect.Multimap;
 
 public class B extends CodeJamCommons {
 
     int numSeats;
 
     @SuppressWarnings("resource")
     @Test
     public void test() throws Exception {
         Scanner in = new Scanner(new FileInputStream(new File(ROOT, "B-small-attempt2.in")));
         File file = new File(ROOT, "data.out");
         PrintStream out = new PrintStream(new FileOutputStream(file));
 
         int numCases = in.nextInt();
         for (int n = 0; n < numCases; n++) {
             numSeats = in.nextInt();
             int numCusts = in.nextInt();
             int numTicks = in.nextInt();
             Multimap<Integer, Integer> customers = mmap();
             Multimap<Integer, Integer> tickets = mmap();
             for (int i = 0; i < numTicks; i++) {
                 int seat = in.nextInt() - 1;
                 int cust = in.nextInt();
                 customers.put(cust, seat);
                 tickets.put(seat, cust);
             }
 
             int numRides = 0;
             for (int cust : customers.keySet())
                 if (customers.get(cust).size() > numRides)
                     numRides = customers.get(cust).size();
             int cumTicks = 0;
             for (int seat = 0; seat < numSeats; seat++) {
                 cumTicks += tickets.get(seat).size();
                 if (ceil(cumTicks, seat + 1) > numRides)
                     numRides = (int) ceil(cumTicks, seat + 1);
             }
 
             int numProms = 0;
             for (int seat = 0; seat < numSeats; seat++)
                 if (tickets.get(seat).size() > numRides)
                     numProms += tickets.get(seat).size() - numRides;
 
             out.printf("Case #%d: ", n + 1);
             out.println(numRides + " " + numProms);
         }
 
         out.close();
         System.out.printf("Output to %s (first 10 lines):\n", file);
         Files.lines(file.toPath()).limit(10).forEach(System.out::println);
     }
 
     boolean add(List<int[]> rides, int seat, int cust) {
         for (int[] ride : rides)
             if (!contains(ride, cust))
                 if (ride[seat] == 0) {
                     ride[seat] = cust;
                     return false;
                 }
         for (int[] ride : rides)
             if (!contains(ride, cust))
                 for (int s = 0; s < seat; s++)
                     if (ride[s] == 0) {
                         ride[s] = cust;
                         return true;
                     }
         int[] newRide = new int[numSeats];
         newRide[seat] = cust;
         rides.add(newRide);
         return false;
     }
 
     boolean contains(int[] ride, int cust) {
         for (int i : ride)
             if (i == cust)
                 return true;
         return false;
     }
 }
