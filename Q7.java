
package assignmentdsq7;

import java.util.Scanner;

public class AssignmentDSQ7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = sc.nextInt();

        int[][] matrix = new int[rows][cols];
        System.out.println("Enter the matrix elements (0s and 1s):");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int clusterCount = findBattleShipClusters(matrix);
        System.out.println("Number of clusters: " + clusterCount);

        sc.close();
    }

    private static int findBattleShipClusters(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int clusterCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    exploreCluster(matrix, visited, i, j);
                    clusterCount++;
                }
            }
        }

        return clusterCount;
    }

    private static void exploreCluster(int[][] matrix, boolean[][] visited, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (row < 0 || col < 0 || row >= rows || col >= cols || visited[row][col] || matrix[row][col] == 0) {
            return;
        }

        visited[row][col] = true;

        exploreCluster(matrix, visited, row - 1, col); // Up
        exploreCluster(matrix, visited, row + 1, col); // Down
        exploreCluster(matrix, visited, row, col - 1); // Left
        exploreCluster(matrix, visited, row, col + 1); // Right
    }
}
