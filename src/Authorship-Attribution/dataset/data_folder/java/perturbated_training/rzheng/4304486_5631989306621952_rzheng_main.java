import java.util.Scanner;
 
 public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 1; i <= T; i++) {
            String S = sc.next();
            String word = "" + S.charAt(0);
            for (int j = 1; j < S.length(); j++) {
                if (S.charAt(j) >= word.charAt(0)) {
                    word = S.charAt(j) + word;
                } else {
                    word += S.charAt(j);
                }
            }
            System.out.println("Case #" + i + ": " + word);
        }
        sc.close();
    }
 }