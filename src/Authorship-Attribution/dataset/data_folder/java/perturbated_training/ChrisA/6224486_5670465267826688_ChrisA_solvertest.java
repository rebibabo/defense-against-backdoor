package chris.atkins.dijkstra;
 
 import static chris.atkins.dijkstra.Var.I;
 import static chris.atkins.dijkstra.Var.J;
 import static chris.atkins.dijkstra.Var.K;
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import org.apache.commons.lang.math.RandomUtils;
 import org.junit.Before;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 
 @RunWith(JUnit4.class)
 public class SolverTest {
 
    private Solver solver;
    private int caseNumber;
 
 
    @Before
    public void setup() {
        this.caseNumber = RandomUtils.nextInt(100);
        this.solver = new Solver();
    }
 
 
    @Test
    public void test1() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new Var[] { I, K }, 1));
        assertThat(result, equalTo(new Output(this.caseNumber, false)));
    }
 
 
    @Test
    public void test2() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new Var[] { I, J, K }, 1));
        assertThat(result, equalTo(new Output(this.caseNumber, true)));
    }
 
 
    @Test
    public void test3() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new Var[] { K, J, I }, 1));
        assertThat(result, equalTo(new Output(this.caseNumber, false)));
    }
 
 
    @Test
    public void test4() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new Var[] { J, I }, 6));
        assertThat(result, equalTo(new Output(this.caseNumber, true)));
    }
 
 
    @Test
    public void test5() throws Exception {
        final Output result = this.solver.solveSingleProblem(new Input(this.caseNumber, new Var[] { I }, 10000));
        assertThat(result, equalTo(new Output(this.caseNumber, false)));
    }
 }
