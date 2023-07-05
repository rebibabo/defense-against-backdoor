package chris.atkins.mushroommonster;
 
 public class Output {
 
 	final int caseNumber;
 	final int numberEatenForAnyAtOnce;
 	int numberEatenForConstantPace;
 
 
 	public Output(final int caseNumber, final int numberEatenForAnyAtOnce, final int numberEatenForConstantPace) {
 		super();
 		this.caseNumber = caseNumber;
 		this.numberEatenForAnyAtOnce = numberEatenForAnyAtOnce;
 		this.numberEatenForConstantPace = numberEatenForConstantPace;
 	}
 
 
 	@Override
 	public int hashCode() {
 		final int prime = 31;
 		int result = 1;
 		result = prime * result + this.caseNumber;
 		result = prime * result + this.numberEatenForAnyAtOnce;
 		result = prime * result + this.numberEatenForConstantPace;
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
 		if (this.numberEatenForAnyAtOnce != other.numberEatenForAnyAtOnce)
 			return false;
 		if (this.numberEatenForConstantPace != other.numberEatenForConstantPace)
 			return false;
 		return true;
 	}
 
 
 	@Override
 	public String toString() {
 		return "Output [caseNumber=" + this.caseNumber + ", numberEatenForAnyAtOnce=" + this.numberEatenForAnyAtOnce
 				+ ", numberEatenForConstantPace=" + this.numberEatenForConstantPace + "]";
 	}
 }
