package chris.atkins.dijkstra;
 
 import java.util.ArrayList;
 import java.util.List;
 
 import org.apache.commons.lang.Validate;
 
 
 public class InputCreator {
 
    private int expectedNumberOfInputs;
 
 
    public List<Input> create(final List<String> inputLines) {
        final List<Input> inputList = new ArrayList<>((inputLines.size() - 1) / 2);
        boolean openingLine = true;
        boolean firstLineOfInput = true;
        int caseNumber = 0;
        String firstLine = null;
 
        for (final String line : inputLines) {
            if (openingLine) {
                openingLine = false;
                this.expectedNumberOfInputs = Integer.parseInt(line);
            } else {
                if (firstLineOfInput) {
                    firstLine = line;
                    caseNumber++;
                } else {
                    inputList.add(createNewInputFromLine(caseNumber, line, firstLine));
                }
                firstLineOfInput = !firstLineOfInput;
            }
        }
        Validate.isTrue(this.expectedNumberOfInputs == inputList.size());
        return inputList;
    }
 
 
    private Input createNewInputFromLine(final int caseNumber, final String line, final String firstLine) {
        final String[] split = firstLine.split(" ");
        final int expectedStringLength = Integer.parseInt(split[0]);
        final int numberOfTimesToRepeat = Integer.parseInt(split[1]);
        Validate.isTrue(expectedStringLength == line.length());
        return new Input(caseNumber, buildVarArray(line.toCharArray()), numberOfTimesToRepeat);
    }
 
 
    private Var[] buildVarArray(final char[] line) {
        final Var[] arr = new Var[line.length];
        for (int i = 0; i < line.length; i++) {
            arr[i] = Var.varFor(line[i]);
        }
        return arr;
 
    }
 }
