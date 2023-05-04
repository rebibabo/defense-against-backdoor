import java.io.FileReader;
 import java.io.PrintWriter;
 import java.util.Scanner;
 
 public class A {
    
    public void solve(Scanner scan, PrintWriter out) {
        String s = scan.next();
        String result = lastWord(s);
        System.out.println(result);
        out.println(result);
    }
    
    public String lastWord(String word) {
        if (word.length() == 0) {
            return word;
        }
        int highest = findHighest(word);
        return word.substring(highest, highest+1) + lastWord(word.substring(0, highest)) + word.substring(highest+1);
    }
    
    public int findHighest(String word) {
        char highest = word.charAt(word.length()-1);
        int highestIndex = word.length()-1;
        for (int i = word.length()-1; i >= 0; i--) {
            char current = word.charAt(i);
            if (current > highest) {
                highest = current;
                highestIndex = i;
            }
        }
        return highestIndex;
    }
 
     public static void main(String[] args) throws Exception {
        String filename = "A-small-attempt0";
         Scanner scan = new Scanner(new FileReader(filename + ".in"));
         PrintWriter out = new PrintWriter(filename + ".out");
         int problems = scan.nextInt();
         for (int count = 0; count < problems; count++) {
             System.out.print("Case #" + (count+1) + ": ");
             out.print("Case #" + (count+1) + ": ");
             new A().solve(scan, out);
         }
         out.flush();
         out.close();
         scan.close();
     }
    
 }