import java.util.ArrayList;
 import java.util.Collections;
 import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int[] P = new int[sc.nextInt()];
            for (int j = 0; j < P.length; j++) {
                P[j] = sc.nextInt();
            }
            System.out.print("Case #" + i + ":");
            if (P.length == 2) {
                while (P[0] > 0) {
                    if (P[0] > P[1]) {
                        System.out.print(" A");
                        P[0]--;
                    } else if (P[1] > P[0]) {
                        System.out.print(" B");
                        P[1]--;
                    } else {
                        System.out.print(" AB");
                        P[0]--;
                        P[1]--;
                    }
                }
            } else {
                ArrayList<Party> parties = new ArrayList<Party>();
                for (int j = 0; j < P.length; j++) {
                    parties.add(new Party((char) ('A' + j), P[j]));
                }
                Collections.sort(parties);
                while (parties.get(0).senators != parties.get(1).senators) {
                    System.out.print(" " + parties.get(0).name);
                    parties.get(0).senators--;
                }
                while (parties.get(2).senators > 0) {
                    System.out.print(" " + parties.get(2).name);
                    parties.get(2).senators--;
                }
                while (parties.get(0).senators > 0) {
                    System.out.print(" " + parties.get(0).name + parties.get(1).name);
                    parties.get(0).senators--;
                }
            }
            System.out.println();
        }
        sc.close();
    }
 
    private static class Party implements Comparable<Party> {
        private char name;
        private int senators;
 
        private Party(char name, int senators) {
            this.name = name;
            this.senators = senators;
        }
 
        public int compareTo(Party o) {
            return o.senators - senators;
        }
 
    }
 }