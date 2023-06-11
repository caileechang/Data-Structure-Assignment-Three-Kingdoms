package DSAssignment;

import java.util.*;

public class BF4_EnemyFortressAttack {

    public static void main(String[] args) {
        //create graph
        paths graph = new paths(10);
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

        Scanner sc = new Scanner(System.in);
        //create and initialize 
        int source = 1;
        int destination;
        while (true) {
            System.out.print("Enter enemy base camp(2-10): ");
            destination = sc.nextInt();
            if (destination <= 10 && destination >= 2) {
                break;
            } else {
                System.out.println("Invalid enemy base camp. Try again.");
            }
        }

        System.out.println("Enemy base camp: " + destination);

        //Find shortest path from source to destination
        List<List<Integer>> allPaths = graph.findPaths(source, destination);

        //Find shortest distance
        int distance = Integer.MAX_VALUE;
        for (List<Integer> path : allPaths) {
            if (path.size() < distance) {
                distance = path.size();
            }
        }

        //Display all paths with shortest distance
        System.out.println("\nBest path:");
        for (List<Integer> path : allPaths) {
            int i = 1;
            for (int node : path) {
                System.out.print(node);
                if (i < distance) {
                    System.out.print("->");
                }
                i++;
            }
            System.out.println();
        }
        System.out.println("");
    }

}

class paths {

    private int V; // Number of vertices
    private List<Integer>[] adjList; // Adjacency list representation

    public paths(int V) {
        this.V = V;
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addDirectedEdge(int u, int v) {
        adjList[u].add(v);
    }

    public void addEdge(int u, int v) {
        addDirectedEdge(u, v);
        addDirectedEdge(v, u);
    }

    public List<List<Integer>> findPaths(int source, int destination) {
        List<List<Integer>> allPaths = new ArrayList<>();
        int shortestDistance = Integer.MAX_VALUE;
        // Queue for BFS traversal
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(source);
        queue.add(path);

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int lastNode = currentPath.get(currentPath.size() - 1);

            // If the path is not the shortest
            if (currentPath.size()>shortestDistance){
                break;
            }
            
            // If destination node is reached, add the path to the result
            if (lastNode == destination) {
                allPaths.add(currentPath);
                shortestDistance=currentPath.size();
            }           
            
            // Enqueue all adjacent nodes
            for (int adjacentNode : adjList[lastNode]) {
                // Avoid revisiting nodes already in the current path
                if (!currentPath.contains(adjacentNode)) {
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(adjacentNode);
                    queue.add(newPath);
                }
            }
        }
        return allPaths;
    }
}
