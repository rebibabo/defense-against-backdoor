package round2;
 
 import java.util.Arrays;
 import java.util.Scanner;
 
 public class BRollerCoasterScheduling {
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         
         int tests = sc.nextInt();
         for (int t = 1; t <= tests; t++) {
             int seatCount = sc.nextInt();
             int customerCount = sc.nextInt(); 
             int ticketCount = sc.nextInt();
             int[][] tickets = new int[customerCount][seatCount];
             for (int x = 0; x < ticketCount; x++) {
                 int seat = sc.nextInt() - 1;
                 int customer = sc.nextInt() - 1;
                 tickets[customer][seat]++;
             }
 
             int aCount = Arrays.stream(tickets[0]).sum();
             int bCount = Arrays.stream(tickets[1]).sum();
             
             int aMinCount = tickets[0][0];
             int bMinCount = tickets[1][0];
             int minRequired = Math.max(aMinCount + bMinCount, Math.max(aCount, bCount));
             
             int totalConflicts = 0;
             for (int x = 0; x < seatCount; x++) {
                 int aOther = aCount - tickets[0][x];
                 int bOther = bCount - tickets[1][x];
                 int aRemaining = Math.max(0, tickets[0][x] - bOther);
                 int bRemaining = Math.max(0, tickets[1][x] - aOther);
                 int conflicts = Math.min(aRemaining, bRemaining);
                 totalConflicts += conflicts;
             }
             
             System.out.printf("Case #%d: %d %d%n", t, minRequired, totalConflicts);
         }
         
         sc.close();
     }
 }
