package RoomLightStateManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ------------------ PURPOSE ------------------
 * In a 3x3 grid representing rooms, some rooms have lights on while others are off initially.
 * Changing a room's state toggles the adjacent rooms' states (left, right, top, bottom) - on becomes off, and off becomes on.
 * However, you must change room states in a specific order. For instance, if rooms are numbered from 1 to 9, you can't change room 9 before room 8.
 * Find the shortest path to turn on all initially lit rooms.
 */
public class StateManager {
    public static int[][] rooms = {{1, 1, -1}, {1, 1, -1}, {-1, 1, 1}};
    public static int[][] numbers = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    public static List<Cell> path = new ArrayList<>();

    static class Cell {
        public int row;
        public int col;
        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static boolean checkRooms(int[][] rooms) {
        for (int[] room : rooms) {
            for (int state : room) {
                if (state == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void toggleLamps(int[][] rooms, int row, int col) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if (newRow >= 0 && newRow < rooms.length && newCol >= 0 && newCol < rooms[0].length) {
                rooms[newRow][newCol] *= -1;
            }
        }
        rooms[row][col] *= -1;
    }

    public static void findShortestPath(int[][] lamps, List path, int row, int col) {
        if (checkRooms(lamps)) {
            System.out.println("Successful");
            for (Object object : path) {
                Cell lamp = (Cell) object;
                int number = numbers[lamp.row][lamp.col];
                System.out.print("  --> " + number);
            }
            return;
        }

        ArrayList tempPath = new ArrayList<>(path);
        int[][] tempLamps = Arrays.stream(lamps).map(int[]::clone).toArray(int[][]::new);
        if(row >= lamps.length || col >= lamps.length){
            return;
        }

        toggleLamps(tempLamps, row, col);
        Cell lamp = new Cell(row, col);
        tempPath.add(lamp);

        if (col != lamps.length - 1) {
            findShortestPath(tempLamps, tempPath, row, col + 1);
            findShortestPath(lamps, path, row, col + 1);
        } else {
            findShortestPath(tempLamps, tempPath, row + 1, 0);
            findShortestPath(lamps, path, row + 1, 0);
        }
    }

    public static void main(String[] args) {
        findShortestPath(rooms, path, 0, 0);
    }
}