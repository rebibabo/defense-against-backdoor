package chris.atkins.infinitehouseofpancakes;
 
 public class Output {
 
    final int caseNumber;
    final int numberOfMinutes;
 
 
    public Output(final int caseNumber, final int numberOfMinutes) {
        this.caseNumber = caseNumber;
        this.numberOfMinutes = numberOfMinutes;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.caseNumber;
        result = prime * result + this.numberOfMinutes;
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
        final Output other = (Output) obj;
        if (this.caseNumber != other.caseNumber)
            return false;
        if (this.numberOfMinutes != other.numberOfMinutes)
            return false;
        return true;
    }
 
 
    @Override
    public String toString() {
        return "Output [caseNumber=" + this.caseNumber + ", numberOfMinutes=" + this.numberOfMinutes + "]";
    }
 }
