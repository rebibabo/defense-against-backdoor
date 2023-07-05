package chris.atkins.dijkstra;
 
 import static chris.atkins.dijkstra.Var.I;
 import static chris.atkins.dijkstra.Var.J;
 import static chris.atkins.dijkstra.Var.K;
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
        assertThat(results.size(), equalTo(5));
    }
 
 
    @Test
    public void lineTranslatesToCorrectIntArray() throws Exception {
        final List<String> inputLines = new ImmutableList.Builder<String>().add("1").add("5 6").add("ijkij").build();
        final List<Input> expectedOutput = new ImmutableList.Builder<Input>().add(new Input(1, new Var[] { I, J, K, I, J }, 6)).build();
 
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
        inputLines.add("5");
        inputLines.add("2 1");
        inputLines.add("ik");
        inputLines.add("3 1");
        inputLines.add("ijk");
        inputLines.add("3 1");
        inputLines.add("kji");
        inputLines.add("2 6");
        inputLines.add("ji");
        inputLines.add("1 10000");
        inputLines.add("i");
        return inputLines;
    }
 
 
    private List<Input> buildExpectedOutputLines() {
        final List<Input> expectedInputs = new ArrayList<>();
        expectedInputs.add(new Input(1, new Var[] { I, K }, 1));
        expectedInputs.add(new Input(2, new Var[] { I, J, K }, 1));
        expectedInputs.add(new Input(3, new Var[] { K, J, I }, 1));
        expectedInputs.add(new Input(4, new Var[] { J, I }, 6));
        expectedInputs.add(new Input(5, new Var[] { I }, 10000));
        return expectedInputs;
    }
 }
