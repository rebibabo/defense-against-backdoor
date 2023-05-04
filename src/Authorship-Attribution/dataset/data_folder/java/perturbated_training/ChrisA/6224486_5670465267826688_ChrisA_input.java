package chris.atkins.dijkstra;
 
 import java.util.Arrays;
 
 
 public class Input {
 
    final int caseNumber;
    final Var[] letters;
    final int numberOfTimesToRepeat;
 
 
    public Input(final int caseNumber, final Var[] vars, final int numberOfTimesToRepeat) {
        super();
        this.caseNumber = caseNumber;
        this.letters = vars;
        this.numberOfTimesToRepeat = numberOfTimesToRepeat;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.caseNumber;
        result = prime * result + Arrays.hashCode(this.letters);
        result = prime * result + this.numberOfTimesToRepeat;
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
        if (!Arrays.equals(this.letters, other.letters))
            return false;
        if (this.numberOfTimesToRepeat != other.numberOfTimesToRepeat)
            return false;
        return true;
    }
 
 
    @Override
    public String toString() {
        return "Input [caseNumber=" + this.caseNumber + ", letters=" + Arrays.toString(this.letters) + ", numberOfTimesToRepeat="
                + this.numberOfTimesToRepeat + "]";
    }
 
 }
