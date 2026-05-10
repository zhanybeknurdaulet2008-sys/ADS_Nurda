import java.util.*;

public class Graph {
    private Map<Integer, List<Integer>> adjacencyList;
    public Graph() {
        adjacencyList = new HashMap<>();
    }
    public void addVertex(Vertex v) {
        adjacencyList.putIfAbsent(v.getId(), new ArrayList<>());
    }
    public void addEdge(int from, int to) {
        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
    }
    public void printGraph() {
        System.out.println("Graph Structure:");
        for (Integer vertex : adjacencyList.keySet()) {
            System.out.print(vertex + " -> ");
            for (Integer neighbor : adjacencyList.get(vertex)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println();
    }
    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        System.out.print("DFS Traversal: ");
        dfsHelper(start, visited);
        System.out.println();
    }
    private void dfsHelper(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");
        for (int neighbor : adjacencyList.get(vertex)) {

            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }
}
