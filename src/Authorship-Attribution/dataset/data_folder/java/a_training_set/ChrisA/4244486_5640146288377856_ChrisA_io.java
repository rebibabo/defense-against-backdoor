package chris.atkins.firstproblem;
 
 import static java.lang.Integer.parseInt;
 
 import java.util.ArrayList;
 import java.util.List;
 
 import org.apache.commons.lang.Validate;
 
 
 public class IO {
 
 }
 
 
 class Input {
 
    int caseNumber;
    int rows;
    int columns;
    int shipWidth;
 
 
    public Input(final int caseNumber, final int rows, final int columns, final int shipWidth) {
        super();
        this.caseNumber = caseNumber;
        this.rows = rows;
        this.columns = columns;
        this.shipWidth = shipWidth;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.caseNumber;
        result = prime * result + this.columns;
        result = prime * result + this.rows;
        result = prime * result + this.shipWidth;
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
        if (this.columns != other.columns)
            return false;
        if (this.rows != other.rows)
            return false;
        if (this.shipWidth != other.shipWidth)
            return false;
        return true;
    }
 
 
    @Override
    public String toString() {
        return "Input [caseNumber=" + this.caseNumber + ", rows=" + this.rows + ", columns=" + this.columns + ", shipWidth=" + this.shipWidth + "]";
    }
 }
 
 
 class InputCreator {
 
    private int expectedNumberOfInputs;
 
 
    public List<Input> create(final List<String> inputLines) {
        final List<Input> inputList = new ArrayList<>((inputLines.size() - 1));
        boolean openingLine = true;
        int caseNumber = 0;
 
        for (final String line : inputLines) {
            if (openingLine) {
                openingLine = false;
                this.expectedNumberOfInputs = Integer.parseInt(line);
            } else {
                caseNumber++;
                inputList.add(createNewInputFromLine(caseNumber, line));
            }
        }
        Validate.isTrue(this.expectedNumberOfInputs == inputList.size());
        return inputList;
    }
 
 
    private Input createNewInputFromLine(final int caseNumber, final String line) {
        final String[] splittings = line.split(" ");
        return new Input(caseNumber, parseInt(splittings[0]), parseInt(splittings[1]), parseInt(splittings[2]));
    }
 }
 
 
 class Output {
 
    int caseNumber;
    int minimumGuaranteedScore;
 
 
    public Output(final int caseNumber, final int minimumGuaranteedScore) {
        super();
        this.caseNumber = caseNumber;
        this.minimumGuaranteedScore = minimumGuaranteedScore;
    }
 
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.caseNumber;
        result = prime * result + this.minimumGuaranteedScore;
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
        if (this.minimumGuaranteedScore != other.minimumGuaranteedScore)
            return false;
        return true;
    }
 
 
    @Override
    public String toString() {
        return "Output [caseNumber=" + this.caseNumber + ", minimumGuaranteedScore=" + this.minimumGuaranteedScore + "]";
    }
 }
 
 
 class OutputTranslator {
 
    public static String translateToLine(final Output output) {
        return String.format("Case #%d: %d", output.caseNumber, output.minimumGuaranteedScore);
    }
 }