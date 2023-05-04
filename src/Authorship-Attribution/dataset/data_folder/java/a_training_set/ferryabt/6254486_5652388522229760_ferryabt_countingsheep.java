package abtric.qualification;
 
 import java.io.IOException;
 import java.nio.file.FileSystems;
 import java.nio.file.Files;
 import java.util.LinkedList;
 import java.util.List;
 
 import abtric.utility.Writer;
 
 public class CountingSheep {
    public static void main(String[] args) {
        
        String stringPath = "A-small-attempt0.in";
        
        try {
            final List<String> inputFile = Files
                    .readAllLines(FileSystems.getDefault().getPath("in\\CountingSheep", stringPath));
            
            final int T = Integer.parseInt(inputFile.get(0));
            String[] results = new String[T];
            for (int i = 0; i < T; i++) {
                
                final int N = Integer.parseInt(inputFile.get(i + 1));
                if (N == 0) {
                    results[i] = "INSOMNIA";
                    continue;
                }
                List<Character> toFind = new LinkedList<>();
                toFind.add('0');
                toFind.add('1');
                toFind.add('2');
                toFind.add('3');
                toFind.add('4');
                toFind.add('5');
                toFind.add('6');
                toFind.add('7');
                toFind.add('8');
                toFind.add('9');
                int current = 0;
                while (!toFind.isEmpty()) {
                    current += N;
                    for (char c : Integer.toString(current).toCharArray()) {
                        toFind.remove(new Character(c));
                    }
                }
                results[i] = Integer.toString(current);
            }
            
            Writer.write("out\\CountingSheep-small.out", results);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
