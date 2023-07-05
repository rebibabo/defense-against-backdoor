package chris.atkins.firstproblem;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import java.util.ArrayList;
 import java.util.LinkedList;
 import java.util.List;
 
 import org.junit.Test;
 import org.junit.experimental.runners.Enclosed;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 import com.google.common.collect.ImmutableList;
 
 
 @RunWith(Enclosed.class)
 public class IOTest {
 
    @RunWith(JUnit4.class)
    public static class InputCreatorTest {
 
        private final InputCreator creator = new InputCreator();
 
 
        @Test
        public void correctNumberOfResponses() throws Exception {
            final List<Input> results = this.creator.create(buildSampleInput());
            assertThat(results.size(), equalTo(4));
        }
 
 
        @Test
        public void lineTranslatesToCorrectIntArray() throws Exception {
            final List<String> inputLines = new ImmutableList.Builder<String>().add("1").add("3 4 5").build();
            final List<Input> expectedOutput = new ImmutableList.Builder<Input>().add(new Input(1, 3, 4, 5)).build();
 
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
            inputLines.add("2 3 6");
            inputLines.add("4 1 2");
            inputLines.add("3 3 8");
            inputLines.add("5 2 0");
            return inputLines;
        }
 
 
        private List<Input> buildExpectedOutputLines() {
            final List<Input> expectedInputs = new ArrayList<>();
            expectedInputs.add(new Input(1, 2, 3, 6));
            expectedInputs.add(new Input(2, 4, 1, 2));
            expectedInputs.add(new Input(3, 3, 3, 8));
            expectedInputs.add(new Input(4, 5, 2, 0));
            return expectedInputs;
        }
    }
 
    @RunWith(JUnit4.class)
    public static class OutputTranslatorTest {
 
        @Test
        public void onlyCase() throws Exception {
            final String line = OutputTranslator.translateToLine(new Output(23, 4567));
            assertThat(line, equalTo("Case #23: 4567"));
        }
    }
 }
