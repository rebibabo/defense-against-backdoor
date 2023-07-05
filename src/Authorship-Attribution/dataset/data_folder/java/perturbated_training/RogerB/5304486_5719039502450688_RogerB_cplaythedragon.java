package roud1A;
 
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.util.Scanner;
 
 public class CPlayTheDragon {
     static int dragonHealth;
     static int dragonAttack;
     static int knightHealth;
     static int knightAttack;
     static int buffSize;
     static int debuffSize;
     
     public static void main (String[] args) {
         try (Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)))) {
             int tests = sc.nextInt();
             for (int t = 1; t <= tests; t++) {
                 dragonHealth = sc.nextInt();
                 dragonAttack = sc.nextInt();
                 knightHealth = sc.nextInt();
                 knightAttack = sc.nextInt();
                 buffSize = sc.nextInt();
                 debuffSize = sc.nextInt();
                 
                 long bestFound = Long.MAX_VALUE;
                 
                 
                 long bestDebuff = 0;
                 long bestBuff = 0;
                 
                 for (long debuffCount = 0; debuffCount < bestFound; debuffCount++) {
                     for (long buffCount = 0; debuffCount + buffCount < bestFound; buffCount++) {
                         long movesRequired = movesRequired(debuffCount, buffCount);
                         if (movesRequired != -1 && movesRequired < bestFound) {
                             bestFound = movesRequired;
                             bestDebuff = debuffCount;
                             bestBuff = buffCount;
                         }
                         if (buffSize == 0) break;
                         if (buffCount >= 5 && bestFound == Long.MAX_VALUE) break; 
                     }
                     if (debuffSize == 0 || debuffSize * debuffCount >= knightAttack) break;
                     if (debuffCount >= 5 && bestFound == Long.MAX_VALUE) break; 
                 }
                 
                 
                 System.out.printf("Case #%d: %s%n", t, bestFound == Long.MAX_VALUE ? "IMPOSSIBLE" : bestFound);
             }
         }
     }
     
     static long movesRequired(long debuffCount, long buffCount) {
         long[] debuffResult = debuffStageCures(debuffCount);
         long debuffMoves = debuffResult[0];
         long finalKnightStrength = debuffResult[1];
         long numberOfHitsCanTake = debuffResult[2];
         
         if (debuffMoves == -1) return -1;
         
         long buffedStrength = dragonAttack + (buffCount * buffSize);
         long requiredAttacksToKill = (long)Math.ceil(knightHealth / (double)buffedStrength);
         
         long requiredCures = requiredCures(numberOfHitsCanTake, buffCount + requiredAttacksToKill, finalKnightStrength);
         if (requiredCures == -1) return -1;
         
         long totalMoves = debuffMoves + buffCount + requiredAttacksToKill + requiredCures;
         
         return totalMoves;
     }
     
     
     static long[] debuffStageCures(long debuffCount) {
         
         long movesTaken = 0;
         long debuffsDone = 0;
         
         long currentHealth = dragonHealth;
         long currentKnightAttack = knightAttack;
         
         boolean curedLastRound = false; 
         
         while (debuffsDone < debuffCount) {
             long potentialKnightAttack = currentKnightAttack - debuffSize;
             if (potentialKnightAttack < 0) potentialKnightAttack = 0;
             if (currentHealth <= potentialKnightAttack) { 
                 currentHealth = dragonHealth;
                 if (curedLastRound) return new long[] {-1, 0, 0}; 
                 curedLastRound = true;
             } else {
                 currentKnightAttack = potentialKnightAttack;
                 debuffsDone++;
                 curedLastRound = false;
             }
             currentHealth -= currentKnightAttack;
             if (currentHealth <= 0) return new long[] {-1, 0, 0}; 
             movesTaken++;
         }
         
         long numberOfHitsCanTake;
         if (currentKnightAttack == 0) {
             numberOfHitsCanTake = Long.MAX_VALUE;
         } else {
             numberOfHitsCanTake = currentHealth / currentKnightAttack;
             if (currentHealth % currentKnightAttack == 0) numberOfHitsCanTake--;
         }
         
         return new long[] {movesTaken, currentKnightAttack, numberOfHitsCanTake};
     }
     
     static long requiredCures(long numberOfHitsCanTake, long numberOfMovesActing, long debuffedKnightStrength) {
         if (debuffedKnightStrength == 0) return 0;
         
         
         long numberOfKnightAttacks = numberOfMovesActing - 1;
         
         if (numberOfHitsCanTake >= numberOfKnightAttacks) return 0;
         
         long healthAfterCureAndAttack = dragonHealth - debuffedKnightStrength;
         long attacksCanTakeAfterCureAndAttack = healthAfterCureAndAttack / debuffedKnightStrength;
         if (healthAfterCureAndAttack % debuffedKnightStrength == 0) attacksCanTakeAfterCureAndAttack--;
         
         if (attacksCanTakeAfterCureAndAttack <= 0) return -1;
         
         long numberToHealFrom = numberOfKnightAttacks;
         numberToHealFrom -= numberOfHitsCanTake;
         long requiredCures = 1;
         requiredCures += (numberToHealFrom - 1) / attacksCanTakeAfterCureAndAttack;
         
         return requiredCures;
     }
 }
