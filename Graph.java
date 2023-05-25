/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds.assignment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
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
                if((neighbor+1)>=numToSkip)
                    System.out.print((neighbor + 2) + " ");
                else
                System.out.print((neighbor + 1) + " ");
            }
            System.out.println();
        }
    }
}


