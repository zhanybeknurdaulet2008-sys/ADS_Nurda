public class Experiment {
    public void runTraversals(Graph g) {
        long bfsStart = System.nanoTime();
        g.bfs(0);
        long bfsEnd = System.nanoTime();
        long dfsStart = System.nanoTime();
        g.dfs(0);
        long dfsEnd = System.nanoTime();
        System.out.println("BFS Time: " + (bfsEnd - bfsStart) + " ns");
        System.out.println("DFS Time: " + (dfsEnd - dfsStart) + " ns");
    }
    public void runMultipleTests() {
        testGraph(10);
        testGraph(30);
        testGraph(100);
    }
    private void testGraph(int size) {
        System.out.println("\n===== Graph Size: " + size + " =====");
        Graph graph = new Graph();
        for (int i = 0; i < size; i++) {
            graph.addVertex(new Vertex(i));
        }
        for (int i = 0; i < size - 1; i++) {
            graph.addEdge(i, i + 1);

            if (i + 2 < size) {
                graph.addEdge(i, i + 2);
            }
        }
        if (size == 10) {
            graph.printGraph();
        }
        runTraversals(graph);
    }
    public void printResults() {
        System.out.println("\nExperiments completed successfully.");
    }
}
