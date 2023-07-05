package chris.atkins.standingovation;
 
 import java.util.Arrays;
 
 
 public class Input {
 
    int[] audience;
    int caseNumber;
 
 
    public Input(final int caseNumber, final int[] audience) {
        this.caseNumber = caseNumber;
        this.audience = audience;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(this.audience);
        return result;
    }
 
 
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Input other = (Input) obj;
        if (!Arrays.equals(this.audience, other.audience))
            return false;
        return true;
    }
 }
