package chris.atkins.dijkstra;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 
 
 @RunWith(JUnit4.class)
 public class OutputTranslatorTest {
 
    @Test
    public void noCase() throws Exception {
        final String line = OutputTranslator.translateToLine(new Output(23, false));
        assertThat(line, equalTo("Case #23: NO"));
    }
 
 
    @Test
    public void yesCase() throws Exception {
        final String line = OutputTranslator.translateToLine(new Output(42, true));
        assertThat(line, equalTo("Case #42: YES"));
    }
 }
