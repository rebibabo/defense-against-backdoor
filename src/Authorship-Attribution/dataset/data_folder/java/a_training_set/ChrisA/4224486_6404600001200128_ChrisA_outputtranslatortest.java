package chris.atkins.mushroommonster;
 
 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.equalTo;
 
 import org.junit.Test;
 
 
 public class OutputTranslatorTest {
 
    @Test
    public void onlyCase() throws Exception {
        final String line = OutputTranslator.translateToLine(new Output(23, 234, 567));
        assertThat(line, equalTo("Case #23: 234 567"));
    }
 }
