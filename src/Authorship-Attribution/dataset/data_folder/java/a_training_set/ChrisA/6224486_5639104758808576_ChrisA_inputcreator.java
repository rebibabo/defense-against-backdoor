package chris.atkins.standingovation;
 
 import java.util.ArrayList;
 import java.util.List;
 
 import org.apache.commons.lang.Validate;
 
 
 public class InputCreator {
 
    private int expectedNumberOfLines;
 
 
    public List<Input> create(final List<String> inputLines) {
        final List<Input> inputList = new ArrayList<>(inputLines.size() - 1);
        boolean firstLine = true;
        int lineNumber = 0;
 
        for (final String line : inputLines) {
            if (firstLine) {
                firstLine = false;
                this.expectedNumberOfLines = Integer.parseInt(line);
            } else {
                inputList.add(createNewInputFromLine(lineNumber, line));
            }
            lineNumber++;
        }
 
        Validate.isTrue(this.expectedNumberOfLines == inputList.size());
        return inputList;
    }
 
 
    private Input createNewInputFromLine(final int lineNumber, final String line) {
        final char[] audienceChars = line.split(" ")[1].toCharArray();
        final int[] audienceArray = new int[audienceChars.length];
        for (int i = 0; i < audienceChars.length; i++) {
            audienceArray[i] = Integer.parseInt("" + audienceChars[i]);
        }
        return new Input(lineNumber, audienceArray);
    }
 }
