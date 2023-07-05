import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskD {
 
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            byte x = sc.nextByte();
            byte r = sc.nextByte();
            byte c = sc.nextByte();
            switch (x) {
            case 1:
                print(i+1, true);
                break;
            case 2:
                if (r * c % 2 == 0) {
                    print(i+1, true);
                }
                else {
                    print(i+1, false);
                }
                break;
            case 3:
                if (r * c % 3 == 0 && Math.min(r, c) > 1) {
                    print(i+1, true);
                }
                else {
                    print(i+1, false);
                }
                break;
            case 4:
                if (r * c % 4 == 0 && Math.min(r, c) > 2) {
                    print(i+1, true);
                }
                else {
                    print(i+1, false);
                }
                break;
            }
        }
        sc.close();
        System.err.println(System.currentTimeMillis() - time);
    }
    
    private static void print(int caseNum, boolean answer) {
        if (answer) {
            System.out.println("Case #" + caseNum + ": GABRIEL");
        }
        else {
            System.out.println("Case #" + caseNum + ": RICHARD");
        }
        
    }
 
 }
