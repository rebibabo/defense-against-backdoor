import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 
 
 import java.util.Arrays;
 
 
 import org.sat4j.core.VecInt;
 import org.sat4j.minisat.SolverFactory;
 import org.sat4j.specs.ContradictionException;
 import org.sat4j.specs.IProblem;
 import org.sat4j.specs.ISolver;
 import org.sat4j.specs.TimeoutException;
 
 
 public class Beam {
    static final int MAXVAR = 1000000;
    static final int NBCLAUSES = 500000;
    public static void main(String[] args) throws IOException, ContradictionException, TimeoutException{
        BufferedReader buf = new BufferedReader(new FileReader("beam.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beam.out")));
        int t = Integer.parseInt(buf.readLine());
        for(int i = 0; i < t; i++) {
            String[] s = buf.readLine().split(" ");
            int r = Integer.parseInt(s[0]);
            int c = Integer.parseInt(s[1]);
            Character[][] grid = new Character[r][c];
            Boolean[][] vtest = new Boolean[r][c];
            Boolean[][] htest = new Boolean[r][c];
            for(int j = 0; j < r; j++){
                String in = buf.readLine();
                for(int k = 0; k < c; k++){
                    grid[j][k] = in.charAt(k);
                    if(grid[j][k] != '.') {
                        vtest[j][k] = true;
                        htest[j][k] = true;
                    }
                }
            }
            
 
 
            ISolver solver = SolverFactory.newDefault();
 
            
            solver.newVar(MAXVAR);
            solver.setExpectedNumberOfClauses(NBCLAUSES);
            
            
            
            
            
            boolean done = false;
            try{
            for(int j = 0; j < r; j++){
                for(int k = 0; k < c; k++){
                    if(grid[j][k] == '.'){
                        int a1 = calc(1, vtest, htest, grid, j, k, r, c);
                        int a2 = calc(3, vtest, htest, grid, j, k, r, c);
                        int b1 = calc(2, vtest, htest, grid, j, k, r, c);
                        int b2 = calc(4, vtest, htest, grid, j, k, r, c);
                        if((a1 == 0 && a2 == 0) && (b1 == 0 && b2 == 0)){
                            out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
                            done = true;
                            break;
                        }
                        if (a1 == 0 && a2 == 0) {
                            if(b1 == 0) {
                                int[] c1 = {b2};
                                solver.addClause(new VecInt(c1));
                            } else if (b2 == 0) {
                                int[] c1 = {b1};
                                solver.addClause(new VecInt(c1));
                            } else {
                                out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
                                done = true;
                                break;
                            }
                        } else if (b1 == 0 && b2 == 0) {
                            if(a1 == 0) {
                                int[] c1 = {a2};
                                solver.addClause(new VecInt(c1));
                            } else if (a2 == 0) {
                                int[] c1 = {a1};
                                solver.addClause(new VecInt(c1));
                            } else {
                                out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
                                done = true;
                                break;
                            }
                        }else if(a1 == 0){
                            if(b1 == 0) {
                                int[] c1 = {a2, b2};
                                solver.addClause(new VecInt(c1));
                            } else if (b2 == 0) {
                                int[] c1 = {a2, b1};
                                solver.addClause(new VecInt(c1));
                            } else {
                                int[] c1 = {a2};
                                solver.addClause(new VecInt(c1));
                                int[] c2 = {-b1};
                                solver.addClause(new VecInt(c2));
                                int[] c3 = {-b2};
                                solver.addClause(new VecInt(c3));
                            }
                        } else if (a2 == 0){
                            if(b1 == 0) {
                                int[] c1 = {a1, b2};
                                solver.addClause(new VecInt(c1));
                            } else if (b2 == 0) {
                                int[] c1 = {a1, b1};
                                solver.addClause(new VecInt(c1));
                            } else {
                                int[] c1 = {a1};
                                solver.addClause(new VecInt(c1));
                                int[] c2 = {-b1};
                                solver.addClause(new VecInt(c2));
                                int[] c3 = {-b2};
                                solver.addClause(new VecInt(c3));
                            }
                        } else {
                            if(b1 == 0) {
                                int[] c1 = {b2};
                                solver.addClause(new VecInt(c1));
                                int[] c2 = {-a1};
                                solver.addClause(new VecInt(c2));
                                int[] c3 = {-a2};
                                solver.addClause(new VecInt(c3));
                            } else if (b2 == 0) {
                                int[] c1 = {b1};
                                solver.addClause(new VecInt(c1));
                                int[] c2 = {-a1};
                                solver.addClause(new VecInt(c2));
                                int[] c3 = {-a2};
                                solver.addClause(new VecInt(c3));
                            } else {
                                out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
                                done = true;
                                break;
                            }
                        }
                    } else if (grid[j][k] == '|' || grid[j][k] == '-') {
                        int a1 = calc(1, vtest, htest, grid, j, k+1, r, c);
                        int a2 = calc(3, vtest, htest, grid, j, k-1, r, c);
                        int b1 = calc(2, vtest, htest, grid, j-1, k, r, c);
                        int b2 = calc(4, vtest, htest, grid, j+1, k, r, c);
                        if(a1 != 0){
                            int[] c1 = {-a1};
                            
                            solver.addClause(new VecInt(c1));
                        }
                        if(a2 != 0){
                            int[] c1 = {-a2};
                            
                            solver.addClause(new VecInt(c1));
                        }
                        if(b1 != 0){
                            int[] c1 = {-b1};
                            
                            solver.addClause(new VecInt(c1));
                        }
                        if(b2 != 0){
                            int[] c1 = {-b2};
                            
                            solver.addClause(new VecInt(c1));
                        }
                    }
                }
                if(done) break;
            }
            } catch (Exception e){
                out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
                done = true;
            }
        
            if(done) continue;
            
            
            IProblem problem = solver;
            int[] assignment = problem.findModel();
            
            if (assignment != null) {
                out.println("Case #" + (i + 1) + ": POSSIBLE");
                for(int j = 0; j < r; j++){
                    for(int k = 0; k < c; k++){
                        if(grid[j][k] == '|' || grid[j][k] == '-'){
                            int val = j * c + k + 1;
                            boolean found = false;
                            for(int l = 0; l < assignment.length; l++){
                                if(assignment[l] == val) {
                                    found = true;
                                    out.print("|");
                                } else if (assignment[l] == -val){
                                    found = true;
                                    out.print("-");
                                }
                            }
                            if(!found) out.print("|");
                        } else {
                            out.print("" + grid[j][k]);
                        }
                    }
                    out.println();
                }
                
            } else {
                out.println("Case #" + (i + 1) + ": IMPOSSIBLE");
            }
        }
        out.close();
        
        
    }
    public static int calc(int dir, Boolean[][] vtest, Boolean[][] htest, Character[][] grid, int r, int c, int maxr, int maxc){
        if(r < 0 || c < 0 || r >= maxr || c >= maxc) return 0;
        char test = grid[r][c];
 
        if(test == '|' || test == '-'){
            if(dir % 2 == 0){
                return r * maxc + c + 1;
            } else {
                return -(r * maxc + c + 1);
            }
        } else if(test == '/'){
            if(dir == 1){
                return calc(2, vtest, htest, grid, r-1, c, maxr, maxc);
            } else if (dir == 2){
                return calc(1, vtest, htest, grid, r, c+1, maxr, maxc);
            } else if (dir == 3){
                return calc(4, vtest, htest, grid, r+1, c, maxr, maxc);
            } else {
                return calc(3, vtest, htest, grid, r, c-1, maxr, maxc);
            }
        } else if(test == '\\'){
            if(dir == 1){
                return calc(4, vtest, htest, grid, r+1, c, maxr, maxc);
            } else if (dir == 2){
                return calc(3, vtest, htest, grid, r, c-1, maxr, maxc);
            } else if (dir == 3){
                return calc(2, vtest, htest, grid, r-1, c, maxr, maxc);
            } else {
                return calc(1, vtest, htest, grid, r, c+1, maxr, maxc);
            }
        } else if(test == '#'){
            return 0;
        } else if(test == '.'){
            if(dir % 2 == 0){
                
                
                if(dir == 2){
                    return calc(dir, vtest, htest, grid, r-1, c, maxr, maxc);
                } else {
                    return calc(dir, vtest, htest, grid, r+1, c, maxr, maxc);
                }
            } else {
                
                
                if(dir == 1){
                    return calc(dir, vtest, htest, grid, r, c+1, maxr, maxc);
                } else {
                    return calc(dir, vtest, htest, grid, r, c-1, maxr, maxc);
                }
            }
        }
        
        return -999999999;
    }
 }
