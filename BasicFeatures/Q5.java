/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DSAssignment.BasicFeatures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Q5 {

    private int numToSkip;
    private int numOfVertices;
    private int[] path;
    private Graph graph;

    public void findHamiltonianCycle(Graph graph) {
        this.graph = graph;
        numOfVertices = graph.source.size();
        numToSkip = graph.getNumToSkip();
        path = new int[graph.source.size()];
        Arrays.fill(path, -1);

        path[0] = 0;
        if (solve(1)) {
            System.out.println("Path exists:");
            printHamiltonianCycle();
        } else {
            System.out.println("Path does not exist.");
        }
    }

    private boolean solve(int position) {
        if (position == graph.source.size()) {
            if (graph.hasEdge(path[position - 1], path[0])) {
                return true;
            } else {
                return false;
            }
        }

        for (int vertexIndex = 0; vertexIndex < graph.source.size(); ++vertexIndex) {
            // int vertex = graph.source.get(vertexIndex);
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
        if (!graph.hasEdge(path[position - 1], index)) {
            return false;
        }

        for (int i = 0; i < position; ++i) {
            if (path[i] == index) {
                return false;
            }
        }

        return true;
    }

    private void printHamiltonianCycle() {
        for (int i = 0; i < numOfVertices; ++i) {
            if ((path[i] + 1) >= graph.getNumToSkip()) {
                System.out.print(path[i] + 2);
                if (i <= numOfVertices - 1) {
                    System.out.print("->");
                }
            } else {
                System.out.print(path[i] + 1);
                if (i <= numOfVertices - 1) {
                    System.out.print("->");
                }
            }
        }

        System.out.println(1);
    }

    public static void main(String[] args) {
        int numToSkip = 9;
        System.out.print("Enter node without food: ");
        Scanner in = new Scanner(System.in);
        numToSkip = in.nextInt();
        int numGraph = 10;

        Graph graph = new Graph(numGraph, numToSkip);
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
            graph.adjacencyList.remove(numToSkip - 1);
            graph.source.remove(numToSkip - 1);
            for (List<Integer> neighbors : graph.adjacencyList) {
                for (int i = 0; i < neighbors.size(); i++) {
                    int neighbor = neighbors.get(i);
                    if (neighbor >= numToSkip) {
                        neighbors.set(i, neighbor - 1);
                    }
                }
            }
        }

//        System.out.println("source size: " + graph.source.size());
//        System.out.println("list size: " + graph.adjacencyList.size());
//        graph.printAdjacencyList();
        Q5 hc = new Q5();
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

    public void addEdge(int source, int destination) {
        if (source != numToSkip && destination != numToSkip) {
            adjacencyList.get(source - 1).add(destination - 1);
            adjacencyList.get(destination - 1).add(source - 1);
        }
    }

    public void addDirectedEdge(int source, int destination) {
        adjacencyList.get(source - 1).add(destination - 1);
    }

    public int getNumOfVertices() {
        return numOfVertices;
    }

    public boolean hasEdge(int source, int destination) {
        return adjacencyList.get(source).contains(destination);
    }

    public int getNumToSkip() {
        return numToSkip;
    }

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
