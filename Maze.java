package DS_Assignment;

public class Maze {

    public static void solveMaze(int [][] maze, int [][] solution, int startX, int startY, int endX, int endY) {
        if (solve(maze, solution, startX, startY, endX, endY)) {
            displaySolution(maze, solution);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean solve(int[][] maze, int [][] solution, int x, int y, int endX, int endY) {
        if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length || maze[x][y] == 1 || solution[x][y] == 1) {
            return false;
        }

        solution[x][y] = 1;

        if (x == endX && y == endY) {
            return true;
        }

        if (solve(maze, solution, x + 1, y, endX, endY)) { // go down
            return true;
        }

        if (solve(maze, solution, x, y + 1, endX, endY)) { // go right
            return true;
        }

        if (solve(maze, solution, x - 1, y, endX, endY)) { // go up
            return true;
        }

        if (solve(maze, solution, x, y - 1, endX, endY)) { // go left
            return true;
        }

        solution[x][y] = 0; // backtrack
        return false;
    }

    private static void displaySolution(int [][] maze, int [][] solution) {
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[0].length; j++) {
                if (maze[i][j] == 1) {
                    System.out.print("# "); // obstacle
                } else if (maze[i][j] == 2) {
                    System.out.print("S "); // start
                } else if (maze[i][j] == 3) {
                    System.out.print("E "); // end
                } else if (solution[i][j] == 1) {
                    System.out.print("C "); // path
                } else {
                    System.out.print("  "); // empty space
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[])
    {
        
        int [][] maze = {
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
        
        int startX=0, startY=0, endX=0, endY=0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 2) {
                    startX = i;
                    startY = j;
                } else if (maze[i][j] == 3) {
                    endX = i;
                    endY = j;
                }
            }
        }
        int [][] solution = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        solveMaze(maze, solution, startX, startY, endX, endY);
    }

}
