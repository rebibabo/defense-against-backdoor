package googlecodejam2017;
 
 import googlecodejam2017.GoogleCodeJam2017.Problem;
 import java.io.BufferedReader;
 import java.io.IOException;
 
 public class Round1BC implements Problem {
 
    private int N;
    private int Q;
    private Transport[] transports;
    private long[][] distances;
    private int[][] paths;
 
    private double[] shortestTimes;
 
    @Override
    public void setup(BufferedReader input) throws IOException {
        String[] args = input.readLine().split(" ");
        N = Integer.parseInt(args[0]);
        Q = Integer.parseInt(args[1]);
 
        transports = new Transport[N];
        for (int i = 0; i < N; ++i) {
            transports[i] = new Transport(input.readLine());
        }
 
        distances = new long[N][N];
        for (int i = 0; i < N; ++i) {
            args = input.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                distances[i][j] = Long.parseLong(args[j]);
            }
        }
 
        paths = new int[Q][2];
        for (int i = 0; i < Q; ++i) {
            args = input.readLine().split(" ");
            paths[i][0] = Integer.parseInt(args[0]);
            paths[i][1] = Integer.parseInt(args[1]);
        }
 
    }
 
    @Override
    public void solve() {
        shortestTimes = new double[Q];
 
        for (int i = 0; i < Q; ++i) {
            DijkstraSolver dijsktraSolver = new DijkstraSolver(paths[i][0] - 1, paths[i][1] - 1);
            shortestTimes[i] = dijsktraSolver.solve();
        }
    }
 
    @Override
    public String getSolution() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Q; ++i) {
            builder.append(Double.toString(shortestTimes[i])).append(" ");
        }
        return builder.toString();
    }
 
    private static class Transport {
 
        private long maxDistance;
        private int speed;
 
        public Transport(String arg) {
            String[] args = arg.split(" ");
            maxDistance = Long.parseLong(args[0]);
            speed = Integer.parseInt(args[1]);
        }
 
        public Transport(long maxDistance, int speed) {
            this.maxDistance = maxDistance;
            this.speed = speed;
        }
    }
 
    private class DijkstraSolver {
 
        private static final long DIST_NO_ROUTE = -1l;
        private static final double TIME_INFINITY = -1d;
        private static final int NODE_NONE = -1;
 
        private int fromNode;
        private int toNode;
        private double[][] edgeTimes;
        private double[] nodeTimes;
        private boolean[] visitedNodes;
 
        public DijkstraSolver(int from, int to) {
            this.fromNode = from;
            this.toNode = to;
        }
 
        public int getNextNodeToVisit() {
            double currMin = TIME_INFINITY;
            int currIndex = -1;
            for (int i = 0; i < N; ++i) {
                if (visitedNodes[i]) {
                    continue;
                }
 
                double currTime = nodeTimes[i];
                if (currTime != TIME_INFINITY && (currMin == TIME_INFINITY || currTime < currMin)) {
                    currMin = currTime;
                    currIndex = i;
                }
            }
 
            return currIndex;
        }
 
        public void calculateEdgeTimes(int orgNode, int speed, boolean[] visited, int currNode, double currTime, long distanceLeft) {
            if (visited[currNode]) {
                return;
            }
 
            visited[currNode] = true;
            for (int i = 0; i < N; ++i) {
                if (visited[i]) {
                    continue;
                }
 
                long distance = distances[currNode][i];
                if (distance == DIST_NO_ROUTE || distance > distanceLeft) {
                    continue;
                }
 
                double nextTime = (double) distance / speed;
                edgeTimes[orgNode][i] = currTime + nextTime;
 
                calculateEdgeTimes(orgNode, speed, visited, i, currTime + nextTime, distanceLeft - distance);
            }
            visited[currNode] = false;
        }
 
        public void doNextVisit() {
            int currNode = getNextNodeToVisit();
 
            if (currNode == NODE_NONE || currNode == toNode) {
                return;
            }
 
            Transport transport = transports[currNode];
            calculateEdgeTimes(currNode, transport.speed, new boolean[N], currNode, 0, transport.maxDistance);
 
            visitedNodes[currNode] = true;
            double currTime = nodeTimes[currNode];
 
            for (int i = 0; i < N; ++i) {
                if (visitedNodes[i]) {
                    continue;
                }
 
                double edgeTime = edgeTimes[currNode][i];
                if (edgeTime == TIME_INFINITY) {
                    continue;
                }
 
                double currMinTime = nodeTimes[i];
                if (currMinTime == TIME_INFINITY || currTime + edgeTime < currMinTime) {
                    nodeTimes[i] = currTime + edgeTime;
                }
 
            }
 
            doNextVisit();
        }
 
        public double solve() {
            nodeTimes = new double[N];
            for (int i = 0; i < N; ++i) {
                if (i == fromNode) {
                    nodeTimes[i] = 0;
                } else {
                    nodeTimes[i] = TIME_INFINITY;
                }
            }
 
            edgeTimes = new double[N][N];
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    edgeTimes[i][j] = TIME_INFINITY;
                }
            }
 
            visitedNodes = new boolean[N];
            for (int i = 0; i < N; ++i) {
                visitedNodes[i] = false;
            }
 
            doNextVisit();
 
            return nodeTimes[toNode];
        }
    }
 
 }
