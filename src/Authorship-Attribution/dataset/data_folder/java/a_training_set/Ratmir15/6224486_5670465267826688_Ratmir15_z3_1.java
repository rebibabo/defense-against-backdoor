package c2015_c.c2015_r1;
 
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileWriter;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Scanner;
 
 public class Z3_1 {
 
     public static final int INT = 100;
 
     public enum S {
         ONE, I, J, K;
     }
 
     private static class State {
         boolean positive;
         S state;
 
         public State(boolean positive, S state) {
             this.positive = positive;
             this.state = state;
         }
 
         State multiply(S s) {
             switch (this.state) {
                 case ONE: {
                     switch (s) {
                         case ONE: {
                             return new State(this.positive, S.ONE);
                         }
                         case I: {
                             return new State(this.positive, S.I);
                         }
                         case J: {
                             return new State(this.positive, S.J);
                         }
                         case K: {
                             return new State(this.positive, S.K);
                         }
                     }
                     break;
                 }
                 case I: {
                     switch (s) {
                         case ONE: {
                             return new State(this.positive, S.I);
                         }
                         case I: {
                             return new State(!this.positive, S.ONE);
                         }
                         case J: {
                             return new State(this.positive, S.K);
                         }
                         case K: {
                             return new State(!this.positive, S.J);
                         }
                     }
                     break;
                 }
                 case J: {
                     switch (s) {
                         case ONE: {
                             return new State(this.positive, S.J);
                         }
                         case I: {
                             return new State(!this.positive, S.K);
                         }
                         case J: {
                             return new State(!this.positive, S.ONE);
                         }
                         case K: {
                             return new State(this.positive, S.I);
                         }
                     }
                     break;
                 }
                 case K: {
                     switch (s) {
                         case ONE: {
                             return new State(this.positive, S.K);
                         }
                         case I: {
                             return new State(this.positive, S.J);
                         }
                         case J: {
                             return new State(!this.positive, S.I);
                         }
                         case K: {
                             return new State(!this.positive, S.ONE);
                         }
                     }
                     break;
                 }
             }
             return null;
         }
 
         public State multiply(char aChar) {
             switch (aChar) {
                 case 'i': {
                     return multiply(S.I);
                 }
                 case 'j': {
                     return multiply(S.J);
                 }
                 case 'k': {
                     return multiply(S.K);
                 }
             }
             return null;
         }
     }
 
     public static void main(String[] args) throws Exception {
         FileWriter fw = new FileWriter("C:\\output.txt");
         BufferedWriter out = new BufferedWriter(fw);
         
         String pathname = "C:\\Users\\YC14rp1\\Downloads\\C-small-attempt0.in";
         
         Scanner scanner = new Scanner(new File(pathname));
         int tn = scanner.nextInt();
         scanner.nextLine();
         for (int ti = 1; ti <= tn; ti++) {
             int l = scanner.nextInt();
             int x = scanner.nextInt();
             String next = scanner.next();
             char[] chars = next.toCharArray();
             boolean flag = false;
             State state = new State(true, S.ONE);
             stop:
             for (int i = 0; i < x * chars.length; i++) {
                 state = state.multiply(chars[i%l]);
                 if (state.positive && state.state.equals(S.I)) {
                     State state_j = new State(state.positive, S.ONE);
                     for (int j = i+1; j < x * chars.length; j++) {
                         state_j = state_j.multiply(chars[j % l]);
                         if (state_j.positive && state_j.state.equals(S.J)) {
                             State state_k = new State(state.positive, S.ONE);
                             for (int k = j+1; k < x * chars.length; k++) {
                                 state_k = state_k.multiply(chars[k % l]);
                             }
                             if (state_k.positive && state_k.state.equals(S.K)) {
                                 flag = true;
                                 break stop;
                             } else {
                                 flag = false;
                                 break stop;
                             }
                             
                         }
                     }
                 }
             }
             String s;
             if (flag) {
                 s = "YES";
             } else {
                 s = "NO";
             }
             s = "Case #" + ti + ": " + s;
             System.out.println(s);
             out.write(s);
             out.write("\n");
         }
         out.close();
     }
 
 
     private static int getMinutes(int d, int[] a, Integer sum) {
         if (sum == 0) {
             return 0;
         }
         int[] ints = Arrays.copyOf(a, INT);
         int[] ints2 = Arrays.copyOf(a, INT);
         int mx = 0;
         int mxi = -1;
         int sum2 = sum;
         for (int i = 0; i < d; i++) {
             if (ints2[i] > 0) {
                 ints2[i]--;
                 sum2--;
             }
             if (ints[i] > mx) {
                 mx = ints[i];
                 mxi = i;
             }
         }
         int min = getMinutes(d, ints2, sum2) + 1;
         if (mxi > -1) {
             
             int mxx = mx / 2;
             if (mx % 2 == 1) {
                 mxx += 1;
             }
             for (int i = 2; i <= mxx; i++) {
                 ints[d] = i;
                 ints[mxi] = mx - i;
                 int m2 = getMinutes(d + 1, ints, sum) + 1;
                 if (m2 < min) {
                     min = m2;
                 }
             }
         }
         
         return min;
     }
 
 }
