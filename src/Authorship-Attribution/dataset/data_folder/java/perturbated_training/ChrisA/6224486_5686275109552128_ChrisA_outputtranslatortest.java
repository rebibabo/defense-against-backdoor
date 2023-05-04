package chris.atkins.infinitehouseofpancakes;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 
 @RunWith(JUnit4.class)
 public class OutputTranslatorTest {
 
    @Test
    public void theOnlyCase() throws Exception {
        final String line = OutputTranslator.translateToLine(new Output(23, 567));
        assertThat(line, equalTo("Case #23: 567"));
    }
 }
