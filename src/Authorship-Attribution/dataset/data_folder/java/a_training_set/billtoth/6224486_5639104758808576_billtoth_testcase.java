public class TestCase {
 
    int[] audienceCase;
    int maxShyness;
    int audienceSize;
    int standing;
    int friends;
    
    public TestCase (String line) {
        maxShyness = Integer.parseInt(line.substring(0,1));
        makeAudience(maxShyness, line);
    }
    
    private void makeAudience(int a, String s) {
        audienceSize=0;
        audienceCase = new int[a+1];
        
        
        for (int i = 0; i < a+1; i++){
            int num = Integer.parseInt(s.substring(i+2,i+3));
            audienceSize += num;
            audienceCase[i] = num;
        }
    }
 
    public int evaluateCase() {
        standing = 0;
        friends = 0;
        for (int i = 0; standing < audienceSize; i++){
            
            if (standing >= i){
                standing+= audienceCase[i];
            }
            
            else{
                while (standing < i){
                    addFriend();
                }
                standing+=audienceCase[i];
            }
            
        }
        
        return friends;
    }
 
    private void addFriend() {
        friends++;
        audienceSize++;
        standing++;
    }
 
 }
