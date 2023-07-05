
 package durazom.fashionshow;
 
 import durazom.bathroomstalls.*;
 import durazom.tidynumbers.*;
 import durazom.util.Case;
 import java.math.BigInteger;
 import java.util.AbstractList;
 import java.util.ArrayList;
 import java.util.Comparator;
 import java.util.HashMap;
 import java.util.LinkedHashMap;
 import java.util.LinkedList;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Queue;
 import java.util.Stack;
 
 
 public class FashionShowCase implements Case {
 
     int cases = 1;
 
     @Override
     public String solve(String caseString) {
 
         String[] split = caseString.split("\n");
 
         String[] nm = split[0].split(" ");
 
         Integer N = Integer.parseInt(nm[0]);
         Integer M = Integer.parseInt(nm[1]);
 
         Board board = new Board(N);
 
         if (M > 0) {
 
             for (int i = 1; i < M + 1; i++) {
 
                 String[] modelLine = split[i].split(" ");
 
                 String type = modelLine[0];
                 Integer xCoordinate = Integer.parseInt(modelLine[1]);
                 Integer yCoordinate = Integer.parseInt(modelLine[2]);
 
                 char typeChar = type.charAt(0);
 
                 board.changeType(xCoordinate, yCoordinate, typeChar, false, true);
 
             }
 
         }
 
         addModels(board, 'o', true);
         addModels(board, 'x', false);
         addModels(board, '+', false);
 
         int style = board.getStyle();
 
         List<Spot> changedSpots = board.getChangedSpots();
 
         String styleAndChanges = style + " " + changedSpots.size();
 
         String result = "Case #" + (cases++) + ": ";
 
         result += styleAndChanges;
 
         result = changedSpots.stream().map((s) -> "\n" + s.toString()).reduce(result, String::concat);
 
         return result;
 
     }
 
     private void addModels(Board b, char modelType, boolean replaceExisting) {
 
         int N = b.size();
 
         for (int i = 0; i < N; i++) {
 
             int X = i + 1;
 
             for (int j = 0; j < N; j++) {
 
                 int Y = j + 1;
 
                 if( b.getSpot(X, Y).type != modelType ){
                 
                     b.changeType(X, Y, modelType, true, replaceExisting);
                 
                 }
 
             }
 
         }
 
     }
 
     private class Board {
 
         public int size() {
 
             return spots.size();
 
         }
 
         List<List<Spot>> history = new ArrayList<List<Spot>>();
 
         public int getStyle() {
 
             int N = size();
             int totalStyle = 0;
 
             for (int i = 0; i < N; i++) {
 
                 int X = i + 1;
 
                 for (int j = 0; j < N; j++) {
 
                     int Y = j + 1;
 
                     totalStyle += getSpot(X, Y).getStyle();
 
                 }
 
             }
 
             return totalStyle;
 
         }
 
         public List<Spot> getChangedSpots() {
 
             int N = size();
             List<Spot> changeList = new ArrayList<>();
 
             for (int i = 0; i < N; i++) {
 
                 for (int j = 0; j < N; j++) {
 
                     Spot s = history.get(i).get(j);
 
                     if (s.getType() != '.') {
 
                         changeList.add(s);
 
                     }
 
                 }
 
             }
 
             return changeList;
 
         }
 
         List<List<Spot>> spots = new ArrayList<List<Spot>>();
 
         public Board(int size) {
 
             for (int i = 0; i < size; i++) {
 
                 spots.add(new ArrayList<>());
                 history.add(new ArrayList<>());
 
                 for (int j = 0; j < size; j++) {
 
                     spots.get(i).add(new Spot('.', i + 1, j + 1));
                     history.get(i).add(new Spot('.', i + 1, j + 1));
 
                 }
 
             }
 
         }
 
         public boolean changeType(int x, int y, char newType, boolean validate, boolean replaceExisting) {
 
             Spot s = spots.get(x - 1).get(y - 1);
 
             if (!replaceExisting) {
 
                 if (s.type != '.') {
 
                     return false;
 
                 }
 
             }
 
             char lastType = s.getType();
 
             s.setType(newType);
 
             if (validate) {
 
                 if ( validateFromCoordinate(x, y)) {
 
                     Spot historyS = history.get(x - 1).get(y - 1);
                     historyS.setType(newType);
 
                     return true;
 
                 } else {
 
                     s.setType(lastType);
 
                     return false;
 
                 }
 
             }
             
             return true;
 
         }        
 
         private boolean validateFromCoordinate(int x, int y) {
 
             if (!validateRow(x)) {
 
                 return false;
 
             }
 
             if (!validateCol(y)) {
 
                 return false;
 
             }
 
             if (!validateDiagonals(x, y)) {
 
                 return false;
 
             }
 
             return true;
 
         }
 
         private boolean validateDiagonals(int x, int y) {
 
             if (!validateDiagonalLtoR(x, y)) {
 
                 return false;
 
             }
 
             if (!validateDiagonalRtoL(x, y)) {
 
                 return false;
 
             }
 
             return true;
 
         }
 
         private boolean validateDiagonalLtoR(int x, int y) {
 
             
             int startingX = x;
             int startingY = y;
 
             while (startingX > 1 && startingY > 1) {
 
                 startingX--;
                 startingY--;
 
             }
 
             
             
             int N = spots.size() + 1;
 
             int xModels = 0;
             int totalModels = 0;
 
             while (startingX < N && startingY < N) {
 
                 Spot s = getSpot(startingX, startingY);
 
                 if (s.type != '.') {
 
                     totalModels++;
 
                     if (s.type == 'x') {
 
                         xModels++;
 
                     }
 
                 }
 
                 startingX++;
                 startingY++;
 
             }
 
             if (totalModels > 1) {
 
                 if (totalModels - xModels > 1) {
 
                     return false;
 
                 }
 
             }
 
             return true;
 
         }
 
         private boolean validateDiagonalRtoL(int x, int y) {
 
             
             int startingX = x;
             int startingY = y;
 
             int N = spots.size();
 
             while (startingX > 1 && startingY < N) {
 
                 startingX--;
                 startingY++;
 
             }
 
             
             
             int xModels = 0;
             int totalModels = 0;
 
             while (startingX < N + 1 && startingY > 0) {
 
                 Spot s = getSpot(startingX, startingY);
 
                 if (s.type != '.') {
 
                     totalModels++;
 
                     if (s.type == 'x') {
 
                         xModels++;
 
                     }
 
                 }
 
                 startingX++;
                 startingY--;
 
             }
 
             if (totalModels > 1) {
 
                 if (totalModels - xModels > 1) {
 
                     return false;
 
                 }
 
             }
 
             return true;
 
         }
 
         public Spot getSpot(int x, int y) {
 
             return spots.get(x - 1).get(y - 1);
 
         }
 
         private boolean validateRow(int x) {
 
             List<Spot> row = spots.get(x - 1);
 
             int plusModels = 0;
             int totalModels = 0;
 
             for (Spot s : row) {
 
                 if (s.type != '.') {
 
                     totalModels++;
 
                     if (s.type == '+') {
 
                         plusModels++;
 
                     }
 
                 }
 
             }
 
             if (totalModels > 1) {
 
                 if (totalModels - plusModels > 1) {
 
                     return false;
 
                 }
 
             }
 
             return true;
 
         }
 
         private boolean validateCol(int y) {
 
             int plusModels = 0;
             int totalModels = 0;
 
             for (int i = 0; i < spots.size(); i++) {
 
                 Spot s = spots.get(i).get(y - 1);
 
                 if (s.type != '.') {
 
                     totalModels++;
 
                     if (s.type == '+') {
 
                         plusModels++;
 
                     }
 
                 }
 
             }
 
             if (totalModels > 1) {
 
                 if (totalModels - plusModels > 1) {
 
                     return false;
 
                 }
 
             }
 
             return true;
 
         }
 
     }
 
     private class Spot {
 
         private char type = '.';
         private int x = 0;
         private int y = 0;
 
         public Spot(char t, int x, int y) {
 
             type = t;
             this.x = x;
             this.y = y;
 
         }
 
         public Spot() {
         }
 
         public char getType() {
             return type;
         }
 
         public void setType(char type) {
             this.type = type;
         }
 
         public int getX() {
             return x;
         }
 
         public void setX(int x) {
             this.x = x;
         }
 
         public int getY() {
             return y;
         }
 
         public void setY(int y) {
             this.y = y;
         }
 
         public int getStyle() {
 
             switch (this.type) {
                 case 'o':
                     return 2;
                 case '+':
                 case 'x':
                     return 1;
             }
             return 0;
 
         }
 
         @Override
         public String toString() {
 
             return type + " " + x + " " + y;
 
         }
 
     }
 
 }
