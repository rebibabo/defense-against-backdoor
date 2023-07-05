package chris.atkins.mushroommonster;
 
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
 
 
    private Input createNewInputFromLine(final int caseNumber, final String secondLine, final String firstLine) {
        final String[] split = secondLine.split(" ");
        final int expectedRounds = Integer.parseInt(firstLine);
        Validate.isTrue(expectedRounds == split.length);
        return new Input(caseNumber, buildIntArray(split), expectedRounds);
    }
 
 
    private int[] buildIntArray(final String[] line) {
        final int[] arr = new int[line.length];
        for (int i = 0; i < line.length; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        return arr;
    }
 }
