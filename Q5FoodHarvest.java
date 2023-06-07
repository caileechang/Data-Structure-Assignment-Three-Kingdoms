package ds.assignment;

/**
 *
 * @author husnaihsan
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Q5FoodHarvest {

    private int numOfVertices;
    private int[] path;
    private Graph graph;

    public void findHamiltonianCycle(Graph graph) {
        this.graph = graph;
        numOfVertices = graph.source.size();
        path = new int[graph.source.size()];
        Arrays.fill(path, -1);

        path[0] = 0;
        if (solve(1)) {
            printHamiltonianCycle();
        } else {
            // Create a new instance of FoodHarvest and a new graph to include the removed node 
            Q5FoodHarvest newGraph = new Q5FoodHarvest();
            Graph graph2 = newGraph.createGraph(10, 11);
            newGraph.findHamiltonianCycle(graph2);
        }
    }

    private boolean solve(int position) {
        if (position == graph.source.size()) {
            // Check if there is an edge from the last vertex to the starting vertex
            if (graph.hasEdge(path[position - 1], path[0])) {
                return true;
            } else {
                return false;
            }
        }

        for (int vertexIndex = 0; vertexIndex < graph.source.size(); ++vertexIndex) {
            if (isSafe(vertexIndex, position)) {
                path[position] = vertexIndex;
                if (solve(position + 1)) {
                    return true;
                }
                path[position] = -1;
            }
        }
        return false;
    }

    private boolean isSafe(int index, int position) {
        // Check if there is an edge between the current vertex and the previous vertex
        if (!graph.hasEdge(path[position - 1], index)) {
            return false;
        }

        // Check if the current vertex is already visited
        for (int i = 0; i < position; ++i) {
            if (path[i] == index) {
                return false;
            }
        }

        return true;
    }

    private void printHamiltonianCycle() {
        System.out.println("Path:");
        for (int i = 0; i < numOfVertices; ++i) {
            if ((path[i] + 1) >= graph.getNumToSkip()) {
                System.out.print(path[i] + 2);
                if (i <= numOfVertices - 1) {
                    System.out.print(" -> ");
                }
            } else {
                System.out.print(path[i] + 1);
                if (i <= numOfVertices - 1) {
                    System.out.print(" -> ");
                }
            }
        }

        System.out.println(1);
    }

    private Graph createGraph(int numGraph, int numToSkip) {
        Graph graph = new Graph(numGraph, numToSkip);
        // Add edges to the graph
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 6);
        graph.addEdge(1, 10);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);
        graph.addEdge(7, 8);
        graph.addEdge(7, 9);
        graph.addEdge(8, 9);
        graph.addEdge(8, 10);
        graph.addEdge(9, 10);
        graph.addDirectedEdge(3, 7); // Directed edge

        if (numToSkip <= numGraph) {
            // Remove the specified node from the adjacency list and source list
            graph.adjacencyList.remove(numToSkip - 1);
            graph.source.remove(numToSkip - 1);
            // Update the indices of the remaining nodes in the adjacency list
            for (List<Integer> neighbors : graph.adjacencyList) {
                for (int i = 0; i < neighbors.size(); i++) {
                    int neighbor = neighbors.get(i);
                    if (neighbor >= numToSkip) {
                        neighbors.set(i, neighbor - 1);
                    }
                }
            }
        }

        return graph;
    }

    public static void main(String[] args) {

        int numToSkip = 0;
        System.out.print("Enter node without food [1-10]: ");
        Scanner in = new Scanner(System.in);
        numToSkip = in.nextInt();
        int numGraph = 10;

        Q5FoodHarvest hc = new Q5FoodHarvest();
        Graph graph = hc.createGraph(numGraph, numToSkip);
        hc.findHamiltonianCycle(graph);
    }
}

class Graph {

    private int numToSkip;
    private int numOfVertices;
    protected List<List<Integer>> adjacencyList;
    protected List<Integer> source = new ArrayList<>();

    public Graph(int numOfVertices, int numToSkip) {
        this.numOfVertices = numOfVertices;
        this.numToSkip = numToSkip;
        adjacencyList = new ArrayList<>();
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int value : values) {
            source.add(value);
        }

        for (int i = 0; i < numOfVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Add undirected edge to the adjacency list
    public void addEdge(int source, int destination) {
        if (source != numToSkip && destination != numToSkip) {
            adjacencyList.get(source - 1).add(destination - 1);
            adjacencyList.get(destination - 1).add(source - 1);
        }
    }

    // Add directed edge to the adjacency list
    public void addDirectedEdge(int source, int destination) {
        adjacencyList.get(source - 1).add(destination - 1);
    }

    public int getNumOfVertices() {
        return numOfVertices;
    }

    public int getRemovedNode() {
        return numToSkip;
    }

    // Check if there is an edge between the source and destination vertices
    public boolean hasEdge(int source, int destination) {
        return adjacencyList.get(source).contains(destination);
    }

    public int getNumToSkip() {
        return numToSkip;
    }

    // Print the adjacency list representation of the graph
    public void printAdjacencyList() {
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.print(source.get(i) + " -> ");
            for (int neighbor : adjacencyList.get(i)) {
                if ((neighbor + 1) >= numToSkip) {
                    System.out.print((neighbor + 2) + " ");
                } else {
                    System.out.print((neighbor + 1) + " ");
                }
            }
            System.out.println();
        }
    }
}
