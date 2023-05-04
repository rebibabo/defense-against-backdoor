package codejam2015;
 
 import java.util.Scanner;
 
 public class ProbD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numcases = sc.nextInt();
        for (int cas = 1; cas <= numcases; cas++) {
            System.out.print("Case #" + cas + ": ");
            int X = sc.nextInt();
            int R = sc.nextInt();
            int C = sc.nextInt();
            if (X == 1) {
                System.out.println("GABRIEL");
                continue;
            }
            if (X == 2) {
                if ((R * C) % 2 == 0) {
                    System.out.println("GABRIEL");
                } else {
                    System.out.println("RICHARD");
                }
                continue;
            }
            if (X == 3) {
                if ((R == 1) || (C == 1)) {
                    System.out.println("RICHARD");
                    continue;
                }
                if (R == 2) {
                    if (C == 3) {
                        System.out.println("GABRIEL");
                    } else {
                        System.out.println("RICHARD");
                    }
                    continue;
                }
                if (R == 3) {
                    System.out.println("GABRIEL");
                    continue;
                }
                if (R == 4) {
                    if (C == 3) {
                        System.out.println("GABRIEL");
                    } else {
                        System.out.println("RICHARD");
                    }
                    continue;
                }
            }
            if (X == 4) {
                if ((R < 3) || (C < 3)) {
                    System.out.println("RICHARD");
                    continue;
                }
                if (R == 3) {
                    if (C == 3) {
                        System.out.println("RICHARD");
                    } else {
                        System.out.println("GABRIEL");
                    }
                    continue;
                }
                if (R == 4) {
                    System.out.println("GABRIEL");
                    continue;
                }
            }
        }
    }
 }
