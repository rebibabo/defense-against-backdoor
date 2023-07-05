package chris.atkins.infinitehouseofpancakes;
 
 import java.util.ArrayList;
 import java.util.List;
 
 import org.apache.commons.lang.Validate;
 
 
 public class InputCreator {
 
    private int expectedNumberOfLines;
 
 
    public List<Input> create(final List<String> inputLines) {
        final List<Input> inputList = new ArrayList<>((inputLines.size() - 1) / 2);
        boolean openingLine = true;
        boolean firstLineOfInput = true;
        int currentExpectedLength = 0;
        int caseNumber = 0;
 
        for (final String line : inputLines) {
            if (openingLine) {
                openingLine = false;
                this.expectedNumberOfLines = Integer.parseInt(line);
            } else {
                if (firstLineOfInput) {
                    currentExpectedLength = Integer.parseInt(line);
                    caseNumber++;
                } else {
                    inputList.add(createNewInputFromLine(caseNumber, line, currentExpectedLength));
                }
                firstLineOfInput = !firstLineOfInput;
            }
        }
 
        Validate.isTrue(this.expectedNumberOfLines == inputList.size());
        return inputList;
    }
 
 
    private Input createNewInputFromLine(final int caseNumber, final String line, final int expectedLength) {
        final String[] splitLine = line.split(" ");
        Validate.isTrue(expectedLength == splitLine.length);
 
        final int[] plates = new int[expectedLength];
        for (int i = 0; i < expectedLength; i++) {
            plates[i] = Integer.parseInt(splitLine[i]);
        }
        return new Input(caseNumber, plates);
    }
 }
