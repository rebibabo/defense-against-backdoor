import java.util.Scanner;
 
 public class C {
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int numCases = sc.nextInt();
         outer:
         for (int caseNum = 1; caseNum <= numCases; caseNum++) {
             long Hd = sc.nextLong();
             long Ad = sc.nextLong();
             long Hk = sc.nextLong();
             long Ak = sc.nextLong();
             long B = sc.nextLong();
             long D = sc.nextLong();
 
             System.out.print("Case #" + caseNum + ": ");
 
             if (Ad >= Hk) {
                 System.out.println(1);
                 continue;
             }
 
 
 
 
 
 
 
 
 
 
             long minABTurns = Long.MAX_VALUE;
             if (B > 0) {
                 double approxBuff = (Math.sqrt(B * Hk) - Ad) / B;
                 long low = Math.max(0, (long) Math.floor(approxBuff - 5));
                 for (long buffTurns = low; buffTurns <= low + 10; buffTurns++) {
                     long attackPower = Ad + buffTurns * B;
                     long attackTurns = (Hk + attackPower - 1) / attackPower;
                     minABTurns = Math.min(minABTurns, attackTurns + buffTurns);
                 }
             } else {
                 minABTurns = (Hk + Ad - 1) / Ad;
             }
 
             long minTotalTurns = Long.MAX_VALUE;
             long maxDebuffTurns = D == 0 ? 0 : (Ak + D - 1) / D;
             debuff:
             for (int debuffTurns = 0; debuffTurns <= maxDebuffTurns; debuffTurns++) {
                 int healTurns = 0;
                 long attack = Ak;
                 long currHealth = Hd;
                 for (int i = 0; i < debuffTurns; i++) {
                     if (attack - D >= currHealth) {
                         healTurns++;
                         currHealth = Hd - attack;
                         if (attack - D >= currHealth) {
                             continue debuff;
                         }
                     }
                     attack = Math.max(0, attack - D);
                     currHealth -= attack;
                 }
                 for (int i = 0; i < minABTurns - 1; i++) {
                     if (attack >= currHealth) {
                         healTurns++;
                         currHealth = Hd - attack;
                         if (attack >= currHealth) {
                             continue debuff;
                         }
                     }
                     currHealth -= attack;
                 }
                 minTotalTurns = Math.min(minTotalTurns, healTurns + debuffTurns + minABTurns);
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
             }
             if (minTotalTurns == Long.MAX_VALUE) {
                 System.out.println("IMPOSSIBLE");
             } else {
                 System.out.println(minTotalTurns);
             }
         }
     }
 }
