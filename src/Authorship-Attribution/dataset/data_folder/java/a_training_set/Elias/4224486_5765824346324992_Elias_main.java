import java.io.File;
 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Random;
 import java.util.Scanner;
 
 
 public class Main {
    public static void main(String[] args) throws IOException {
        new Main();
    }
 
    public Main() throws IOException {
        
        Scanner sc = new Scanner(new File("B-small-attempt4.in"));
        PrintWriter out = new PrintWriter(new File("B-small-attempt4.out"));
 
        int amountOfTasks = sc.nextInt();
        for (int task = 1; task <= amountOfTasks; task++) {
            long B = sc.nextInt();
            long N = sc.nextInt();
            ArrayList<Barber> barbers = new ArrayList<Barber>();
            HashMap<Integer, Barber> map = new HashMap<Integer, Barber>();
            for (int i = 0; i < B; i++) {
                Barber b = new Barber(i+1, sc.nextInt());
                barbers.add(b);
                map.put(i+1, b);
            }
            
            int pos;
            while(true) {
                
                int biggestBarber = -1;
                long biggestTime = -1;
                for (Barber barber : barbers) {
                    if (barber.time > biggestTime) {
                        biggestTime = barber.time;
                        biggestBarber = barber.nb;
                    }
                }
                long min = 0;
                long max = N/B;
                long total = 0;
                while (min != max) {
                    
                    long mid;
                    if (max-min == 1) {
                        mid = max;
                    }
                    else mid = (min+max)/2;
                    total = 0;
                    for (Barber barber: barbers) {
                        if (barber.timeLeft > 0 && mid>0) total++; 
                        total += (mid*biggestTime-barber.timeLeft)/barber.time;
                    }
                    if (total >= N) {
                        max = mid-1;
                    } else 
                        min = mid;
                    
                }
                long amount = min;
                long timePassed = amount*biggestTime;
                total = 0;
                for (Barber barber: barbers) {
                    if (barber.timeLeft > 0 && amount>0) total++; 
                    total += (timePassed-barber.timeLeft)/barber.time;
                }
                
                N-=total;
                N--;
                for (Barber barber: barbers) {
                    long timeLastCut = ((timePassed-barber.timeLeft)%barber.time);
                    if (timeLastCut > 0)
                        barber.timeLeft = barber.time-timeLastCut;
                    else 
                        barber.timeLeft = 0;
                }
                if (N==0) {
                    pos = biggestBarber;
                    break;
                }
                barbers.remove(map.get(biggestBarber));
                B--;
            }
            
            String solution = "Case #" + task + ": " + pos;
            System.out.println(solution);
            out.println(solution);
        }
 
        out.close();
        sc.close();
    }
 
    class Barber{
        int nb;
        long time;
        long timeLeft = 0;
        public Barber(int nb, long time) {
            this.nb = nb;
            this.time = time;
        }
    }
 }