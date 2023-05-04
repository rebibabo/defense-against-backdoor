package chris.atkins.mushroommonster;
 
 import java.util.Arrays;
 
 
 public class Input {
 
    int caseNumber;
    int[] roundValues;
    int numberOfRounds;
 
 
    public Input(final int caseNumber, final int[] roundValues, final int numberOfRounds) {
        super();
        this.caseNumber = caseNumber;
        this.roundValues = roundValues;
        this.numberOfRounds = numberOfRounds;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.caseNumber;
        result = prime * result + this.numberOfRounds;
        result = prime * result + Arrays.hashCode(this.roundValues);
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
        if (this.caseNumber != other.caseNumber)
            return false;
        if (this.numberOfRounds != other.numberOfRounds)
            return false;
        if (!Arrays.equals(this.roundValues, other.roundValues))
            return false;
        return true;
    }
 
 
    @Override
    public String toString() {
        return "Input [caseNumber=" + this.caseNumber + ", roundValues=" + Arrays.toString(this.roundValues) + ", numberOfRounds="
                + this.numberOfRounds + "]";
    }
 }
