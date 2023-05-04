import java.util.HashSet;
 import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt(), min = Integer.MAX_VALUE;
            sc.nextLine();
            HashSet<String> set = new HashSet<String>();
            for (String word : sc.nextLine().split(" ")) {
                set.add(word);
            }
            String[] sentence = sc.nextLine().split(" ");
            String[][] words = new String[N - 2][];
            for (int j = 0; j < N - 2; j++) {
                words[j] = sc.nextLine().split(" ");
            }
            for (int j = 0; j < 1 << N - 2; j++) {
                HashSet<String> clone = (HashSet<String>) set.clone();
                for (int k = 0; k < N - 2; k++) {
                    if ((j & 1 << k) == 0) {
                        for (String word : words[k]) {
                            clone.add(word);
                        }
                    }
                }
                int count = 0;
                for (String word : sentence) {
                    count += clone.remove(word) ? 1 : 0;
                }
                for (int k = 0; k < N - 2; k++) {
                    if ((j & 1 << k) != 0) {
                        for (String word : words[k]) {
                            count += clone.remove(word) ? 1 : 0;
                        }
                    }
                }
                min = Math.min(min, count);
            }
            System.out.println("Case #" + i + ": " + min);
        }
        sc.close();
    }
 }