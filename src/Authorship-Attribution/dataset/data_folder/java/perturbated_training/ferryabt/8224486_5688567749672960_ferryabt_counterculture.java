package Round1B;
 
 import java.util.ArrayList;
 
 import helpers.AbtRicReader;
 import helpers.AbtRicWriter;
 
 public class CounterCulture {
    public static void main(String[] args) {
         String file = "C:/Users/Ferry/eclipse/CodeJam/in/A-small-attempt0.in";
         AbtRicReader reader = new AbtRicReader();
         int[] cases = reader.getNumberOfCases(file, 1);
         System.out.println(cases[0]);
         ArrayList<Integer[]> listOfCases = reader.readIntFile(file);
         CounterCulture solver = new CounterCulture();
         String[] solution = new String[cases[0]];
         for (int i = 0; i < cases[0]; i++) {
         solution[i] = Integer.toString(solver.solve(listOfCases.get(i)[0]));
         System.out.println("Case #" + (i+1) + ": " + solution[i]);
         }
         AbtRicWriter writer = new AbtRicWriter();
         writer.write("C:/Users/Ferry/eclipse/CodeJam/in/CounterCultureSmall.out",
         solution);
    }
 
    public int solve(int n) {
        int[] count = new int[n + 1];
        count[1] = 1;
        for (int i = 1; i < n; i++) {
            if (count[i + 1] > 0 && count[i + 1] > count[i] + 1) {
                count[i + 1] = count[i] + 1;
            } else if (!(count[i + 1] > 0)) {
                count[i + 1] = count[i] + 1;
            }
            int rev = reverse(i);
            if(rev<=n) {
                if(count[rev]>0&&count[rev]>count[i]+1) {
                    count[rev]=count[i]+1;
                } else if(!(count[rev]>0)) {
                    count[rev]=count[i]+1;
                }
            }
        }
        return count[n];
    }
 
    private static int reverse(int n) {
        String tmp = Integer.toString(n);
        String output = "";
        for (int i = tmp.length() - 1; i >= 0; i--) {
            output += tmp.charAt(i);
        }
        return Integer.parseInt(output);
    }
 }
