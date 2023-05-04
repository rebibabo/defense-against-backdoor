package chris.atkins.infinitehouseofpancakes;
 
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
        assertThat(results.size(), equalTo(3));
    }
 
 
    @Test
    public void lineTranslatesToCorrectIntArray() throws Exception {
        final List<String> inputLines = new ImmutableList.Builder<String>().add("1").add("5").add("1 2 3 4 5").build();
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
        inputLines.add("3");
        inputLines.add("1");
        inputLines.add("3");
        inputLines.add("4");
        inputLines.add("1 2 1 2");
        inputLines.add("1");
        inputLines.add("4");
        return inputLines;
    }
 
 
    private List<Input> buildExpectedOutputLines() {
        final List<Input> expectedOutputLines = new ArrayList<>();
        expectedOutputLines.add(new Input(1, new int[] { 3 }));
        expectedOutputLines.add(new Input(2, new int[] { 1, 2, 1, 2 }));
        expectedOutputLines.add(new Input(3, new int[] { 4 }));
        return expectedOutputLines;
    }
 }
