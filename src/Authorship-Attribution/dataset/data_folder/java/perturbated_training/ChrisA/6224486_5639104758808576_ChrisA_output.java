package chris.atkins.standingovation;
 
 public class Output {
 
    int caseNumber;
    int numberToAdd;
 
 
    public Output(final int caseNumber, final int numberToAdd) {
        this.caseNumber = caseNumber;
        this.numberToAdd = numberToAdd;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.caseNumber;
        result = prime * result + this.numberToAdd;
        return result;
    }
 
 
    @Override
    public String toString() {
        return "Output [caseNumber=" + this.caseNumber + ", numberToAdd=" + this.numberToAdd + "]";
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
        if (this.numberToAdd != other.numberToAdd)
            return false;
        return true;
    }
 }
