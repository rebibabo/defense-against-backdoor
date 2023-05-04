package qualification;
 
 import java.util.Arrays;
 import java.util.HashMap;
 
 import helpers.AbtRicReader;
 import helpers.AbtRicWriter;
 import training.RopeIntranet2;
 
 public class InfiniteHouseofPancakes2 {
 
    public static HashMap<String, Integer> map = new HashMap<String, Integer>();
 
    public static void main(String[] args) {
        String file = "C:/Users/Ferry/eclipse/CodeJam/in/B-small-attempt2.in";
        AbtRicReader reader = new AbtRicReader(file);
        AbtRicWriter writer = new AbtRicWriter();
        InfiniteHouseofPancakes2 solver = new InfiniteHouseofPancakes2();
        int numberOfCases = Integer.parseInt(reader.getLine()[0]);
        String[] solutions = new String[numberOfCases];
        for (int curCase = 0; curCase < numberOfCases; curCase++) {
            int numOfDiners = Integer.parseInt(reader.getLine()[0]);
            int[] diners = reader.toInteger(reader.getLine());
            solutions[curCase] = Integer.toString(solver.solve(diners));
            System.out.println("#" + (curCase + 1) + ": " + solutions[curCase]);
            System.out.println();
        }
        writer.write(
                "C:/Users/Ferry/eclipse/CodeJam/out/InfiniteHouseofPancakes.out",
                solutions);
    }
 
    public int solve(int[] diners) {
        if (empty(diners)) {
            return 1;
        }
        
        int[][] times = new int[diners.length][8];
        int timeWait;
        int[] diners2 = diners.clone();
        for (int i = 0; i < diners.length; i++) {
            if (diners2[i] > 0) {
                diners2[i]--;
            }
        }
        diners2 = reduce(diners2);
        Arrays.sort(diners2);
        String dinersString = "";
        for (int i = 0; i < diners2.length; i++) {
            dinersString += Integer.toString(diners2[i]);
        }
        if (map.containsKey(dinersString)) {
            timeWait = map.get(dinersString) + 1;
        } else {
            timeWait = solve(diners2) + 1;
            map.put(dinersString, timeWait - 1);
            
        }
        for (int i = 0; i < diners.length; i++) {
            if (diners[i] <= 1) {
                continue;
            }
            for(int j=1;j<diners[i];j++) {
                int[] diners3 = split2(diners.clone(),i,j);
                dinersString = "";
                Arrays.sort(diners3);
                for (int k = 0; k < diners3.length; k++) {
                    dinersString += Integer.toString(diners3[k]);
                }
                if (map.containsKey(dinersString)) {
                    times[i][j-1] = map.get(dinersString) + 1;
                } else {
                    times[i][j-1] = solve(diners3) + 1;
                    map.put(dinersString, times[i][j-1] - 1);
                    
                }
            }
            
            
            
            
 
 
 
 
 
 
 
 
 
 
 
 
 
        }
        int minTime = timeWait;
        for (int i = 0; i < diners.length; i++) {
            if (diners[i] <= 1) {
                continue;
            }
            for (int j = 1; j < diners[i]; j++) {
                if (times[i][j - 1] < minTime) {
                    minTime = times[i][j - 1];
                }
            }
 
 
 
        }
        if (timeWait < minTime) {
            minTime = timeWait;
        }
        return minTime;
    }
 
    private int max(int[] list) {
        int max = list[0];
        if (list.length > 1) {
            for (int i = 1; i < list.length; i++) {
                if (list[i] > max) {
                    max = list[i];
                }
            }
        }
        return max;
    }
 
    private int[] split(int[] list, int i) {
        int[] output = new int[list.length + 1];
        for (int j = 0; j < list.length; j++) {
            output[j] = list[j];
        }
        if (list[i] % 2 == 0) {
            output[i] = list[i] / 2;
        } else {
            output[i] = list[i] / 2 + 1;
        }
        output[output.length - 1] = list[i] / 2;
        return output;
    }
 
    private int[] split2(int[] list, int i, int amount) {
        int[] output = new int[list.length + 1];
        for (int j = 0; j < list.length; j++) {
            output[j] = list[j];
        }
        output[i] = list[i] - amount;
        output[output.length - 1] = amount;
        return output;
    }
 
    private boolean empty(int[] array) {
        boolean empty = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 1) {
                empty = false;
            }
        }
        return empty;
    }
 
    private int[] reduce(int[] list) {
        int count = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] > 0) {
                count++;
            }
        }
        int[] output = new int[count];
        for (int i = list.length - 1; i >= 0; i--) {
            if (list[i] > 0) {
                output[count - 1] = list[i];
                count--;
            }
        }
        return output;
    }
 
 }
