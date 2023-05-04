package abtric.qualification;
 
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.List;
 
 import abtric.utility.Writer;
 
 public class RevengeOfThePancakes {
    public static void main(String[] args) {
        
        String stringPath = "B-small-attempt1.in";
        
        try {
            final List<String> inputFile = Files
                    .readAllLines(FileSystems.getDefault().getPath("in\\RevengeOfThePancakes", stringPath));
            
            final int T = Integer.parseInt(inputFile.get(0));
            String[] results = new String[T];
            for (int i = 0; i < T; i++) {
                final String S = inputFile.get(i + 1);
                int count = 0;
                String current = S;
                while (notDone(current)) {
                    for (int j = current.length() - 1; j >= 1; j--) {
                        if (current.charAt(j) == '+') {
                            continue;
                        }
                        if (current.charAt(0) == '+') {
                            int start = 0;
                            while (current.charAt(start + 1) == '+') {
                                start++;
                            }
                            current = flip(current, start);
                            count++;
                        }
                        current = flip(current, j);
                        count++;
                    }
                    if (current.charAt(0) == '-') {
                        current = flip(current, 0);
                        count++;
                    }
                }
                results[i] = Integer.toString(count);
            }
            
            Writer.write("out\\RevengeOfThePancakes-small.out", results);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private static boolean notDone(final String s) {
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            if (c == '-') {
                return true;
            }
        }
        return false;
    }
 
    private static String flip(final String s, final int i) {
        char[] output = s.toCharArray();
        for (int j = 0; j <= i; j++) {
            if (s.charAt(i - j) == '+') {
                output[j] = '-';
            } else {
                output[j] = '+';
            }
        }
        return String.copyValueOf(output);
    }
 }
