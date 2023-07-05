import java.util.Scanner;
 
 public class C {
     
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int T = sc.nextInt();
         for (int caseNum = 1; caseNum <= T; caseNum++) {
             int L = sc.nextInt();
             int X = sc.nextInt();
             String str = sc.next();
             int[] arr = new int[L + 1];
             for (int t = 0; t < L; t++) {
                 switch (str.charAt(t)) {
                 case 'i':
                     arr[t] = 1;
                     break;
                 case 'j':
                     arr[t] = 2;
                     break;
                 case 'k':
                     arr[t] = 3;
                     break;
                 }
             }
 
             System.out.print("Case #" + caseNum + ": ");
             if (isPossible(L, X, arr)) {
                 System.out.println("YES");
             } else {
                 System.out.println("NO");
             }
         }
     }
 
     private static boolean isPossible(int L, int X, int[] arr) {
         int[] left = new int[L];
         left[0] = arr[0];
         for (int t = 1; t < L; t++) {
             left[t] = multiply(left[t - 1], arr[t]);
         }
         if (X % 4 == 0) {
             return false;
         } else if (X % 4 == 2) {
             if (left[L - 1] == 0 || left[L - 1] == 4) {
                 return false;
             }
         } else {
             if (left[L - 1] != 4) {
                 return false;
             }
         }
         
         int firstIIndex = getFirstIIndex(L, left);
         if (firstIIndex == -1) {
             return false;
         }
         int[] right = new int[L];
         right[L - 1] = arr[L - 1];
         for (int t = L - 2; t >= 0; t--) {
             right[t] = multiply(arr[t], right[t + 1]);
         }
         int lastJIndex = getLastKIndex(L, X, right);
         if (lastJIndex == -1) {
             return false;
         }
         return firstIIndex < lastJIndex;
     }
 
     private static int getLastKIndex(int L, int X, int[] right) {
         int rightProd = 0;
         for (int i = 0; i < 4; i++) {
             for (int j = 0; j < L; j++) {
                 if (multiply(right[L - 1 - j], rightProd) == 3) {
                     return L * X - (i * L + j);
                 }
             }
             rightProd = multiply(right[0], rightProd);
         }
         return -1;
     }
 
     private static int getFirstIIndex(int L, int[] left) {
         int leftProd = 0;
         for (int i = 0; i < 4; i++) {
             for (int j = 0; j < L; j++) {
                 int val = multiply(leftProd, left[j]);
                 if (val == 1) {
                     return i * L + j;
                 }
             }
             leftProd = multiply(leftProd, left[L - 1]);
         }
         return -1;
     }
 
     static int[][] mult = { { 0, 1, 2, 3 }, { 1, 4, 3, 6 }, { 2, 7, 4, 1 }, { 3, 2, 5, 4 } };
 
     private static int multiply(int left, int right) {
         if (left < 4) {
             if (right < 4) {
                 return mult[left][right];
             } else {
                 return (4 + mult[left][right - 4]) % 8;
             }
         } else {
             if (right < 4) {
                 return (4 + mult[left - 4][right]) % 8;
             } else {
                 return mult[left - 4][right - 4];
             }
         }
     }
 
     private static String toString(int x) {
         switch (x) {
         case 0:
             return "1";
         case 1:
             return "i";
         case 2:
             return "j";
         case 3:
             return "k";
         case 4:
             return "-1";
         case 5:
             return "-i";
         case 6:
             return "-j";
         case 7:
             return "-k";
         default:
             return null;
         }
     }
 }
