package qualification;
 
 import helpers.AbtRicReader;
 import helpers.AbtRicWriter;
 
 public class StandingOvation {
 
    public static void main(String[] args) {
        String file = "C:/Users/Ferry/eclipse/CodeJam/in/A-small-attempt0.in";
        AbtRicReader reader = new AbtRicReader(file);
        AbtRicWriter writer = new AbtRicWriter();
        StandingOvation solver = new StandingOvation();
        int numberOfCases = Integer.parseInt(reader.getLine()[0]);
        String[] solutions = new String[numberOfCases];
        for (int curCase = 0; curCase < numberOfCases; curCase++) {
            String[] input = reader.getLine();
            int maxShyness = Integer.parseInt(input[0]);
            int[] levels = new int[maxShyness + 1];
            for (int i = 0; i <= maxShyness; i++) {
                levels[i] = Character.getNumericValue(input[1].charAt(i));
            }
            solutions[curCase] = Integer.toString(solver.solve(maxShyness,
                    levels));
            System.out.println("#"+(curCase+1)+": "+solutions[curCase]);
        }
        writer.write("C:/Users/Ferry/eclipse/CodeJam/out/StandingOvation.out",
                solutions);
    }
 
    public int solve(int maxShyness, int[] levels) {
        int count = 0;
        int standing = levels[0];
        for (int level = 1; level <= maxShyness; level++) {
            if (level > standing) {
                count += level - standing;
                standing = level + levels[level];
            } else {
                standing += levels[level];
            }
        }
        return count;
    }
 
 }
