package chris.atkins.infinitehouseofpancakes;
 
 import java.util.Arrays;
 
 
 public class Input {
 
    final int caseNumber;
    final int[] plates;
 
 
    public Input(final int caseNumber, final int[] plates) {
        this.caseNumber = caseNumber;
        this.plates = plates;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.caseNumber;
        result = prime * result + Arrays.hashCode(this.plates);
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
        if (!Arrays.equals(this.plates, other.plates))
            return false;
        return true;
    }
 }
