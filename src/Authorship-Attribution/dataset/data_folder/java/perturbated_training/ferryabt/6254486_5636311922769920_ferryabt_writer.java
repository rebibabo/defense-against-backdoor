package abtric.utility;
 
 import java.io.FileWriter;
 import java.io.IOException;
 
 public class Writer {
    public static void write(String file, String[] content) {
        try {
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < content.length; i++) {
                writer.write("Case #" + (i + 1) + ": " + content[i] + "\n");
            }
            writer.close();
            System.out.println("---> DONE <---");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 }
