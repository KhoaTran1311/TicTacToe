package tictactoe.model.state;

import java.util.Arrays;

public class Board {
    private int[][] state;
    private final int boardSideLength;

    public Board() {
        boardSideLength = 3;
        state = new int[boardSideLength][boardSideLength];
    }

    public Board(int boardSideLength) {
        this.boardSideLength = boardSideLength;
        state = new int[boardSideLength][boardSideLength];
    }

    // deep coy
    public Board(Board board) {
        this.boardSideLength = board.boardSideLength;
        int[][] copiedState = new int[boardSideLength][boardSideLength];

        for (int i = 0; i < boardSideLength; i++) {
            System.arraycopy(board.state[i], 0, copiedState[i], 0, boardSideLength);
        }

        this.state = copiedState;
    }

    // 0: not won
    // 1: won
    // 2: error
    public int move(int x, int y, int player) {
        if (getState()[x][y] == 0) {
            int[][] copiedState = new int[getBoardSideLength()][getBoardSideLength()];
            for (int i = 0; i < getBoardSideLength(); i++) {
                System.arraycopy(getState()[i], 0, copiedState[i], 0, getBoardSideLength());
            }
            copiedState[x][y] = player;
            setState(copiedState);
        } else {
            return 2;
        }

        // column check
        if (columnCheck(x, player, x, y) == Math.min(5, getBoardSideLength())) return 1;

        // row check
        if (rowCheck(y, player, x, y) == Math.min(5, getBoardSideLength())) return 1;

        // diagonal check
        if (diagonalCheck(x, y, player, x, y) == Math.min(5, getBoardSideLength())) return 1;

        return 0;
    }

    private int columnCheck(int x, int player, int originalX, int originalY) {
        if (x < 0 || x > getBoardSideLength() - 1 || getState()[x][originalY] != player) {
            return 0;
        }

        if (originalX < x) {
            return 1 + columnCheck(x + 1, player, originalX, originalY);
        } else if (originalX > x) {
            return 1 + columnCheck(x - 1, player, originalX, originalY);
        }

        return 1 +
                columnCheck(x + 1, player, originalX, originalY) +
                columnCheck(x - 1, player, originalX, originalY);
    }

    private int rowCheck(int y, int player, int originalX, int originalY) {
        if (y < 0 || y > getBoardSideLength() - 1 || getState()[originalX][y] != player) {
            return 0;
        }

        if (originalY < y) {
            return 1 + rowCheck(y + 1, player, originalX, originalY);
        } else if (originalY > y) {
            return 1 + rowCheck(y - 1, player, originalX, originalY);
        }

        return 1 +
                rowCheck(y + 1, player, originalX, originalY) +
                rowCheck(y - 1, player, originalX, originalY);
    }

    private int diagonalCheck(int x, int y, int player, int originalX, int originalY) {
        if (x < 0 || x > getBoardSideLength() - 1 || y < 0 || y > getBoardSideLength() - 1 || getState()[x][y] != player) {
            return 0;
        }

        if (x != originalX || y != originalY) {
            if (originalX < x && originalY < y) {
                return 1 + diagonalCheck(x + 1, y + 1, player, originalX, originalY); // SE
            } else if (originalX > x && originalY > y) {
                return 1 + diagonalCheck(x - 1, y - 1, player, originalX, originalY); // NW
            } else if (originalX < x && originalY > y) {
                return 1 + diagonalCheck(x + 1, y - 1, player, originalX, originalY); // SW
            }
            return 1 + diagonalCheck(x - 1, y + 1, player, originalX, originalY); // NE
        }

        return 1 + Math.max(
                (diagonalCheck(x + 1, y + 1, player, originalX, originalY) +
                        diagonalCheck(x - 1, y - 1, player, originalX, originalY)), // NW-SE diagonal
                (diagonalCheck(x + 1, y - 1, player, originalX, originalY) +
                        diagonalCheck(x - 1, y + 1, player, originalX, originalY))); // NE-SW diagonal
    }

    public boolean isFull() {
        for (int i = 0; i < getBoardSideLength(); i++) {
            for (int j = 0; j < getBoardSideLength(); j++) {
                if (getState()[i][j] == 0) return false;
            }
        }
        return true;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public int getBoardSideLength() {
        return boardSideLength;
    }

    @Override
    public String toString() {
        return "Board{\n" +
                "  " + Arrays.toString(state[0]) + "\n" +
                "  " + Arrays.toString(state[1]) + "\n" +
                '}';
    }
}
