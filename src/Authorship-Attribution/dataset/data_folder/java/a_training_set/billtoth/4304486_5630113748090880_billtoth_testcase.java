import java.util.Arrays;
 
 public class TestCase {
 
    int n;
    int[] heightArray;
    String answer;
    int [] missingArray;
    
    public TestCase (int a, int[] heights) {
        n = a;
        answer = "";
        heightArray = heights;
    }
 
    public String evaluateCase() {
        for (int i=0; i < 2500; i++){
            if ((heightArray[i] % 2) != 0){
                answer += (i) + " ";
            }
        }
        return answer;
    }
 
 }
