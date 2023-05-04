package round1a;
 
 import java.util.Scanner;
 
 public class A {
    static boolean empty(char[] arr) {
        boolean empty = true;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '?' && empty) {
                for (int j=0; j < i; j++) {
                    arr[j] = arr[i];
                }
                empty = false;
            } else if (arr[i] == '?' && !empty) {
                arr[i] = arr[i-1];
            }
        }
        return empty;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int caze = 1; caze <= cases; caze++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            char[][] table = new char[r][];
            for (int i = 0; i < table.length; i++) {
                table[i] = sc.next().toCharArray();
            }
            int first;
            for (first = 0; empty(table[first]); first++);
            for (int i = 0; i < first; i++) table[i] = table[first];
            for (int i = first + 1; i < table.length; i++) if (empty(table[i])) {
                table[i] = table[i-1];
            }
            
            System.out.println("Case #" + caze + ":");
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table[i].length; j++) {
                    System.out.print(table[i][j]);
                }
                System.out.println();
            }
        }
    }
 
 }
