package tictactoe.model.state;

import java.util.Arrays;

public class Board {
    private final int[][] state;
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
        if (state[x][y] == 0) {
            state[x][y] = player;
        } else {
            return 2;
        }

        boolean colWon = true;
        boolean rowWon = true;
        boolean diagonalWon = true;

        // column check
        for (int i = 0; i < boardSideLength; i++) {
            if (state[i][y] != player) {
                colWon = false;
                break;
            }
        }

        // row check
        for (int i = 0; i < boardSideLength; i++) {
            if (state[x][i] != player) {
                rowWon = false;
                break;
            }
        }

        // diagonal check
        int position = x * boardSideLength + y;
        int boardSize = boardSideLength * boardSideLength;
        if (position % (boardSideLength + 1) != 0 && position % (boardSideLength - 1) != 0) {
            diagonalWon = false;
        }
        else if (position % (boardSideLength + 1) == 0) {
            for (int i = 0; i < boardSize; i += (boardSideLength + 1)) {
                int temp = i / boardSideLength;
                if (state[temp][temp] != player) {
                    diagonalWon = false;
                    break;
                }
            }
        }
        else {
            for (int i = boardSideLength - 1; i < boardSize - 1; i += (boardSideLength - 1)) {
                int temp = i / boardSideLength;
                if (state[temp][i % boardSideLength] != player) {
                    diagonalWon = false;
                    break;
                }
            }
        }

        return rowWon || colWon || diagonalWon ? 1 : 0;
    }

    public boolean isFull() {
        for (int i = 0; i < boardSideLength; i++) {
            for (int j = 0; j < boardSideLength; j++) {
                if (state[i][j] == 0) return false;
            }
        }
        return true;
    }

    public int[][] getState() {
        return state;
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
