package round2;
 
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
 
 public class C {
     public static void main(String[] args) throws FileNotFoundException {
         Kattio io;
 
 
 
         io = new Kattio(new FileInputStream("round2/C-small-attempt0.in"), new FileOutputStream("round2/C-small-0.out"));
 
 
         int cases = io.getInt();
         for (int i = 1; i <= cases; i++) {
             io.print("Case #" + i + ": ");
             new C().solve(io);
         }
         io.close();
     }
 
     private static class Position {
         public int px, py, x, y;
 
         public Position(int px, int py, int x, int y) {
             this.px = px;
             this.py = py;
             this.x = x;
             this.y = y;
         }
 
         @Override
         public String toString() {
             return "Position{" +
                     "px=" + px +
                     ", py=" + py +
                     ", x=" + x +
                     ", y=" + y +
                     '}';
         }
     }
 
     int xsize, ysize;
     int loves[];
 
     boolean maze[][]; 
 
     private int walk(int x, int y, int px, int py) {
         if (x < 0) return (xsize+ysize)*2 - y - 1;
         if (y < 0) return x;
         if (x >= xsize) return xsize+y;
         if (y >= ysize) return xsize+ysize+xsize-x - 1;
 
         if (px > x) return maze[y][x] ? walk(x,y+1,x,y) : walk(x,y-1,x,y);
         if (px < x) return maze[y][x] ? walk(x,y-1,x,y) : walk(x,y+1,x,y);
         if (py > y) return maze[y][x] ? walk(x+1,y,x,y) : walk(x-1,y,x,y);
         if (py < y) return maze[y][x] ? walk(x-1,y,x,y) : walk(x+1,y,x,y);
         throw new RuntimeException();
     }
 
     private Position getStart(int courtier) {
         if (courtier < xsize) {
             return new Position(courtier, -1, courtier, 0);
         }
         if (courtier < xsize+ysize) {
             return new Position(xsize, courtier - xsize, xsize - 1, courtier - xsize);
         }
         if (courtier < xsize+ysize+xsize) {
             return new Position(xsize+ysize+xsize-courtier-1,ysize, xsize+ysize+xsize-courtier-1,ysize-1);
         }
         return new Position(-1, xsize+ysize+xsize+ysize-courtier-1,0, xsize+ysize+xsize+ysize-courtier-1);
     }
 
     private boolean isValid() {
         for (int i = 0; i < loves.length; i+=2) {
             Position c1 = getStart(loves[i]);
             int result = walk(c1.x, c1.y, c1.px, c1.py);
             if (result != loves[i+1]) return false;
 
             Position c2 = getStart(loves[i+1]);
             result = walk(c2.x, c2.y, c2.px, c2.py);
             if (result != loves[i]) return false;
         }
         return true;
     }
 
     private void solve(Kattio io) {
         io.println();
         ysize = io.getInt();
         xsize = io.getInt();
         loves = new int[2*(ysize+xsize)];
         for (int i = 0; i < loves.length; i++) {
             loves[i] = io.getInt() - 1;
         }
 
         maze = new boolean[ysize][xsize];
         boolean found = false;
         for (int i = 0; i < 1 << (ysize * xsize) && !found; i++) {
             for (int j = 0; j < (ysize * xsize); j++) {
                 int x = j%xsize, y = j/xsize;
                 maze[y][x] = ((1<<j) & i) > 0;
             }
             if (isValid()) {
                 for (int y = 0; y < ysize; y++) {
                     for (int x = 0; x < xsize; x++) {
                         io.print(maze[y][x] ? '/' : '\\');
                     }
                     io.println();
                 }
                 found=true;
             }
         }
         if (!found) io.println("IMPOSSIBLE");
 
 
     }
 }
