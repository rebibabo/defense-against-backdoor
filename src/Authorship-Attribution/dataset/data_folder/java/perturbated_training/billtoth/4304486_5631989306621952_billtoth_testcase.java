
 public class TestCase {
 
    String word;
    String lastWord;
    String latestLetter;
    int position;
    int positionTracker;
    
    public TestCase (String s) {
        word = s;
        lastWord = "";
        position = word.length();
        positionTracker = position;
    }
    
    public String evaluateCase() {
        while (position > 1){
            lastWord += findLast(word);
            word = word.substring (0, position) + word.substring(position+1);
        }
        lastWord += word;
        return lastWord;
    }
 
    public String findLast(String s){
        latestLetter = s.substring(position - 1, position);
        for (int i = 0; i < position; i++){
            if (s.substring(i, i+1).compareTo(latestLetter) >= 0){
                
                
                latestLetter = s.substring(i, i+1);
                positionTracker = i;
            }
        }
        position = positionTracker;
        return latestLetter; 
    }
 }
