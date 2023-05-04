package chris.atkins.dijkstra;
 
 public class Output {
 
    final int caseNumber;
    final boolean correctish;
 
 
    public Output(final int caseNumber, final boolean correctish) {
        super();
        this.caseNumber = caseNumber;
        this.correctish = correctish;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.caseNumber;
        result = prime * result + (this.correctish ? 1231 : 1237);
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
        if (this.correctish != other.correctish)
            return false;
        return true;
    }
 
 
    @Override
    public String toString() {
        return "Output [caseNumber=" + this.caseNumber + ", correctish=" + this.correctish + "]";
    }
 
 }
