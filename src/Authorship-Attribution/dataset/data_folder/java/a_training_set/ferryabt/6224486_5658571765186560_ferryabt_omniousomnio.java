package qualification;
 
 import helpers.AbtRicReader;
 import helpers.AbtRicWriter;
 
 public class OmniousOmnio {
 
    public static void main(String[] args) {
        String file = "C:/Users/Ferry/eclipse/CodeJam/in/D-small-attempt0.in";
        AbtRicReader reader = new AbtRicReader(file);
        AbtRicWriter writer = new AbtRicWriter();
        OmniousOmnio solver = new OmniousOmnio();
        int numberOfCases = Integer.parseInt(reader.getLine()[0]);
        String[] solutions = new String[numberOfCases];
        for (int curCase = 0; curCase < numberOfCases; curCase++) {
            int[] input = reader.toInteger(reader.getLine());
            if (solver.solve(input[0], input[1], input[2])) {
                solutions[curCase] = "RICHARD";
            } else {
                solutions[curCase] = "GABRIEL";
            }
            System.out.println("#"+(curCase+1)+": "+solutions[curCase]);
        }
        writer.write("C:/Users/Ferry/eclipse/CodeJam/out/OmniousOmnio.out",
                solutions);
    }
 
    public boolean solve(int X, int rows, int columns) {
        
        if (X > rows && X > columns) {
            return true;
        }
        
        
        if (rows * columns % X != 0) {
            return true;
        }
        
        if ((rows+1) * 2 - 1 <= X || (columns+1) * 2 - 1 <= X) {
            return true;
        }
        
        
        
        
        
        
        if (X >= 7) {
            return true;
        }
        if(X>=4&&rows+columns-2<=X) {
            return true;
        }
        return false;
    }
 
 }
