import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.PrintWriter;
 
 public class StandingOvation {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        try {
            String line = br.readLine();
            int max, actSila, result, count = Integer.parseInt(line);
            for(int i = 0; i < count; i++) {
                line = br.readLine();
                max = parse(line);
                actSila = 0; result = 0;
                for (int ii = 0; ii <= max; ii++) {
                    int bonus = 0;
                    if (actSila - ii < 0) {
                        bonus = ii - actSila;
                        result += ii - actSila;
                    }
                    actSila += bonus + parse2(line, ii);
                }
                writer.println("Case #" + (i + 1) + ": " + result);
            }
        } finally {
            br.close();
        }
        writer.close();
    }
 
    private static int parse(String s) {
        return Integer.parseInt(s.substring(0, s.indexOf(' ')));
    }
 
    private static int parse2(String s, int i) {
        i++;
        return Integer.parseInt(s.substring(s.indexOf(' ') + i, s.indexOf(' ') + i + 1));
    }
 }
