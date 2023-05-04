package chris.atkins.standingovation;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.List;
 
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 import com.google.common.collect.ImmutableList;
 
 
 @RunWith(JUnit4.class)
 public class InputCreatorTest {
 
    private final InputCreator creator = new InputCreator();
 
 
    @Test
    public void correctNumberOfResponses() throws Exception {
        final List<Input> results = this.creator.create(buildSampleInput());
        assertThat(results.size(), equalTo(4));
    }
 
 
    @Test
    public void lineTranslatesToCorrectIntArray() throws Exception {
        final List<String> inputLines = new ImmutableList.Builder<String>().add("1").add("4 12345").build();
        final List<Input> expectedOutput = new ImmutableList.Builder<Input>().add(new Input(1, new int[] { 1, 2, 3, 4, 5 })).build();
 
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
        inputLines.add("4 11111");
        inputLines.add("1 09");
        inputLines.add("5 110011");
        inputLines.add("0 1");
        return inputLines;
    }
 
 
    private List<Input> buildExpectedOutputLines() {
        final List<Input> expectedOutputLines = new ArrayList<>();
        expectedOutputLines.add(new Input(1, new int[] { 1, 1, 1, 1, 1 }));
        expectedOutputLines.add(new Input(2, new int[] { 0, 9 }));
        expectedOutputLines.add(new Input(3, new int[] { 1, 1, 0, 0, 1, 1 }));
        expectedOutputLines.add(new Input(4, new int[] { 1 }));
        return expectedOutputLines;
    }
 }
