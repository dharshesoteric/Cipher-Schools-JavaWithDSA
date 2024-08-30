import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MazeSolverUpdated1 {

    private static final int[] ROW_MOVES = {0, 0, -1, 1}; // Up, Down
    private static final int[] COL_MOVES = {1, -1, 0, 0}; // Right, Left

    public static int solveMaze(int[][] maze, int startX, int startY, int endX, int endY) {
        int rows = maze.length;
        int cols = maze[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startY, startX, 0}); // {row, col, distance}

        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];
            int dist = current[2];

            if (y == endY && x == endX) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int newY = y + ROW_MOVES[i];
                int newX = x + COL_MOVES[i];

                if (isValidMove(newY, newX, maze, visited)) {
                    queue.offer(new int[]{newY, newX, dist + 1});
                    visited[newY][newX] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isValidMove(int y, int x, int[][] maze, boolean[][] visited) {
        int rows = maze.length;
        int cols = maze[0].length;
        return y >= 0 && y < rows && x >= 0 && x < cols && maze[y][x] == 0 && !visited[y][x];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = scanner.nextInt();

        int[][] maze = new int[rows][cols];

        System.out.println("Enter the maze (0 for open, 1 for wall):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter starting position (x y) from bottom-left corner: ");
        int startX = scanner.nextInt(); // Horizontal position
        int startY = rows - 1 - scanner.nextInt(); // Convert to vertical position

        System.out.print("Enter ending position (x y) from bottom-left corner: ");
        int endX = scanner.nextInt(); // Horizontal position
        int endY = rows - 1 - scanner.nextInt(); // Convert to vertical position

        int result = solveMaze(maze, startX, startY, endX, endY);

        if (result != -1) {
            System.out.println("Shortest path length is: " + result);
        } else {
            System.out.println("No path exists from start to end.");
        }

        scanner.close();
    }
}

