package chris.atkins.infinitehouseofpancakes;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import java.util.LinkedList;
 import java.util.List;
 
 import org.junit.Before;
 import org.junit.Test;
 
 
 public class InfiniteHouseOfPancakesTest {
 
 	private List<String> inputLines;
 	private List<String> expectedOutputLines;
 	private final InfiniteHouseOfPancakes standingOvation = new InfiniteHouseOfPancakes();
 
 
 	@Before
 	public void setup() {
 		setupInput();
 		setupExpectedOutput();
 	}
 
 
 	@Test
 	public void sampleCase() throws Exception {
 		final List<String> result = this.standingOvation.solveAllProblems(this.inputLines);
 		assertThat(result, equalTo(this.expectedOutputLines));
 	}
 
 
 	private void setupInput() {
 		this.inputLines = new LinkedList<>();
 		this.inputLines.add("3");
 		this.inputLines.add("1");
 		this.inputLines.add("3");
 		this.inputLines.add("4");
 		this.inputLines.add("1 2 1 2");
 		this.inputLines.add("1");
 		this.inputLines.add("4");
 	}
 
 
 	private void setupExpectedOutput() {
 		this.expectedOutputLines = new LinkedList<>();
 		this.expectedOutputLines.add("Case #1: 3");
 		this.expectedOutputLines.add("Case #2: 2");
 		this.expectedOutputLines.add("Case #3: 3");
 	}
 }
