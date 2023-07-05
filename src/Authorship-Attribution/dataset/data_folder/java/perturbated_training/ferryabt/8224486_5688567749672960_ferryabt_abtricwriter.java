package helpers;
 
 import java.io.FileWriter;
 import java.io.IOException;
 
 public class AbtRicWriter {
 public void write(String file, String[] content) {
    try {
        FileWriter writer = new FileWriter(file);
        for(int i=0;i<content.length;i++) {
            writer.write("Case #"+(i+1)+": "+content[i]+"\n");
        }
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
 }
 }
