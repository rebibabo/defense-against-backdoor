package chris.atkins.dijkstra;
 
 import java.util.Map;
 
 import com.google.common.collect.ImmutableMap;
 
 
 public enum Var {
    ONE, 
    I, 
    J, 
    K, 
    NEGATIVE_ONE, 
    NEGATIVE_I, 
    NEGATIVE_J, 
    NEGATIVE_K; 
 
    int byOne = 0;
    int byI = 1;
    int byJ = 2;
    int byK = 3;
    int byNegativeOne = 4;
    int byNegativeI = 5;
    int byNegativeJ = 6;
    int byNegativeK = 7;
 
    static Map<Var, Var[]> multiplicationTable = new ImmutableMap.Builder<Var, Var[]>()
            .put(ONE, new Var[] { ONE, I, J, K, NEGATIVE_ONE, NEGATIVE_I, NEGATIVE_J, NEGATIVE_K }) 
            .put(I, new Var[] { I, NEGATIVE_ONE, K, NEGATIVE_J, NEGATIVE_I, ONE, NEGATIVE_K, J }) 
            .put(J, new Var[] { J, NEGATIVE_K, NEGATIVE_ONE, I, NEGATIVE_J, K, ONE, NEGATIVE_I }) 
            .put(K, new Var[] { K, J, NEGATIVE_I, NEGATIVE_ONE, NEGATIVE_K, NEGATIVE_J, I, ONE }) 
            .put(NEGATIVE_ONE, new Var[] { NEGATIVE_ONE, NEGATIVE_I, NEGATIVE_J, NEGATIVE_K, ONE, I, J, K }) 
            .put(NEGATIVE_I, new Var[] { NEGATIVE_I, ONE, NEGATIVE_K, J, I, NEGATIVE_ONE, K, NEGATIVE_J }) 
            .put(NEGATIVE_J, new Var[] { NEGATIVE_J, K, ONE, NEGATIVE_I, J, NEGATIVE_K, NEGATIVE_ONE, I }) 
            .put(NEGATIVE_K, new Var[] { NEGATIVE_K, NEGATIVE_J, I, ONE, K, J, NEGATIVE_I, NEGATIVE_ONE }) 
            .build();
 
 
    public Var times(final Var timesBy) {
        int index = -1;
        switch (timesBy) {
        case ONE:
            index = this.byOne;
            break;
        case I:
            index = this.byI;
            break;
        case J:
            index = this.byJ;
            break;
        case K:
            index = this.byK;
            break;
        case NEGATIVE_ONE:
            index = this.byNegativeOne;
            break;
        case NEGATIVE_I:
            index = this.byNegativeI;
            break;
        case NEGATIVE_J:
            index = this.byNegativeJ;
            break;
        case NEGATIVE_K:
            index = this.byNegativeK;
            break;
        }
 
        return Var.multiplicationTable.get(this)[index];
    }
 
 
    static Var varFor(final char character) {
        switch (character) {
        case 'i':
            return I;
        case 'j':
            return J;
        case 'k':
            return K;
        default:
            throw new RuntimeException("Invalid Input: " + character);
        }
    }
 }
