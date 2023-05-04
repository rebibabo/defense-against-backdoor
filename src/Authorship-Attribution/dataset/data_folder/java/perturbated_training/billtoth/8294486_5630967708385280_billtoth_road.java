import java.util.Scanner;
 
 public class Road {
 
    double totalDistance;
    int otherHorses;
    double speed;
    double[][] horseArray;
    int slowestHorse;
    double longestETDSoFar;
    int slowestSoFar;
    
    public Road(Scanner i, double d, int n){
        totalDistance = d;
        otherHorses = n;
        buildHorseArray();
    }
    
    private void buildHorseArray() {
        horseArray = new double[otherHorses][3];
        for (int i = 0; i < otherHorses; i++){
            horseArray[i][0] = Dataset.input.nextDouble();
            horseArray[i][1] = Dataset.input.nextDouble();
            horseArray[i][2] = (totalDistance - horseArray[i][0])/horseArray[i][1];
            
            
            
        }
    }
 
    public double evaluate(){
        slowestHorse = findSlowestHorse();
        speed = totalDistance/((totalDistance-horseArray[slowestHorse][0])/horseArray[slowestHorse][1]);
        return speed;
    }
 
    private int findSlowestHorse() {
        longestETDSoFar = horseArray[0][2];
        slowestSoFar = 0;
        for(int i = 0; i < otherHorses; i++){
            if(horseArray[i][2] > longestETDSoFar){
                longestETDSoFar = horseArray[i][2];
                slowestSoFar = i;   
            }
        }
        return slowestSoFar;
    }
 }
