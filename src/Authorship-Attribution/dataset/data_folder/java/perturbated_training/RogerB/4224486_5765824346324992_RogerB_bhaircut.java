package Round1A;
 
 import java.util.HashSet;
 import java.util.Scanner;
 import java.util.PriorityQueue;
 import java.util.Set;
 
 public class BHaircut {
     static class Barber implements Comparable<Barber>{
         long index;
         long cutTime;
         long freeTime;
         public Barber (long index, long cutTime, long freeTime) {
             this.index = index;
             this.cutTime = cutTime;
             this.freeTime = freeTime;
         }
         public int compareTo(Barber b) {
             if (this.freeTime < b.freeTime) {
                 return -1;
             } else if (this.freeTime > b.freeTime) {
                 return 1;
             }
             else {
                 if (this.index < b.index) {
                     return -1;
                 } else if (this.index > b.index) {
                     return 1;
                 } else {
                     return 0;
                 }
             }
         }
         
     }
     public static void main (String[] args) {
         Scanner sc = new Scanner(System.in);
         long cases = sc.nextInt();
         for (long t = 1; t <= cases; t++) {
             long barberCount = sc.nextInt();
             long placeInLine = sc.nextInt();
             Set<Barber> barberSet = new HashSet<Barber>();
             double patronsPerMinute = 0;
             for (long x = 1; x <= barberCount; x++) {
                 long time = sc.nextInt();
                 barberSet.add(new Barber(x, time, 0));
                 patronsPerMinute += 1 / (double) time;
             }
             long fastForwardTime = Math.max((long)Math.floor((placeInLine - barberCount) / patronsPerMinute) - barberCount - 1, 0);
             PriorityQueue<Barber> barbers = new PriorityQueue<Barber>();
             long patronsServed = 0;
             for (Barber barber : barberSet) {
                 long served = (fastForwardTime / barber.cutTime) + 1;
                 if (fastForwardTime == 0) served = 0;
                 patronsServed += served;
                 barbers.add(new Barber(barber.index, barber.cutTime, served * barber.cutTime));
             }
             placeInLine -= patronsServed;
             Barber currBarber = barbers.peek();
             while (placeInLine > 0) {
                 currBarber = barbers.poll();
                 barbers.add(new Barber(currBarber.index, currBarber.cutTime, currBarber.freeTime + currBarber.cutTime));
                 placeInLine--;
             }
             System.out.printf("Case #%d: %d%n", t, currBarber.index);
         }
         sc.close();
     }
 }
