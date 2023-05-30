
package assignmentdsq8;

import java.util.*;

public class AssignmentDSQ8 {


    static class Cell {
        int row;
        int col;
        String path;

        Cell(int row, int col, String path) {
            this.row = row;
            this.col = col;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        int rows = maze.length;
        int cols = maze[0].length;

        Queue<Cell> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        // Starting position
        int startRow = -1;
        int startCol = -1;

        // Find the starting position
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maze[i][j] == 2) {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
            if (startRow != -1) {
                break;
            }
        }

        // Enqueue the starting position
        queue.offer(new Cell(startRow, startCol, ""));

        while (!queue.isEmpty()) {
            Cell currentCell = queue.poll();
            int row = currentCell.row;
            int col = currentCell.col;
            String path = currentCell.path;

            // Check if the current position is the exit
            if (maze[row][col] == 3) {
                System.out.println("Path of Cao Cao's escape: " + path);
                printMazeWithEscapePath(maze, path);
                break;
            }

            // Mark the current position as visited
            visited[row][col] = true;

            // Explore neighboring cells
            if (row - 1 >= 0 && maze[row - 1][col] != 1 && !visited[row - 1][col]) {
                queue.offer(new Cell(row - 1, col, path + "Up "));
            }
            if (row + 1 < rows && maze[row + 1][col] != 1 && !visited[row + 1][col]) {
                queue.offer(new Cell(row + 1, col, path + "Down "));
            }
            if (col - 1 >= 0 && maze[row][col - 1] != 1 && !visited[row][col - 1]) {
                queue.offer(new Cell(row, col - 1, path + "Left "));
            }
            if (col + 1 < cols && maze[row][col + 1] != 1 && !visited[row][col + 1]) {
                queue.offer(new Cell(row, col + 1, path + "Right "));
            }
        }
    }

    public static void printMazeWithEscapePath(int[][] maze, String path) {
        int rows = maze.length;
        int cols = maze[0].length;

        int[][] pathMaze = new int[rows][cols];

        // Copy the original maze to the path maze
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                pathMaze[i][j] = maze[i][j];
            }
        }

        // Mark the path in the path maze
        String[] directions = path.split(" ");
        int row = -1;
        int col = -1;

        // Find the starting position
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pathMaze[i][j] == 2) {
                    row = i;
                    col = j;
                    break;
                }
            }
            if (row != -1) {
                break;
            }
        }

        // Mark the path in the path maze
        for (String direction : directions) {
            switch (direction) {
                case "Up":
                    pathMaze[row][col] = 9;
                    row--;
                    break;
                case "Down":
                    pathMaze[row][col] = 9;
                    row++;
                    break;
                case "Left":
                    pathMaze[row][col] = 9;
                    col--;
                    break;
                case "Right":
                    pathMaze[row][col] = 9;
                    col++;
                    break;
            }
        }

        // Print the path maze
        System.out.println("Maze with Cao Cao's escape path:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pathMaze[i][j] == 9) {
                    System.out.print("* ");
                } else {
                    System.out.print(pathMaze[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
