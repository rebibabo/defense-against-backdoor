import java.io.BufferedInputStream;
 import java.util.Scanner;
 
 
 public class TaskA {
 
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int s = sc.nextInt();
            String values = sc.nextLine().substring(1);
            byte[] shyness = new byte[s+1];
            for (int j = 0; j <= s; j++) {
                shyness[j] = Byte.valueOf(values.substring(j, j + 1));
            }
            int sum = 0;
            int invited = 0;
            for (int j = 0; j <= s; j++) {
                if (sum < j) {
                    invited += j - sum;
                    sum = j + shyness[j];
                }
                else {
                    sum += shyness[j];
                }
            }
            print(i+1, invited);
        }
        sc.close();
        System.err.println(System.currentTimeMillis() - time);
    }
    
    private static void print(int caseNum, int answer) {
        System.out.println("Case #" + caseNum + ": " + answer);
    }
 
 }
