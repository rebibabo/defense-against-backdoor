package chris.atkins.mushroommonster;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.List;
 
 import org.junit.Test;
 
 import com.google.common.collect.ImmutableList;
 
 
 public class InputCreatorTest {
 
    private final InputCreator creator = new InputCreator();
 
 
    @Test
    public void correctNumberOfResponses() throws Exception {
        final List<Input> results = this.creator.create(buildSampleInput());
        assertThat(results.size(), equalTo(4));
    }
 
 
    @Test
    public void lineTranslatesToCorrectIntArray() throws Exception {
        final List<String> inputLines = new ImmutableList.Builder<String>().add("1").add("5").add("1 2 3 4 5").build();
        final List<Input> expectedOutput = new ImmutableList.Builder<Input>().add(new Input(1, new int[] { 1, 2, 3, 4, 5 }, 5)).build();
 
        final List<Input> results = this.creator.create(inputLines);
        assertThat(results, equalTo(expectedOutput));
    }
 
 
    @Test
    public void sampleProblem() throws Exception {
        final List<Input> results = this.creator.create(buildSampleInput());
        assertThat(results, equalTo(buildExpectedOutputLines()));
    }
 
 
    private List<String> buildSampleInput() {
        final List<String> inputLines = new LinkedList<>();
        inputLines.add("4");
        inputLines.add("4");
        inputLines.add("10 5 15 5");
        inputLines.add("2");
        inputLines.add("100 100");
        inputLines.add("8");
        inputLines.add("81 81 81 81 81 81 81 0");
        inputLines.add("6");
        inputLines.add("23 90 40 0 100 9");
        return inputLines;
    }
 
 
    private List<Input> buildExpectedOutputLines() {
        final List<Input> expectedInputs = new ArrayList<>();
        expectedInputs.add(new Input(1, new int[] { 10, 5, 15, 5 }, 4));
        expectedInputs.add(new Input(2, new int[] { 100, 100 }, 2));
        expectedInputs.add(new Input(3, new int[] { 81, 81, 81, 81, 81, 81, 81, 0 }, 8));
        expectedInputs.add(new Input(4, new int[] { 23, 90, 40, 0, 100, 9 }, 6));
        return expectedInputs;
    }
 }
