

package assignmentdsq7;


public class AssignmentDSQ7 {

   
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 1, 0, 0, 1, 0, 0, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 1, 1, 0, 1, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {1, 0, 0, 0, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0}
        };
        
        int clusterCount = findBattleshipClusters(matrix);
        System.out.println("Number of battleship clusters: " + clusterCount);
    }
    
    public static int findBattleshipClusters(int[][] matrix) {
        int clusterCount = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    dfs(matrix, i, j, visited);
                    clusterCount++;
                }
            }
        }
        
        return clusterCount;
    }
    
    public static void dfs(int[][] matrix, int row, int col, boolean[][] visited) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        if (row < 0 || row >= rows || col < 0 || col >= cols || matrix[row][col] == 0 || visited[row][col])
            return;
        
        visited[row][col] = true;
        
        // Perform DFS in all eight adjacent cells
        dfs(matrix, row - 1, col, visited); // Up
        dfs(matrix, row + 1, col, visited); // Down
        dfs(matrix, row, col - 1, visited); // Left
        dfs(matrix, row, col + 1, visited); // Right
        dfs(matrix, row - 1, col - 1, visited); // Diagonal: Up-left
        dfs(matrix, row - 1, col + 1, visited); // Diagonal: Up-right
        dfs(matrix, row + 1, col - 1, visited); // Diagonal: Down-left
        dfs(matrix, row + 1, col + 1, visited); // Diagonal: Down-right
    }
}
